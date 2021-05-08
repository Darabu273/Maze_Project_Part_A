package algorithms.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * the server will communicate will multiply clients in parallel by using threads, that will be managed by thread-pool
 * the server will run in it's own thread
 */
public class Server {
    private int port;
   private int listeningIntervalMS;
    private IServerStrategy strategy;
    private ExecutorService threadPool;
    private volatile boolean stop;
    private static Configurations configFile = Configurations.getConfigurations();
    private int treadsNumber; //how many threads we have in the pool


    public Server(int port, int listeningIntervalMS ,IServerStrategy strategy) {
        this.port = port;
        this.strategy = strategy;
        this.listeningIntervalMS=listeningIntervalMS;
        treadsNumber = Configurations.getThreadsNumber();
        this.threadPool = Executors.newFixedThreadPool(treadsNumber);
    }
    public void start() { // create server thread that will enable to the Main Program to rum parallel to the other threads that execute there task.
        new Thread(()->{
            threadStart(); }).start();

    }

    public void threadStart(){ //connect between Server and Client with the same port
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            while (!stop) { // the Server will work and wait for clients until we change stop to true.
                try {
                    Socket clientSocket = serverSocket.accept(); //waiting for connecting client

                    //handleClient function is thread's task that get the current connection with clientSocket
                    threadPool.execute(() -> {handleClient(clientSocket);});

                } catch (SocketTimeoutException e) {
                    //do nothing
                }
            }
            threadPool.shutdown(); //when all the tasks we want to do will finish we will shut down the server service.
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        stop = true;}

    public void handleClient(Socket clientSocket) { //This function will apply in generically in the relevant Strategy on the current Client
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
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
