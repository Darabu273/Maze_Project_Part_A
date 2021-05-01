package algorithms.Server;
import algorithms.IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.awt.*;
import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    IMazeGenerator MymazeGenerator = new MyMazeGenerator();
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

            int[] clientCommand;
            int index = 0;
            try {
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

                if (fromClient != null ) {
                    clientCommand =(int[])fromClient.readObject();
                    try {
                        Maze maze = MymazeGenerator.generate(clientCommand[0], clientCommand[1]);
                        //Compress maze
                        ByteArrayOutputStream sourceTosend =  new ByteArrayOutputStream(maze.toByteArray().length);
                        OutputStream out = new MyCompressorOutputStream(sourceTosend);
                        out.write(maze.toByteArray());
                        out.flush();
                        toClient.writeObject(sourceTosend.toByteArray());
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
