package algorithms.search;

/**
 * interface of SearchingAlgorithm for problem-solvers
 */
public interface ISearchingAlgorithm {

    //Number of neighbors which developed
    public int getNumberOfNodesEvaluated();

    //Get the name of the algorithm
    public String getName();

    //Solve the problem, return the Solution
    public Solution solve(ISearchable problem);

}
