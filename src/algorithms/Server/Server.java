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


    public Server(int port, int listeningIntervalMS ,IServerStrategy strategy) {
        this.port = port;
        this.strategy = strategy;
        this.listeningIntervalMS=listeningIntervalMS;
        this.threadPool = Executors.newCachedThreadPool();

    }
    public void start() {
        threadPool.execute(()->{
            threadStart(); });

    }//todo
    public void stop(){stop = true;}

    public void handleClient(Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            System.out.println("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e){
            System.out.println("IOException");
        }
    }

    public void threadStart(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());

                    threadPool.execute(() -> {
                        handleClient(clientSocket);
                    });

                } catch (SocketTimeoutException e) {
                    System.out.println("Socket timeout");
                }
            }
            threadPool.shutdownNow();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
