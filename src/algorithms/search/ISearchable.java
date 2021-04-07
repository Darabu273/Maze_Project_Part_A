package algorithms.search;

import java.util.ArrayList;

/**
 * interface of Searchable problems
 */
public interface ISearchable {

    //Get the start position of the specific problem
    public abstract AState getStartState() throws Exception;

    //Get the goal position of the specific problem
    public abstract AState getGoalState() throws Exception;

    //Get all progress options, from a given state position
    public abstract ArrayList<AState> getAllSuccessors(AState s) throws Exception;

}
