package algorithms.search;
import algorithms.mazeGenerators.Maze;

import java.util.*;

/**
 * DFS Algorithm - one option of SearchingAlgorithm
 * holds stack of nodes
 */

public class DepthFirstSearch extends ASearchingAlgorithm {
    //private Stack<AState> stack;

    //constructor
    public DepthFirstSearch() {

        struct = new Stack<AState>();
    }

    @Override
    public void addCost(AState curr, AState neighbor) {
        //remain empty
    }

/*    @Override
    //solve the problem, using DFS Algorithm
    public Solution solve(ISearchable problem){
        AState start = problem.getStartState();
        AState end = problem.getGoalState();
        //HashSet will saved visited AStates (we have seen those states while solving this problem)
        HashSet<AState> hsVisited = new HashSet<AState>();
        //the start Astate will mark as visit, and be pushed into the stack
        hsVisited.add(start);
        InsertStruct(stack,start);
        AState curr = null;
        boolean foundSolution = false; //will be true if we have found a solution //todo: add except
        while (!IsEmptyStruct(stack))
        {
            // curr = the top Astate of the stack
            //pop the next node from the Stack, return this Node, and add 1 to the visitedNodes counter
            curr = removeFromStruct(stack);
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
                    addCost(curr, neighbors.get(i));
                    hsVisited.add(neighbors.get(i));
                    neighbors.get(i).setPrevState(curr); //set curr to be this neighbor father
                    InsertStruct(stack,neighbors.get(i)); //push the neighbor into the stack
                }
                }

        }

        Solution sol = CreateSolution(foundSolution,curr,problem);
        return sol;
    }*/

    @Override
    //Solve the problem, return the Solution
    public void InsertStruct(Object stack, AState objectToInsert){
        ((Stack<AState>)stack).push(objectToInsert);
    }

    @Override
    public boolean IsEmptyStruct(Object stack) {
        return ((Stack<AState>)stack).empty();
    }

    @Override
    public AState removeFromStruct(Object stack) {
        AState poped = ((Stack<AState>)stack).pop();
        return poped;
    }
}


