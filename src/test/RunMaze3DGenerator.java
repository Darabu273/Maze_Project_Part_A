package test;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;

public class RunMaze3DGenerator {

    public static void main(String[] args) throws Exception {
        testMaze3DGenerator(new MyMaze3DGenerator());
    }

    private static void testMaze3DGenerator(IMazeGenerator3D maze3DGenerator) throws Exception {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", maze3DGenerator.measureAlgorithmTimeMillis(100,100, 100)));
        // generate another maze
        Maze3D maze = maze3DGenerator.generate(3,5,5);
        // prints the maze
        maze.print();
        // get the maze entrance
        Position3D startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }

}
