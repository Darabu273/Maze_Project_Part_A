package algorithms.Server;

public class Server {
    private int port;
   //private int listeningIntervalMS;
    private IServerStrategy strategy;

    public Server(int port, IServerStrategy strategy) {
        this.port = port;
        this.strategy = strategy;
    }
}
