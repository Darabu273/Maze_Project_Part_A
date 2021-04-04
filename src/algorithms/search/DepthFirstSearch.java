package algorithms.search;
import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;


/**
 * DFS Algorithm - one option of SearchingAlgorithm
 * holds stack of nodes
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    private Stack<AState> stack;

    //constructor
    public DepthFirstSearch() {
        stack = new Stack<AState>();
    }
    @Override
    //solve the problem, using DFS Algorithm
    public Solution solve(ISearchable problem) {
        DFS(problem, stack);
        //return the solution path which has been found -reverse the path of the problem
        ArrayList<AState> path = new ArrayList<AState>();
        AState temp = problem.getGoalState();
        while(temp != problem.getStartState()){
            path.add(0,temp);
            temp = temp.getPrevState();
        }
        path.add(0,problem.getStartState());
        //create a Solution instance from this path
        Solution sol = new Solution(path);
        return sol;
    }

    public void DFS(ISearchable problem, Stack<AState> stack)
    {
        AState start = problem.getStartState();
        AState end = problem.getGoalState();
        // HashSet will saved visited AState (we have seen this state of the problem)
        HashSet<AState> hsVisited = new HashSet<AState>();
        //  the start node in the stack
        hsVisited.add(start);
        stack.push(start);


        // While there is node to be handled in the stack
        while (!stack.empty())
        {
            // Handle the node on the top of the stack and retrieve its unvisited neighbors
            AState curr = popFromStructure();

            // Terminate if the goal is reached
            if (curr.equals(end))
                break;

            // Take unvisited neighbors in order (eg. Noth, East, South, West),
            // set its parent, mark as visited, and add to the stack
            ArrayList<AState> neighbors = problem.getAllSuccessors(curr);
            for (int i = 0; i < neighbors.size(); ++i)
            {
                if (!(hsVisited.contains(neighbors.get(i)))){
                    hsVisited.add(neighbors.get(i));
                    neighbors.get(i).setPrevState(curr);
                    stack.push(neighbors.get(i));
                }
            }
        }

        // Done ! At this point we just have to walk back from the end using the parent
        // If end does not have a parent, it means that it has not been found.
    }

    @Override
    //pop the next node from the Stack, return this Node, and add 1 to the visitedNodes counter
    protected AState popFromStructure() {
        visitedNodes++;
        return stack.pop();
    }

}


