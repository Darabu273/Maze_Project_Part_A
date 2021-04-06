package test;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

public class GeneralCheckingFunctions {
    /*public static boolean check3DMaze(){ // if you have chosen to not do the Maze3D assignment, please change this value to false.
        // Note, that if this will be false, you will automatically get minus 10 in the score of the first part of the project.
        boolean weChoseToDoTheMaze3DAssignment = true;
        return weChoseToDoTheMaze3DAssignment; }*/
    public static void main(String[] args) {
        //testMazeGenerator(new EmptyMazeGenerator());
        //testMazeGenerator(new SimpleMazeGenerator());
        testMaze3DGenerator(new MyMaze3DGenerator());
    }

    private static void testMaze3DGenerator(IMazeGenerator3D mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(3,3/*rows*/, 5/*columns*/)));
        // generate another maze
        Maze3D maze = mazeGenerator.generate(3, 3/*rows*/, 5/*columns*/);
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
