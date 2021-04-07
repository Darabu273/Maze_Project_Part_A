package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * SearchableMaze will represent a Maze Searchable-problem
 * this class is an Object-Adapter from a maze instance, to an ISearchable problem
 */

public class SearchableMaze implements ISearchable {
    Maze myMaze;

    //constructor
    public SearchableMaze(Maze maze) throws Exception {
        if (maze == null)
            throw new Exception("Invalid null input");
        myMaze = maze;
    }

    //return the start position of the problem
    public AState getStartState() throws Exception {
        MazeState startPos = new MazeState(myMaze.getStartPosition());
        return startPos;
    }

    @Override
    //return the goal position of the problem
    public AState getGoalState() throws Exception {
        MazeState endPos = new MazeState(myMaze.getGoalPosition());
        return endPos;
    }

    @Override
    //Return all maze progress options, from a given state position
    public ArrayList<AState> getAllSuccessors(AState s) throws Exception {
        if (s == null)
            throw new Exception("Invalid null input");
        ArrayList<AState> successors = new ArrayList<>();
        int row = ((MazeState) s).position.getRowIndex();
        int col = ((MazeState) s).position.getColumnIndex();
        //check the neighbors from above, below, right and left, and diagonal-neighbors
        //Check that the neighbor being inspected is within range of the maze, and is not a wall (not equal 1)
        //add to each valid neighbor it's cost - 10 for 'regular' neighbor, 15 for corner neighbor
        if (col - 1 >= 0 && myMaze.getMazeContent()[row][col - 1] == 0) {
            MazeState Successor = new MazeState(new Position(row, col - 1));
            Successor.setCurrCost(10);
            successors.add(Successor);
        }
        if (col + 1 < myMaze.getColumns() && myMaze.getMazeContent()[row][col + 1] == 0) {
            MazeState Successor = new MazeState(new Position(row, col + 1));
            Successor.setCurrCost(10);
            successors.add(Successor);
        }
        if (row - 1 >= 0 && myMaze.getMazeContent()[row - 1][col] == 0) {
            MazeState Successor = new MazeState(new Position(row - 1, col));
            Successor.setCurrCost(10);
            successors.add(Successor);

        }
        if (row + 1 < myMaze.getRows() && myMaze.getMazeContent()[row + 1][col] == 0) {
            MazeState Successor = new MazeState(new Position(row + 1, col));
            Successor.setCurrCost(10);
            successors.add(Successor);
        }
        //add the neighbors diagonally, which can be reached through one of the non-diagonal neighbors
        if (row - 1 >= 0 && col - 1 >= 0 && myMaze.getMazeContent()[row - 1][col - 1] == 0 &&
                (myMaze.getMazeContent()[row - 1][col] == 0 || myMaze.getMazeContent()[row][col - 1] == 0)) {
            MazeState Successor = new MazeState(new Position(row - 1, col - 1));
            Successor.setCurrCost(15);
            successors.add(Successor);
        }

        if (row + 1 < myMaze.getRows() && col + 1 < myMaze.getColumns() && myMaze.getMazeContent()[row + 1][col + 1] == 0 &&
                (myMaze.getMazeContent()[row + 1][col] == 0 || myMaze.getMazeContent()[row][col + 1] == 0)) {
            MazeState Successor = new MazeState(new Position(row + 1, col + 1));
            Successor.setCurrCost(15);
            successors.add(Successor);
        }

        if (row - 1 >= 0 && col + 1 < myMaze.getColumns() && myMaze.getMazeContent()[row - 1][col + 1] == 0 &&
                (myMaze.getMazeContent()[row - 1][col] == 0 || myMaze.getMazeContent()[row][col + 1] == 0)) {
            MazeState Successor = new MazeState(new Position(row - 1, col + 1));
            Successor.setCurrCost(15);
            successors.add(Successor);
        }

        if (row + 1 < myMaze.getRows() && col - 1 >= 0 && myMaze.getMazeContent()[row + 1][col - 1] == 0 &&
                (myMaze.getMazeContent()[row + 1][col] == 0 || myMaze.getMazeContent()[row][col - 1] == 0)) {
            MazeState Successor = new MazeState(new Position(row + 1, col - 1));
            Successor.setCurrCost(15);
            successors.add(Successor);

        }

        return successors;
    }

}


