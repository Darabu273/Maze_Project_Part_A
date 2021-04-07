package algorithms.search;

import java.util.*;

/**
 * Best Algorithm - one option of SearchingAlgorithm , extends BFS
 * holds PriorityQueue of Asates
 */

public class BestFirstSearch extends BreadthFirstSearch{

    //Comparator - using for sort element in the Comparator Priority Queue
    Comparator<AState> stateSorter = Comparator.comparing(AState::getSumCost);

    //constructor - BEST based on Priority Queue
    public BestFirstSearch() {
        struct = new PriorityQueue<AState>(stateSorter);
    }

    @Override
    //add cost for the given neighbor Astate- (implement only for the Best algorithm, using for polymorphism)
    public void addCost(AState curr, AState neighbor) throws Exception{
        if (curr == null || neighbor == null)
            throw new Exception("Invalid parameters will be current Astate or neighbor Astate");
        neighbor.setSumCost(curr.getSumCost() + neighbor.getCurrCost());
    }
}
