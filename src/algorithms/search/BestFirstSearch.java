package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    Comparator<AState> stateSorter = Comparator.comparing(AState::getSumCost);

    public BestFirstSearch() {
        struct = new PriorityQueue<AState>(stateSorter);
    }

    @Override
    public void addCost(AState curr, AState neighbor) {
        neighbor.setSumCost(curr.getSumCost() + neighbor.getCurrCost());
    }
}
