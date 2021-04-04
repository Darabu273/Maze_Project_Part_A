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
    public Solution solve(ISearchable problem){
        AState temp = DFS(problem, stack);
        if(temp==null){return null;} //todo: except?
        //return the solution path which has been found -reverse the path of the problem
        ArrayList<AState> path = new ArrayList<AState>();
        while(!temp.equals(problem.getStartState())){
            path.add(0,temp);
            temp = temp.getPrevState();
        }
        path.add(0,problem.getStartState());
        //create a Solution instance from this path
        Solution sol = new Solution(path);
        return sol;
    }

    //dfs search algorithm
    public AState DFS(ISearchable problem, Stack<AState> stack)
    {
        AState start = problem.getStartState();
        AState end = problem.getGoalState();
        //HashSet will saved visited AState (we have seen this state of the problem)
        HashSet<AState> hsVisited = new HashSet<AState>();
        //the start node in the stack
        hsVisited.add(start);
        stack.push(start);

        while (!stack.empty())
        {
            // curr = the top  Astate of the stack
            AState curr = popFromStructure();

            //if we have found the goal state - finish the algorithm
            if (curr.equals(end))
                return curr;

            // find all the neighbors of the state
            ArrayList<AState> neighbors = problem.getAllSuccessors(curr);
            for (int i = 0; i < neighbors.size(); ++i)
            {
                if (!(hsVisited.contains(neighbors.get(i)))){ //check if this neighbor is visited
                    hsVisited.add(neighbors.get(i)); //if not - add this neighbor to the hsVisited hash table
                    neighbors.get(i).setPrevState(curr); //set curr to be this neighbor father
                    stack.push(neighbors.get(i)); //push the neighbor into the stack
                }
            }
        }
        return null;
    }

    @Override
    //pop the next node from the Stack, return this Node, and add 1 to the visitedNodes counter
    protected AState popFromStructure() {
        visitedNodes++;
        return stack.pop();
    }

}


