package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    Comparator<AState> stateSorter = Comparator.comparing(AState::getSumCost);

    public BestFirstSearch() {
        struct = new PriorityQueue<AState>(stateSorter);

    }
/*    @Override
    public Solution solve(ISearchable problem) {
        AState start = problem.getStartState();
        AState end = problem.getGoalState();
        //HashSet will saved visited AStates (we have seen those states while solving this problem)
        HashSet<AState> hsVisited = new HashSet<AState>();
        //the start Astate will mark as visit, and be pushed into the queue
        hsVisited.add(start);
        queue.add(start);

        AState curr = null;
        boolean foundSolution = false; //will be true if we have found a solution //todo: add except
        while (!queue.isEmpty()) {
            // curr = the first  Astate of the queue
            //poll the next node from the queue, return this Node, and add 1 to the visitedNodes counter
            curr = queue.poll();
            visitedNodes++;

            //if we have found the goal state - finish the algorithm
            if (curr.equals(end)) {
                foundSolution = true;
                break;
            }
            // find all the neighbors of the state
            ArrayList<AState> neighbors = problem.getAllSuccessors(curr);
            for (int i = 0; i < neighbors.size(); ++i) {
                if (!(hsVisited.contains(neighbors.get(i)))) { //check if this neighbor is visited
                    //if not - add this neighbor to the hsVisited hash table
                    addCost(curr, neighbors.get(i));
//                    neighbors.get(i).setSumCost(curr.getSumCost() + neighbors.get(i).getCurrCost());
                    hsVisited.add(neighbors.get(i));
                    neighbors.get(i).setPrevState(curr); //set curr to be this neighbor father
                    queue.add(neighbors.get(i)); //push the neighbor into the queue
                }
            }
        }
        Solution sol = CreateSolution(foundSolution,curr,problem);
        return sol;
    }*/

    @Override
    public void addCost(AState curr, AState neighbor) {
        neighbor.setSumCost(curr.getSumCost() + neighbor.getCurrCost());
    }
}
