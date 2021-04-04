package algorithms.search;

/**
 * AState represent a specific state of the problem
 * this generic abstract class defines the functions the of every problem-state needs
 */
public abstract class AState {
    private AState prevState;

    public AState() {
        this.prevState = null;
    }

    public AState getPrevState() { return prevState;}

    public void setPrevState(AState father) {
        this.prevState = father;
    }

    @Override

    public abstract int hashCode();

    @Override
    //compare two AStates
    public abstract boolean equals(Object obj);

    //get the state itself
    public abstract Object getStatus();

    @Override
    public abstract String toString();
}
