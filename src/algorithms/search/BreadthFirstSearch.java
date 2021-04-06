package algorithms.search;

import java.util.*;

/**
 * BFS Algorithm - one option of SearchingAlgorithm
 * holds queue of nodes
 */

public class BreadthFirstSearch extends ASearchingAlgorithm{

    //constructor - BFS based on queue (LinkedList)
    public BreadthFirstSearch() {
        struct = new LinkedList<AState>();}

    @Override
    //insert into the queue (implement the abstract method of ASearchingAlgorithm)
    public void InsertStruct(Object queue, AState objectToInsert){
        ((Queue<AState>)queue).add(objectToInsert);
    }

    @Override
    //check if the queue is empty (implement the abstract method of ASearchingAlgorithm)
    public boolean IsEmptyStruct(Object queue) {
        return ((Queue<AState>)queue).isEmpty();
    }

    @Override
    //remove element from the queue (implement the abstract method of ASearchingAlgorithm)
    public AState removeFromStruct(Object queue) {
        AState poped = ((Queue<AState>)queue).poll();
        return poped;
    }

    @Override
    //add cost for the given neighbor Astate- (implement only for the Best algorithm, using for polymorphism)
    public void addCost(AState curr, AState neighbor) {
        //remain empty
    }

}
