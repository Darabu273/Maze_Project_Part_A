package algorithms.search;
import algorithms.mazeGenerators.Maze;

import java.util.*;

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
        AState start = problem.getStartState();
        AState end = problem.getGoalState();
        //HashSet will saved visited AStates (we have seen those states while solving this problem)
        HashSet<AState> hsVisited = new HashSet<AState>();
        //the start Astate will mark as visit, and be pushed into the stack
        hsVisited.add(start);

        stack.push(start);

        AState curr = null;
        boolean foundSolution = false; //will be true if we have found a solution //todo: add except
        while (!stack.empty())
        {
            // curr = the top Astate of the stack
            //pop the next node from the Stack, return this Node, and add 1 to the visitedNodes counter
            curr = stack.pop();
            visitedNodes++;

            //if we have found the goal state - finish the algorithm
            if (curr.equals(end)){
                foundSolution = true;
                break;}

            // find all the neighbors of the state
            ArrayList<AState> neighbors = problem.getAllSuccessors(curr);
            for (int i = 0; i < neighbors.size(); ++i)
            {
                if (!(hsVisited.contains(neighbors.get(i)))) { //check if this neighbor is visited
                    //if not - add this neighbor to the hsVisited hash table
                    hsVisited.add(neighbors.get(i));
                    neighbors.get(i).setPrevState(curr); //set curr to be this neighbor father
                    stack.push(neighbors.get(i)); //push the neighbor into the stack
                }
                }

        }

        if(!foundSolution || curr == null){return null;} //todo: except?
        //return the solution path which has been found - reverse the path of the problem
        ArrayList<AState> path = new ArrayList<AState>();
        while(!curr.equals(problem.getStartState())){
            path.add(0,curr);
            curr = curr.getPrevState(); }

        path.add(0,problem.getStartState());
        //create a Solution instance from this path
        Solution sol = new Solution(path);
        return sol;
    }

}


