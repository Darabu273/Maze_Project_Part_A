package algorithms.search;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{


    public BestFirstSearch() {
        queue = new PriorityQueue<AState>();
    }

    @Override
    public Solution solve(ISearchable problemS) {
        return null;
    }

}
