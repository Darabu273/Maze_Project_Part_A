package algorithms.search;
import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    private Stack<Node> stack;

    public DepthFirstSearch(ISearchable problem) {
        super(problem);
        stack = new Stack<Node>();
    }

    @Override
    public Solution solve(ISearchable problem) {





        ArrayList<AState> path = new ArrayList<AState>();
        Solution sol = new Solution(path);
        return sol;
    }

    @Override
    protected AState popFromStructure() {
        visitedNodes++;
        return stack.pop().getValue();
    }

}


