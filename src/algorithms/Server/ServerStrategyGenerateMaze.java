package algorithms.Server;
import algorithms.IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;
import java.io.*;

/**
 * ServerStrategyGenerateMaze implements IServerStrategy interface
 * applyStrategy will build a maze by get int array with 2 parameters, holds the size of the rows and columns maze we will create.
 */

public class ServerStrategyGenerateMaze implements IServerStrategy {
    IMazeGenerator MymazeGenerator = new MyMazeGenerator();
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

            int[] clientCommand;
            try {
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

                if (fromClient != null ) { //checking we got normal data
                    clientCommand =(int[])fromClient.readObject();// we got int[] in 2 size, that holds the size of the rows and columns of the maze that we will create.
                    try {
                        //clientCommand[0] = hold the rows size , clientCommand[1] - hold the columns soze
                        Maze maze = MymazeGenerator.generate(clientCommand[0], clientCommand[1]);
                        //Compress maze
                        ByteArrayOutputStream sourceTosend =  new ByteArrayOutputStream(maze.toByteArray().length); //helper variable of ByteArrayOutputStream type that will contain the compress maze and the meta data we want to send to client
                        OutputStream out = new MyCompressorOutputStream(sourceTosend); //the connection between sourceTosend -> out -> toClient
                        out.write(maze.toByteArray());
                        out.flush();
                        toClient.writeObject(sourceTosend.toByteArray()); //write to Client the compress data (the maze after we compressed it)
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
