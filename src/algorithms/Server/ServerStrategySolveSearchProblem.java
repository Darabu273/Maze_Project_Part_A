package algorithms.Server;

import algorithms.IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

/**
 * ServerStrategySolveSearchProblem implements IServerStrategy interface
 * applyStrategy will solve the a maze problem and will return Solution Object of the maze.
 */

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    ISearchingAlgorithm searcher = new BestFirstSearch();
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            if (fromClient != null ) {
                Maze clientMaze =(Maze)fromClient.readObject(); //read maze-input from client
                SearchableMaze searchableMaze = new SearchableMaze(clientMaze); //create new instance of searchableMaze
                Solution solution = GetSolution(searchableMaze); //find searchableMaze solution
                toClient.writeObject(solution); //send solution to client
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private synchronized Solution GetSolution(SearchableMaze searchableMaze) throws Exception {
        Solution solution = searcher.solve(searchableMaze);;
        return solution;
    }

}
