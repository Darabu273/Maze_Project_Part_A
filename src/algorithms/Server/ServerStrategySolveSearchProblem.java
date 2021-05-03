package algorithms.Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ServerStrategySolveSearchProblem implements IServerStrategy interface
 * applyStrategy will solve the a maze problem and will return Solution Object of the maze.
 */

//todo: check with rotem about creation of file (temporaty? need to delete after running?)

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private ISearchingAlgorithm searcher = new BestFirstSearch(); //searcher to solve the given maze
    private AtomicInteger solNumber = new AtomicInteger(0); //solNumber will hold the uniqe index of each maze-solution
    ConcurrentHashMap<String, Integer> SolutionsMap = new ConcurrentHashMap<>(); //hash map that save the solution index for each maze
    String tempDirectoryPath = System.getProperty("java.io.tmpdir"); //the temporary directory path

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze clientMaze =(Maze)fromClient.readObject(); //read maze-input from client
            byte[] contentInByte = clientMaze.toByteArray();
            String mazeContent = ""; //convert maze content (in bytes) to string
            for (int i = 0; i < contentInByte.length; i++) {
                mazeContent+=contentInByte[i];
            }
            Integer currMazeSolIndex; //will hold the current maze solution index
            Solution solution;
            if(this.SolutionsMap.containsKey(mazeContent)){ //check if this maze solution is already exists
                currMazeSolIndex=SolutionsMap.get(mazeContent); //current maze solution index = the index that saves in map
                //return the solution that save in it's file
                String mazeFileName = currMazeSolIndex.toString() + ".txt"; //the name of the solution file
                ObjectInputStream solFromFile = new ObjectInputStream(new FileInputStream(tempDirectoryPath+"\\"+mazeFileName));
                solution = (Solution) solFromFile.readObject();
                solFromFile.close();
            }
            else{ //this maze has never been solved before
                //find the new solution and save this solution to a new file
                SearchableMaze searchableMaze = new SearchableMaze(clientMaze); //create new instance of searchableMaze
                solution = searcher.solve(searchableMaze);//find searchableMaze solution
                currMazeSolIndex=solNumber.getAndIncrement(); //current maze solution index = current solNumber (atomic) value
                String mazeFileName = currMazeSolIndex.toString() + ".txt"; //the name of the solution file

                updateClientAndHash(mazeContent, solution, currMazeSolIndex, mazeFileName);
                //solution = getSolution(searchableMaze); //find searchableMaze solution todo
                //ArrayList<AState> solutionPath = solution.getSolutionPath(); todo
            }

            toClient.writeObject(solution); //send solution to client
            toClient.flush();

            fromClient.close();
            toClient.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }    private synchronized void updateClientAndHash(String mazeContent, Solution solution, Integer currMazeSolIndex, String mazeFileName) throws IOException {
        SolutionsMap.put(mazeContent, currMazeSolIndex); //add this maze string & index of new solution to the map
        ObjectOutputStream solutionToFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath+"\\"+mazeFileName));
        solutionToFile.writeObject(solution);
        solutionToFile.flush();
        solutionToFile.close();
    }

/*    private synchronized Solution getSolution(SearchableMaze searchableMaze) throws Exception {
        Solution sol = searcher.solve(searchableMaze);
        return sol;
    }*/

}


/*                    ArrayList<AState> solutionPath = solution.getSolutionPath(); //todo: delete
                    for (int i = 0; i < solutionPath.size(); i++) { //todo: delete
                        System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
                    }//todo: check and return Sol to client*/