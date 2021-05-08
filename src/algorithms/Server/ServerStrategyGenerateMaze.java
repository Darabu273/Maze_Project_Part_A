package algorithms.Server;
import algorithms.IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;
import java.io.*;

/**
 * ServerStrategyGenerateMaze implements IServerStrategy interface
 * applyStrategy will build a maze by get int array with 2 parameters, holds the size of the rows and columns maze we will create.

 */

public class ServerStrategyGenerateMaze implements IServerStrategy {
        @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {

            try {
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

                int[] clientCommand =(int[])fromClient.readObject();// we got int[] in 2 size, that holds the size of the rows and columns of the maze that we will create.
                try {
                    IMazeGenerator MyMazeGenerator = Configurations.getGeneratingAlgorithm();
                    //clientCommand[0] = hold the rows size , clientCommand[1] - hold the columns soze
                    Maze maze = MyMazeGenerator.generate(clientCommand[0], clientCommand[1]);
                    //Compress maze
                    ByteArrayOutputStream sourceToSend =  new ByteArrayOutputStream(maze.toByteArray().length); //helper variable of ByteArrayOutputStream type that will contain the compress maze and the meta data we want to send to client
                    OutputStream out = new MyCompressorOutputStream(sourceToSend); //the connection between sourceTosend -> out -> toClient
                    out.write(maze.toByteArray());
                    out.flush();
                    toClient.writeObject(sourceToSend.toByteArray()); //write to Client the compress data (the maze after we compressed it)
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fromClient.close();
                toClient.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


}
