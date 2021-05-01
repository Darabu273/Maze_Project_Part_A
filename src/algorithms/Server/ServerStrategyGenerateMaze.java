package algorithms.Server;
import algorithms.IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.awt.*;
import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    IMazeGenerator MymazeGenerator = new MyMazeGenerator();
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

            String clientCommand;
            int index = 0;
            try {
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

                if (fromClient != null ) {
                    clientCommand = (String)fromClient.readObject();
                    for (int i = 0; i < clientCommand.length(); i++) {
                        if (clientCommand.charAt(i) == ',') {
                            index = i;
                            break;
                        }
                    }
                    int row = Integer.parseInt(String.valueOf(clientCommand.substring(1, index - 1)), 10);
                    int col = Integer.parseInt(String.valueOf(clientCommand.substring(index + 1, clientCommand.length() - 2)), 10);
                    try {
                        Maze maze = MymazeGenerator.generate(row, col);
                        //Compress maze
                        OutputStream out = new MyCompressorOutputStream(toClient);
                        out.write(maze.toByteArray());
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


}
