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

    @Override
    public void addCost(AState curr, AState neighbor) {
        //remain empty
    }

}
