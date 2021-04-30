package algorithms.Server;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(inFromClient));
        BufferedWriter toClient = new BufferedWriter(new PrintWriter(outToClient));

        String clientCommand;
        try {
            while (fromClient != null && !(clientCommand = fromClient.readLine()).toLowerCase().equals("exit")) {

            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    }
}
