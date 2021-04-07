package algorithms.search;

import java.util.*;

/**
 * DFS Algorithm - one option of SearchingAlgorithm
 * holds stack of Asates
 */

public class DepthFirstSearch extends ASearchingAlgorithm {

    //constructor - DFS based on stack
    public DepthFirstSearch() {
        struct = new Stack<AState>();}

    @Override
    //insert into the stack (implement the abstract method of ASearchingAlgorithm)
    public void InsertStruct(Object stack, AState objectToInsert){
        ((Stack<AState>)stack).push(objectToInsert);
    }

    @Override
    //check if the stack is empty (implement the abstract method of ASearchingAlgorithm)
    public boolean IsEmptyStruct(Object stack) {
        return ((Stack<AState>)stack).empty();
    }

    @Override
    //remove element from the stack (implement the abstract method of ASearchingAlgorithm)
    public AState removeFromStruct(Object stack) {
        AState poped = ((Stack<AState>)stack).pop();
        return poped;
    }

    @Override
    //add cost for the given neighbor Astate- (implement only for the Best algorithm, using for polymorphism)
    public void addCost(AState curr, AState neighbor) {
        //remain empty
    }
}


