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
        System.out.println(String.format("%s. %s","start:",maze.getStartPosition())); //todo
        System.out.println(String.format("%s. %s","end:",maze.getGoalPosition())); //todo
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
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
        System.out.println(String.format("%s. %s","start:",solutionPath.get(0))); //todo
        System.out.println(String.format("%s. %s","end:",solutionPath.get(solutionPath.size()-1))); //todo
    }
}