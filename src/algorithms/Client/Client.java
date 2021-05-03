package algorithms.Client;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


/**
 * the client class will communicate with the servers
 */
public class Client {
    private InetAddress serverIP; //the IP server we will connect to
    private int serverPort; ////the Port server we will connect to - will be same as the Port of the Client
    private IClientStrategy strategy; //the strategy of the client

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer() // the connect between Client and the suitable server and applying the client Strategy that we got in RunCommunicateWithServers class.
    {
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
        strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
    } catch (IOException e) {
        e.printStackTrace();
    }}
}
