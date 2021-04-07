package test;

import algorithms.maze3D.*;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) {
        IMazeGenerator3D mg = new MyMaze3DGenerator();
        long s_time_0 = System.currentTimeMillis();//todo
        Maze3D maze = mg.generate(100,100,100);
        System.out.println(String.format("%s. %s","start:",maze.getStartPosition()));
        System.out.println(String.format("%s. %s","end:",maze.getGoalPosition()));
        long e_time_0 =System.currentTimeMillis();//todo
        System.out.println (e_time_0-s_time_0); //todo
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        long s_time = System.currentTimeMillis();//todo
        solveProblem(searchableMaze, new BreadthFirstSearch());
        long e_time =System.currentTimeMillis();//todo
        System.out.println (e_time-s_time); //todo

        long s_time_1 = System.currentTimeMillis();//todo
        solveProblem(searchableMaze, new DepthFirstSearch());
        long e_time_1 =System.currentTimeMillis();//todo
        System.out.println (e_time_1-s_time_1); //todo

        long s_time_2 = System.currentTimeMillis();//todo
        solveProblem(searchableMaze, new BestFirstSearch());
        long e_time_2 =System.currentTimeMillis();//todo
        System.out.println (e_time_2-s_time_2); //todo

    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
/*        for (int i = 0; i < solutionPath.size(); i++) { System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }*/
        System.out.println(String.format("%s. %s","start",solutionPath.get(0))); //todo
        System.out.println(String.format("%s. %s","end",solutionPath.get(solutionPath.size()-1))); //todo

    }
}
