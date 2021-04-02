package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    public abstract AState getStartState();

    public abstract AState getGoldState();

    public abstract ArrayList<AState> getAllSuccessors(AState s);
}
