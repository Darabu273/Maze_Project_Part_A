package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(10, 10);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        long s_time = System.currentTimeMillis(); //todo
        solveProblem(searchableMaze, new BreadthFirstSearch());
        long e_time =System.currentTimeMillis(); //todo
        System.out.println("time:"); //todo
        System.out.println(e_time-s_time); //return end time minus start time //todo
       long s_time2 = System.currentTimeMillis(); //todo
        solveProblem(searchableMaze, new DepthFirstSearch());
        long e_time2 =System.currentTimeMillis(); //todo
        System.out.println("time:"); //todo
        System.out.println(e_time2-s_time2); //return end time minus start time //todo
         long s_time3 = System.currentTimeMillis(); //todo
        solveProblem(searchableMaze, new BestFirstSearch());
        long e_time3 =System.currentTimeMillis(); //todo
        System.out.println("time:"); //todo
        System.out.println(e_time3-s_time3); //return end time minus start time //todo
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
                System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) { System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
}