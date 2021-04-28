package test;
//import IO.MyCompressorOutputStream;
//import IO.MyDecompressorInputStream;
import algorithms.IO.SimpleCompressorOutputStream;
import algorithms.IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) throws Exception {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(10,10); //Generate new maze
        maze.print();
        try {
            // save maze to a file
            OutputStream out = new SimpleCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new SimpleDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals =Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        //maze should be equal to loadedMaze*/
    }
}

/*        Maze test = new Maze(maze.toByteArray()); //todo
        boolean check =Arrays.equals(test.toByteArray(),maze.toByteArray()); //todo
        System.out.println("first check"); //todo
        System.out.println(String.format("Mazes equal: %s",check)); //todo
        System.out.println("end check");//todo*/


/*        //todo
        byte[] newM = loadedMaze.toByteArray();
        byte[] oldM = maze.toByteArray();
        for (int i = 0; i < newM.length; i++) {
            if(newM[i] != oldM[i]){
                System.out.println("index: "+ i +" new maze: " +newM[i] +" old maze: "+ oldM[i]);
            }
        }
        //todo*/