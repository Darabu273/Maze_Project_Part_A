package algorithms.maze3D;

import algorithms.search.AState;

import java.util.Objects;

/**
 * will represent an AState of the 3D-Maze problem, holds a specific 3D-position in the Maze
 */

public class Maze3DState extends AState {

    Position3D position;

    //constructor
    public Maze3DState(Position3D position) {
        this.position = position;
    }

    @Override
    //compare between two Maze3DState
    public boolean equals(Object obj) {
        boolean ans =this.position.equals(((Maze3DState)obj).position);
        return ans;
    }

    @Override
    public String toString() {
        return position.toString();
    }

    @Override
    //hashCode function, using for Hash structs (HashSet)
    public int hashCode() {
        return Objects.hash(position.getDepthIndex(),position.getRowIndex(), position.getColumnIndex());
    }

}
