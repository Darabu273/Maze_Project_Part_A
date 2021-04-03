package algorithms.search;

public interface ISearchingAlgorithm {

    public int getNumberOfNodesEvaluated();
    public String getName();
    public Solution solve(ISearchable problemS);

}
