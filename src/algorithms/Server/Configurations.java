package algorithms.Server;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


/**
 * Configurations class- Allows the user to configure the thread-pool size, the generator of the maze, and the searching algorithm
 * propertiesInstance -  single object of Configurations (singleTone design pattern)
 */

//todo - ask rotem about the configuration functions + the problem of second server

public class Configurations {
    private static Configurations propertiesInstance = null; // The only instance of Configuration
    public static final String fileName = "./resources/config.properties"; //location of the configuration file
    private static Properties properties;
    private static OutputStream configurationOut;

    private Configurations() { //private constructor
        try (InputStream configurationIn = new FileInputStream(fileName)) {
            properties= new Properties();
            properties.load(configurationIn); //load configuration file input
            configurationOut = new FileOutputStream(fileName);
            //set default configuration - in case of no initialization
            properties.setProperty("threadPoolSize", "3");
            properties.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
            properties.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //if we already have propertiesInstance-> return it, else - create & return
    public static Configurations getConfigurations() {
        if(propertiesInstance == null) {
            propertiesInstance = new Configurations();
        }
        return propertiesInstance;
    }

    //setters & getters for each property

    public static void setThreadPoolSize(String value) throws IOException {
        try{
            Integer.parseInt(value); //if the given value isn't a number -> trow exception
            //check if this Property is already exist and update the right value -> if not -> create a new one
            if(properties.containsKey("threadPoolSize"))
                properties.replace("threadPoolSize" , value);
            else{
                properties.setProperty("threadPoolSize" , value);
            }
            properties.store(configurationOut, null); //save the changes to the file
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    public static void setGenerator(String value) throws Exception {
        Set<String> set = new HashSet<String>(Arrays.asList("EmptyMazeGenerator","SimpleMazeGenerator","MyMazeGenerator"));
        //make sure that value != null, and have a valid content
        if (value != null) {
            if(!set.contains(value)){
                throw new Exception("Invalid inputs: given generator name is wrong: only EmptyMazeGenerator, SimpleMazeGenerator, MyMazeGenerator are valid");
            }
            //check if this Property is already exist and update the right value -> if not -> create a new one
            if(properties.containsKey("mazeGeneratingAlgorithm"))
                properties.replace("mazeGeneratingAlgorithm", value);
            else{
                properties.setProperty("mazeGeneratingAlgorithm", value);
            }
            properties.store(configurationOut, null); //save the changes to the file
        }
        else {
            throw new Exception("Invalid inputs: Generator can't be null");
        }
    }

    public static void setSearchingAlgorithm(String value) throws Exception {
        Set<String> set = new HashSet<String>(Arrays.asList("DepthFirstSearch","BreadthFirstSearch","BestFirstSearch"));
        //make sure that value != null, and have a valid content
        if (value != null) {
            if(!set.contains(value)){
                throw new Exception("Invalid inputs: given Searching Algorithm name is wrong: only DepthFirstSearch, BreadthFirstSearch, BestFirstSearch are valid");
            }
            //check if this Property is already exist and update the right value -> if not -> create a new one
            if(properties.containsKey("mazeSearchingAlgorithm"))
                properties.replace("mazeSearchingAlgorithm", value);
            else{
                properties.setProperty("mazeSearchingAlgorithm", value);
            }
            properties.store(configurationOut, null); //save the changes to the file
        }
        else {
            throw new Exception("Invalid inputs: Searching Algorithm can't be null");
        }
    }

    //return the value of the threadPoolSize property (if it's null -> set default of 3)
    public static int getThreadsNumber() {
        String TreadsNumber = properties.getProperty("threadPoolSize");
        if(TreadsNumber==null){
            TreadsNumber="3";
        }
        return Integer.parseInt(TreadsNumber);
    }

    //return the right instance by the mazeGeneratingAlgorithm property
    public static IMazeGenerator getGeneratingAlgorithm() {
        String generator = properties.getProperty("mazeGeneratingAlgorithm");
        if(generator.equals("EmptyMazeGenerator")){
            return new EmptyMazeGenerator();
        }
        else if(generator.equals("SimpleMazeGenerator")){
            return new SimpleMazeGenerator();
        }
        return new MyMazeGenerator();
    }

    //return the right instance by the mazeSearchingAlgorithm property
    public static ISearchingAlgorithm getSearchingAlgorithm() {
        String algo = properties.getProperty("mazeSearchingAlgorithm");
        if(algo.equals("DepthFirstSearch")){
            return new DepthFirstSearch();
        }
        else if(algo.equals("BreadthFirstSearch")){
            return new BreadthFirstSearch();
        }
        return new BestFirstSearch();
    }
}
