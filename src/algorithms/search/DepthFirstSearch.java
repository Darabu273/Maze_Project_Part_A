package algorithms.search;
import java.util.ArrayList;
import java.util.Stack;


/**
 * DFS Algorithm - one option of SearchingAlgorithm
 * holds stack of nodes
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    private Stack<Node> stack;

    //constructor
    public DepthFirstSearch(ISearchable problem) {
        super(problem);
        stack = new Stack<Node>();
    }

    @Override
    //solve the problem, using DFS Algorithm
    public Solution solve(ISearchable problem) {

        //todo: add DFS code, https://hurna.io/academy/algorithms/maze_pathfinder/dfs.html


        //return the solution path which has been found
        ArrayList<AState> path = new ArrayList<AState>();
        //create a Solution instance from this path
        Solution sol = new Solution(path);
        return sol;
    }

    @Override
    //pop the next node from the Stack, return this Node, and add 1 to the visitedNodes counter
    protected AState popFromStructure() {
        visitedNodes++;
        return stack.pop().getValue();
    }

}


