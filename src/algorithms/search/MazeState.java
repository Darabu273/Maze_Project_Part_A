package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    Position position;

    public MazeState(Position position) {
        this.position = position;
    }

    @Override
        public boolean equals(Object obj) {
        boolean ans =this.position.equals(((MazeState)obj).position);
        return ans;
    }
    @Override
    public Object getStatus() {
        return this.position;
    }
    @Override
    public String toString() {
        return position.toString();
    }
}
