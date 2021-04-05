package algorithms.search;

import java.util.*;

/**
 * BFS Algorithm - one option of SearchingAlgorithm
 * holds queue of nodes
 */

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> queue;

    public BreadthFirstSearch() {
        queue = new LinkedList<AState>();
    }

    @Override
    //BFS algorithm
    public Solution solve(ISearchable problem) {
        AState start = problem.getStartState();
        AState end = problem.getGoalState();
        //HashMap will saved visited AStates (we have seen those states while solving this problem)
        //key = Astate's toString result (string)
        //value = the Astate itself
        HashMap<String, AState> hsVisited = new HashMap<String, AState>();
        //the start Astate will mark as visit, and be pushed into the queue
        hsVisited.put(start.toString(), start);
        queue.add(start);

        AState curr = null;
        boolean foundSolution = false; //will be true if we have found a solution //todo: add except
        while (!queue.isEmpty())
        {
            // curr = the first  Astate of the queue
            //poll the next node from the queue, return this Node, and add 1 to the visitedNodes counter
            curr = queue.poll();
            visitedNodes++;

            //if we have found the goal state - finish the algorithm
            if (curr.equals(end)){
                foundSolution = true;
                break;}

            // find all the neighbors of the state
            ArrayList<AState> neighbors = problem.getAllSuccessors(curr);
            for (int i = 0; i < neighbors.size(); ++i)
            {
                if (!(hsVisited.containsKey(neighbors.get(i).toString()))){ //check if this neighbor is visited
                    //if not - add this neighbor to the hsVisited hash table
                    hsVisited.put(neighbors.get(i).toString(), neighbors.get(i));
                    neighbors.get(i).setPrevState(curr); //set curr to be this neighbor father
                    queue.add(neighbors.get(i)); //push the neighbor into the queue
                }
            }
        }

        if(!foundSolution || curr == null){return null;} //todo: except?
        //return the solution path which has been found -reverse the path of the problem
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
