package algorithms.search;

/**
 * AState represent a specific state of the problem
 * this generic abstract class defines the functions the of every problem-state needs
 */
public abstract class AState {
    private AState prevState;
    private int cost; // cost to arrive curr State

    public AState() {

        this.prevState = null;
        this.cost = 0;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AState getPrevState() { return prevState;}

    public void setPrevState(AState father) {
        this.prevState = father;
    }

    @Override
    //compare two AStates
    public abstract boolean equals(Object obj);


    @Override
    public abstract String toString();
}
