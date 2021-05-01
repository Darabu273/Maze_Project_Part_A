package algorithms.search;

import java.io.Serializable;

/**
 * AState represent a specific state of the problem
 * this generic abstract class defines the functions the of every problem-state needs
 */
public abstract class AState implements Serializable {
    private AState prevState; //each Astate will hold the previous state that it came from
    private int SumCost; // cost to arrive to this curr State, from the start
    private int CurrCost; //cost to move to this Astate, from the prev Astate

    public AState() {
        this.prevState = null;
        this.SumCost = 0;
        this.CurrCost = 0;
    }

    //setters & getters
    public int getSumCost() {
        return SumCost;
    }

    public void setSumCost(int cost) {
        this.SumCost = cost;
    }

    public int getCurrCost() {
        return CurrCost;
    }

    public void setCurrCost(int cost) {
        this.CurrCost = cost;
    }

    public AState getPrevState() { return prevState;}

    public void setPrevState(AState father) throws Exception {
        if (father == null)
            throw new Exception("Invalid null input - Astate parameter");
        this.prevState = father;
    }

    @Override
    //compare two AStates
    public abstract boolean equals(Object obj);

    @Override
    //hashCode function, using for Hash structs (HashSet)
    public abstract int hashCode();

    @Override
    public abstract String toString();
}
