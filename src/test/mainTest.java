package test;

import java.io.*;
import java.util.Properties;

public class mainTest {
    public static void main(String[] args) throws Exception {
        OutputStream configurationOut = new FileOutputStream("./resources/config.properties");
        Properties properties= new Properties();
        properties.setProperty("threadPoolSize", "3");
        properties.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
        properties.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
        properties.store(configurationOut, null); //save the changes to the file
    }
}