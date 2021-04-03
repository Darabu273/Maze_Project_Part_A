package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
        protected int visitedNodes;
        ISearchable search;

        public ASearchingAlgorithm(ISearchable search) {
                this.visitedNodes = 0 ;
                this.search = search;
        }
        @Override
        public int getNumberOfNodesEvaluated() {
                return visitedNodes;
        }
        @Override
        //todo: check function getSimple
        public String getName(){
               return this.getClass().getSimpleName();
        }

        @Override
        public abstract Solution solve(ISearchable problemS);

        protected abstract AState popFromStructure();

}


