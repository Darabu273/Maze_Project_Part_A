package algorithms.search;

import java.util.*;

/**
 * BFS Algorithm - one option of SearchingAlgorithm
 * holds queue of nodes
 */

public class BreadthFirstSearch extends ASearchingAlgorithm{
    //protected Queue<AState> queue;

    public BreadthFirstSearch() {

        struct = new LinkedList<AState>();
    }

    @Override
    public void addCost(AState curr, AState neighbor) {
        //remain empty
    }
/*
    @Override
    //BFS algorithm
    public Solution solve(ISearchable problem) {
        AState start = problem.getStartState();
        AState end = problem.getGoalState();
        //HashSet will saved visited AStates (we have seen those states while solving this problem)
        HashSet<AState> hsVisited = new HashSet<AState>();
        //the start Astate will mark as visit, and be pushed into the queue
        hsVisited.add(start);
        InsertStruct(queue,start);
        AState curr = null;

        boolean foundSolution = false; //will be true if we have found a solution //todo: add except
        while (!IsEmptyStruct(queue))
        {
            // curr = the first  Astate of the queue
            //poll the next node from the queue, return this Node, and add 1 to the visitedNodes counter
            curr = removeFromStruct(queue);
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
                    InsertStruct(queue,neighbors.get(i)); //push the neighbor into the queue

                }
            }
        }

        Solution sol = CreateSolution(foundSolution,curr,problem);
        return sol;

    }*/

    @Override
    //Solve the problem, return the Solution
    public void InsertStruct(Object queue, AState objectToInsert){
        ((Queue<AState>)queue).add(objectToInsert);
    }

    @Override
    public boolean IsEmptyStruct(Object queue) {
        return ((Queue<AState>)queue).isEmpty();
    }

    @Override
    public AState removeFromStruct(Object queue) {
        AState poped = ((Queue<AState>)queue).poll();
        return poped;
    }

}
