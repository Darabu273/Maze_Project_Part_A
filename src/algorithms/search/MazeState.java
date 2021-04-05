package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * will represent an AState of the Maze problem, holds a specific position in the Maze
 */

public class MazeState extends AState{

    Position position;

    //constructor
    public MazeState(Position position) {
        this.position = position;
    }

    @Override
    //compare between two MazeStates
    public boolean equals(Object obj) {
        boolean ans =this.position.equals(((MazeState)obj).position);
        return ans;
    }

    @Override
    //return this MazeState position
    public Object getStatus() {
        return this.position;
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
