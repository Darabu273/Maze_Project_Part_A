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
        protected Object struct;


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

        //abstract methode - insert into the Structure of the states
        public abstract void InsertStruct(Object struct, AState objectToInsert);

        public abstract boolean IsEmptyStruct(Object struct);

        public abstract AState removeFromStruct(Object struct);

        public abstract void addCost(AState curr, AState neighbor);

        public Solution CreateSolution(boolean foundSolution, AState curr,ISearchable problem){
                if(!foundSolution || curr == null){return null;} //todo: except?
                //return the solution path which has been found - reverse the path of the problem
                ArrayList<AState> path = new ArrayList<AState>();
                while(!curr.equals(problem.getStartState())){
                        path.add(0,curr);
                        curr = curr.getPrevState(); }
                path.add(0,problem.getStartState());
                //create a Solution instance from this path
                Solution sol = new Solution(path);
                return sol;
        }

        @Override
        //Solve the problem, return the Solution
        public Solution solve(ISearchable problem){
                AState start = problem.getStartState();
                AState end = problem.getGoalState();
                //HashSet will saved visited AStates (we have seen those states while solving this problem)
                HashSet<AState> hsVisited = new HashSet<AState>();
                //the start Astate will mark as visit, and be pushed into the stack
                hsVisited.add(start);
                InsertStruct(struct,start);
                AState curr = null;
                boolean foundSolution = false; //will be true if we have found a solution //todo: add except
                while (!IsEmptyStruct(struct))
                {
                        // curr = the top Astate of the stack
                        //pop the next node from the Stack, return this Node, and add 1 to the visitedNodes counter
                        curr = removeFromStruct(struct);
                        visitedNodes++;
                        //if we have found the goal state - finish the algorithm
                        if (curr.equals(end)){
                                foundSolution = true;
                                break;}
                        // find all the neighbors of the state
                        ArrayList<AState> neighbors = problem.getAllSuccessors(curr);
                        for (int i = 0; i < neighbors.size(); ++i)
                        {
                                if (!(hsVisited.contains(neighbors.get(i)))) { //check if this neighbor is visited
                                        //if not - add this neighbor to the hsVisited hash table
                                        addCost(curr, neighbors.get(i));
                                        hsVisited.add(neighbors.get(i));
                                        neighbors.get(i).setPrevState(curr); //set curr to be this neighbor father
                                        InsertStruct(struct,neighbors.get(i)); //push the neighbor into the stack
                                }
                        }
                }

                Solution sol = CreateSolution(foundSolution,curr,problem);
                return sol;
        }


}


