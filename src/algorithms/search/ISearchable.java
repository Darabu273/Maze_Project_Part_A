package algorithms.search;

import java.util.ArrayList;

/**
 * interface of Searchable problems
 */
public interface ISearchable {

    //Get the start position of the specific problem
    public abstract AState getStartState();

    //Get the goal position of the specific problem
    public abstract AState getGoalState();

    //Get all progress options, from a given state position
    public abstract ArrayList<AState> getAllSuccessors(AState s);

    public abstract boolean CheckSpecialCostPosition(AState curr , AState successor);
}
