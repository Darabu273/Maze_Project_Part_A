package algorithms.search;

public abstract class AState {

    @Override
    public abstract boolean equals(Object obj);

    public abstract Object getStatus();

    @Override
    public abstract String toString();
}
