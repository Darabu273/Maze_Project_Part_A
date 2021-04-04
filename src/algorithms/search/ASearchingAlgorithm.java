package algorithms.search;

import java.util.*;

/**
 * ASearchingAlgorithm implements ISearchingAlgorithm
 * this abstract class will hold the functions that every ISearchingAlgorithm needs, and implement some of them
 * visitedNodes: counter for number of neighbors which developed
 * search: the search problem (Bridge design pattern)
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
        protected int visitedNodes;
        ISearchable problem;

        //constructor
        public ASearchingAlgorithm() {
                this.visitedNodes = 0 ;
        }

        @Override
        //Number of neighbors which developed
        public int getNumberOfNodesEvaluated() {
                return visitedNodes;
        }

        @Override
        //Get the name of the algorithm
        public String getName(){
               return this.getClass().getSimpleName();
        }

        @Override
        //Solve the problem, return the Solution
        public abstract Solution solve(ISearchable problemS);

        //pop the next AState from the Stack, return this AState, and add 1 to the visitedNodes counter
        protected abstract AState popFromStructure();

}


