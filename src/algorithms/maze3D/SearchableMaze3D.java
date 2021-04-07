package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

/**
 * SearchableMaze3D will represent a 3D-Maze Searchable-problem
 * this class is an Object-Adapter from a 3D-maze instance, to an ISearchable problem
 */

public class SearchableMaze3D implements ISearchable {

    Maze3D myMaze;

    //constructor
    public SearchableMaze3D(Maze3D maze) {
        myMaze = maze;
    }

    //return the start position of the problem
    public AState getStartState() {
        Maze3DState startPos = new Maze3DState(myMaze.getStartPosition());
        return startPos;
    }

    @Override
    //return the goal position of the problem
    public AState getGoalState() {
        Maze3DState endPos = new Maze3DState(myMaze.getGoalPosition());
        return endPos;
    }

    @Override
    //Return all maze progress options, from a given state position
    public ArrayList<AState> getAllSuccessors(AState s) {
        ArrayList<AState> successors = new ArrayList<>();
        int depth = ((Maze3DState) s).position.getDepthIndex();
        int row = ((Maze3DState) s).position.getRowIndex();
        int col = ((Maze3DState) s).position.getColumnIndex();
        //check the neighbors from above, below, right and left, and in and out (depth+1 or depth-1)
        //Check that the neighbor being inspected is within range of the maze, and is not a wall (not equal 1)
        //add to each valid neighbor it's cost - 10 for 'regular' neighbor, 15 for corner neighbor
        //add default curr cost (10), because in this problem we have no diagonal steps
        if (col - 1 >= 0 && myMaze.getMap()[depth][row][col - 1] == 0) {
            Maze3DState Successor = new Maze3DState(new Position3D(depth,row, col - 1));
            Successor.setCurrCost(10);
            successors.add(Successor);
        }
        if (col + 1 < myMaze.getColumns() && myMaze.getMap()[depth][row][col + 1] == 0) {
            Maze3DState Successor = new Maze3DState(new Position3D(depth,row, col + 1));
            Successor.setCurrCost(10);
            successors.add(Successor);
        }
        if (row - 1 >= 0 && myMaze.getMap()[depth][row - 1][col] == 0) {
            Maze3DState Successor = new Maze3DState(new Position3D(depth,row - 1, col));
            Successor.setCurrCost(10);
            successors.add(Successor);

        }
        if (row + 1 < myMaze.getRows() && myMaze.getMap()[depth][row + 1][col] == 0) {
            Maze3DState Successor = new Maze3DState(new Position3D(depth,row + 1, col));
            Successor.setCurrCost(10);
            successors.add(Successor);
        }

        //step on depth forward
        if (depth + 1 < myMaze.getDepths() && myMaze.getMap()[depth+1][row][col] == 0) {
            Maze3DState Successor = new Maze3DState(new Position3D(depth+1,row, col));
            Successor.setCurrCost(10);
            successors.add(Successor);
        }
        //step on depth backward
        if (depth-1 >=0 && myMaze.getMap()[depth-1][row][col] == 0) {
            Maze3DState Successor = new Maze3DState(new Position3D(depth-1,row, col));
            Successor.setCurrCost(10);
            successors.add(Successor);
        }

        return successors;
    }

}
