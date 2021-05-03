package algorithms.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
   private int listeningIntervalMS;
    private IServerStrategy strategy;
    private ExecutorService threadPool;
    private volatile boolean stop;
    private static Configurations configFile = Configurations.getConfigurations();
    private int treadsNumber;


    public Server(int port, int listeningIntervalMS ,IServerStrategy strategy) {
        this.port = port;
        this.strategy = strategy;
        this.listeningIntervalMS=listeningIntervalMS;
        treadsNumber = Configurations.getThreadsNumber();
        this.threadPool = Executors.newFixedThreadPool(treadsNumber);
    }
    public void start() { // create MainThread in the threadPool that will enable to the Main Program to rum parallel to the other threads that execute there task.
        new Thread(()->{
            threadStart(); }).start();

    }

    public void stop(){stop = true;}

    public void handleClient(Socket clientSocket) { //This function will apply in generically in the relevant Strategy on the current Client
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            System.out.println("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e){
            System.out.println("IOException");
        }
    }

    public void threadStart(){ //connect between Server and Client with the same port
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);

            while (!stop) { // the Server will work and wait for clients until we change stop to true.
                try {
                    Socket clientSocket = serverSocket.accept(); //waiting for connecting client
                    System.out.println("Client accepted: " + clientSocket.toString());

                    //handleClient function is thread's task that get the current connection with clientSocket
                    threadPool.execute(() -> {
                        handleClient(clientSocket);
                    });

                } catch (SocketTimeoutException e) {
                    System.out.println("Socket timeout"); //todo
                }
            }
            threadPool.shutdown(); //when all the tasks we want to do will finish we will shut down the server service.
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static void defineConfigurations(String Property, String value) throws Exception {
        if(Property.equals("threadPoolSize")){
            Configurations.setThreadPoolSize(value);
        }
        else if(Property.equals("mazeGeneratingAlgorithm")){
            Configurations.setGenerator(value);
        }
        else if(Property.equals("mazeSearchingAlgorithm")){
            Configurations.setSearchingAlgorithm(value);
        }
    }
}
