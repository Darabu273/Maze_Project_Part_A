package algorithms.Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//todo: maze string - compressed

/**
 * ServerStrategySolveSearchProblem implements IServerStrategy interface
 * applyStrategy will solve the a maze problem and will return Solution Object of the maze.
 */

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    String tempDirectoryPath = System.getProperty("java.io.tmpdir"); //the temporary directory path
    ConcurrentHashMap<String, Integer> SolutionsMap = BuildHashMapFromFile(); //hash map that save the solution index for each maze
    private AtomicInteger solNumber = new AtomicInteger(SolutionsMap.size()); //solNumber will hold the uniqe index of each maze-solution

    //this func will create the SolutionsMap file if not exist, or if it does - build the SolutionsMap by the file content
    //with this operation, we will remember the solutions of old mazes that this server requested to solve in the past
    public ConcurrentHashMap<String, Integer> BuildHashMapFromFile() {
        //create an empty map
        ConcurrentHashMap<String, Integer> mapOfSolutions = new ConcurrentHashMap<>(); //hash map that save the solution index for each m
        try{
            //check if SolutionsMap file exist (if it's the first server call - it will not be exist)
            File solFile = new File(tempDirectoryPath+"\\SolutionsMap.txt");
            if(!solFile.exists()) {
                solFile.createNewFile(); //create the file in the directory
                return mapOfSolutions; //return empty map
            }
            //if file is exist, read file content into the hash map, line by line
            else if(solFile.exists() && !solFile.isDirectory()) {
                BufferedReader reader = new BufferedReader(new FileReader(tempDirectoryPath+"\\SolutionsMap.txt"));
                String line = reader.readLine();
                while (line != null){
                    int indexOfP = line.indexOf(",");
                    if(indexOfP>=0){ //if "," is exist (no empty line)
                        String tempMaze = line.substring(0,indexOfP);
                        String tempNumber = line.substring(indexOfP+1);
                        Integer number = Integer.parseInt(tempNumber);
                        mapOfSolutions.put(tempMaze, number); //update hash map with maze content and solution index
                        line= reader.readLine();
                    }
                }
                reader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapOfSolutions;
    }

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {

            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            ISearchingAlgorithm searcher = Configurations.getSearchingAlgorithm(); //searcher to solve the given maze

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
            else{ //if this maze has never been solved before
                //find the new solution and save this solution to a new file
                SearchableMaze searchableMaze = new SearchableMaze(clientMaze); //create new instance of searchableMaze
                solution = searcher.solve(searchableMaze);//find searchableMaze solution
                currMazeSolIndex=solNumber.getAndIncrement(); //current maze solution index = current solNumber (atomic) value
                String mazeFileName = currMazeSolIndex.toString() + ".txt"; //the name of the solution file
                //updateClientAndHash(mazeContent, solution, currMazeSolIndex, mazeFileName);
                SolutionsMap.put(mazeContent, currMazeSolIndex); //add this maze string & index of new solution to the map
                UpdateHashMapFile(mazeContent, currMazeSolIndex); //update the file that saves the hash map content in synchronized method
                ObjectOutputStream solutionToFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath+"\\"+mazeFileName));
                solutionToFile.writeObject(solution);
                solutionToFile.flush();
                solutionToFile.close();
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

    }

    //update the file that saves the hash map content in synchronized way - prevent threads races
    private synchronized void UpdateHashMapFile(String mazeContent, Integer currMazeSolIndex) {
        try {
            String str = mazeContent + "," + currMazeSolIndex;
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempDirectoryPath+"\\SolutionsMap.txt", true));
            writer.append(str);
            writer.append("\n");
            writer.close();

            try (BufferedReader br = new BufferedReader(new FileReader(tempDirectoryPath+"\\SolutionsMap.txt"))) { //todo delete
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
