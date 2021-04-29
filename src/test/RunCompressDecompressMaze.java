package test;
//import IO.MyCompressorOutputStream;
//import IO.MyDecompressorInputStream;
import algorithms.IO.MyCompressorOutputStream;
import algorithms.IO.MyDecompressorInputStream;
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
        Maze maze = mazeGenerator.generate(100,100); //Generate new maze
        //maze.print();
        try {
            // save maze to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();

            OutputStream out2 = new SimpleCompressorOutputStream(new FileOutputStream("test.txt")); //todo
            out2.write(maze.toByteArray()); //todo
            out2.flush(); //todo
            out2.close(); //todo
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        byte savedMazeBytes2[] = new byte[0]; //todo
        try {
            //read maze from file
            InputStream in2 = new SimpleDecompressorInputStream(new FileInputStream("test.txt")); //todo
            savedMazeBytes2 = new byte[maze.toByteArray().length]; //todo
            in2.read(savedMazeBytes2); //todo
            in2.close(); //todo

            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals =Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals));

        Maze loadedMaze_2 = new Maze(savedMazeBytes2); //todo
        boolean check =Arrays.equals(loadedMaze_2.toByteArray(),loadedMaze.toByteArray()); //todo
        System.out.println("another check"); //todo
        System.out.println(String.format("Mazes equal: %s",check)); //todo
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