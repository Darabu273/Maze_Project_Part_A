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
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import javax.swing.plaf.TableHeaderUI;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class RunCommunicateWithServers {
    public static void main(String[] args) throws Exception {//Initializing servers
        Server.defineConfigurations("mazeGeneratingAlgorithm", "MyMazeGenerator");
        Server.defineConfigurations("mazeSearchingAlgorithm", "BestFirstSearch");
        Server.defineConfigurations("threadPoolSize", "5");

        //Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());  // to open todo
        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());

        //Starting servers
        solveSearchProblemServer.start();
        //mazeGeneratingServer.start(); // to open todo

/*
        for (int i = 0; i <3; i++) {
            new Thread(()->{CommunicateWithServer_MazeGenerating();}).start();
        }
*/

        Thread[] threadsArr = new Thread[2];
        for (int i = 0; i<threadsArr.length; i++){
            threadsArr[i] = new Thread(() -> CommunicateWithServer_SolveSearchProblem());
            threadsArr[i].start();
        }

        for (int j = 0; j<threadsArr.length; j++) {
            try {
                threadsArr[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*new Thread(() -> {
                        handleClient(clientSocket);
                    }).start();
*/
        //Communicating with servers
        //CommunicateWithServer_MazeGenerating(); //to add todo
        //CommunicateWithServer_SolveSearchProblem(); // to open todo

        //Stopping all servers
        //mazeGeneratingServer.stop(); //to open todo
        solveSearchProblemServer.stop();
    }
    private static void CommunicateWithServer_MazeGenerating() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                        @Override
                        public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                            try {
                                ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                                ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                                toServer.flush();
                                int[] mazeDimensions = new int[]{50, 50};//todo 50 50
                                toServer.writeObject(mazeDimensions); //send maze dimensions to server
                                toServer.flush();
                                byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                                InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                                byte[] decompressedMaze = new byte[mazeDimensions[0]*mazeDimensions[1]+12]; ///*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*/ todo
                                //allocating byte[] for the decompressed maze -
                                is.read(decompressedMaze);
                                // Fill decompressedMaze with bytes
                                Maze maze = new Maze(decompressedMaze);
                                //maze.print();
                                long i = Thread.currentThread().getId(); //todo
                                //printMaze(maze,i); //todo
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
                                Maze maze = mg.generate(5, 5); //todo 50 50
                                //maze.print();
                                long i = Thread.currentThread().getId();
                                printMaze(maze,i);
                                toServer.writeObject(maze); //send maze to server
                                toServer.flush();
                                Solution mazeSolution = (Solution)fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                                //Print Maze Solution retrieved from the server
/*                                System.out.println(String.format("Solution steps: %s", mazeSolution));
                                ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
                                for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                                    System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
                                }*/ //todo
                                printSol(mazeSolution, i);
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

    private static synchronized void printMaze(Maze m, long id) throws Exception {
        System.out.println("thread id: " + id);
        m.print();
        System.out.println("-----");
    }

    private static synchronized void printSol(Solution mazeSolution, long id) throws Exception {
        System.out.println("thread id: " + id);
        System.out.println(String.format("Solution steps: %s", mazeSolution));
        ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
        for (int i = 0; i < mazeSolutionSteps.size(); i++) {
            System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
        }
        System.out.println("-----");
    }
}