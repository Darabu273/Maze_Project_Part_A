package test;

import algorithms.Client.Client;
import algorithms.Client.IClientStrategy;
import algorithms.IO.MyDecompressorInputStream;
import algorithms.Server.Server;
import algorithms.Server.ServerStrategyGenerateMaze;
import algorithms.Server.ServerStrategySolveSearchProblem;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.Solution;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class RunCommunicateWithServers {
    public static void main(String[] args) {//Initializing servers
        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        //Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        //Server stringReverserServer = new Server(5402, 1000, new ServerStrategyStringReverser());

        //Starting servers
        //solveSearchProblemServer.start(); // to open todo
        mazeGeneratingServer.start();
        //stringReverserServer.start();


        for (int i = 0; i <3; i++) {
            new Thread(()->{CommunicateWithServer_MazeGenerating();}).start();
            //Thread.sleep(5000);
        }
        /*new Thread(() -> {
                        handleClient(clientSocket);
                    }).start();
*/

        //Communicating with servers
        //CommunicateWithServer_MazeGenerating(); //to add todo
        //CommunicateWithServer_SolveSearchProblem(); //to open todo
        //CommunicateWithServer_StringReverser();

        //Stopping all servers
        //mazeGeneratingServer.stop();//to add todo
        //solveSearchProblemServer.stop(); //to open todo
        //stringReverserServer.stop();
    }
    private static void CommunicateWithServer_MazeGenerating() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                        @Override
                        public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                            try {
                                ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                                System.out.println("arrived");
                                ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                                System.out.println("not arrived");

                                toServer.flush();
                                int[] mazeDimensions = new int[]{5, 5};//todo 50 50
                                toServer.writeObject(mazeDimensions); //send maze dimensions to server
                                toServer.flush();
                                byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                                InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                                byte[] decompressedMaze = new byte[mazeDimensions[0]*mazeDimensions[1]+12]; ///*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*/ todo
                                //allocating byte[] for the decompressed maze -
                                is.read(decompressedMaze);
                                // Fill decompressedMaze with bytes
                                Maze maze = new Maze(decompressedMaze);
                                maze.print();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    private static void CommunicateWithServer_SolveSearchProblem() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                        @Override
                        public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                            try {
                                ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                                ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                                toServer.flush();
                                MyMazeGenerator mg = new MyMazeGenerator();
                                Maze maze = mg.generate(50, 50);
                                maze.print();
                                toServer.writeObject(maze); //send maze to server
                                toServer.flush();
                                Solution mazeSolution = (Solution)fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                                //Print Maze Solution retrieved from the server
                                System.out.println(String.format("Solution steps: %s", mazeSolution));
                                ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
                                for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                                    System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
/*    private static void CommunicateWithServer_StringReverser() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5402, new IClientStrategy() {
                        @Override
                        public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                            try {
                                BufferedReader fromServer = new BufferedReader(new InputStreamReader(inFromServer));
                                PrintWriter toServer = new PrintWriter(outToServer);
                                26 | P a g e
                                        %s", serverResponse));
                                String message = "Client Message";
                                String serverResponse;
                                toServer.write(message + "\n");
                                toServer.flush();
                                serverResponse = fromServer.readLine();
                                System.out.println(String.format("Server response:
                                        toServer.flush();
                                fromServer.close();
                                toServer.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }*/
}