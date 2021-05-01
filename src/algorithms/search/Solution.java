package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Solution class will represent the Solution of the problem
 * solution ArrayList will hold the AStates of the Solution.
 *
 */

public class Solution implements Serializable {
    private ArrayList<AState> solution;

    //constructor
    public Solution(ArrayList<AState> sol) {
        this.solution = sol;
    }

    //return the solution path
    public ArrayList<AState> getSolutionPath(){
        return solution;
    }
}
