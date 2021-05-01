package algorithms.Client;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP; //the IP server we will connect to
    private int serverPort; ////the Port server we will connect to - will be same as the Port of the Client
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer() // the connect between Client and the suitable server and applying the client Strategy that we got in RunCommunicateWithServers class.
    {
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
        System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
        strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
    } catch (IOException e) {
        e.printStackTrace();
    }}
}
