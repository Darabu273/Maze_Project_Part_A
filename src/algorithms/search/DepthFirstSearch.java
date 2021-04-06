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

    @Override
    public void addCost(AState curr, AState neighbor) {
        //remain empty
    }
}


