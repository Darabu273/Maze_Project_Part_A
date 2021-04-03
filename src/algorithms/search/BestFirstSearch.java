package algorithms.search;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch(ISearchable problem) {
        super(problem);
    }

    @Override
    public Solution solve(ISearchable problemS) {
        return null;
    }

    @Override
    protected AState popFromStructure() {
        return null;
    }
}
