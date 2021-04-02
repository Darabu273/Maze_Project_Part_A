package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;


public class SearchableMaze implements ISearchable{
    Maze myMaze;

    public SearchableMaze(Maze maze) {
        myMaze = maze;
    }

    public AState getStartState() {
        MazeState startPos = new MazeState(myMaze.getStartPosition());
        return startPos;
    }

    @Override
    public AState getGoldState() {
        MazeState endPos = new MazeState(myMaze.getGoalPosition());
        return endPos;
    }
    @Override
    //Return all maze progress options, from a given state position
    public ArrayList<AState> getAllSuccessors(AState s) {
        ArrayList<AState> successors = new ArrayList<>();
        int row = ((MazeState)s).position.getRowIndex();
        int col = ((MazeState)s).position.getColumnIndex();
        //check the neighbors from above, below, right and left
        //Check that the neighbor being inspected is within range of the maze, and is not a wall (not equal 1)
        if(col-1 >=0 && myMaze.getMazeContent()[row][col-1] == 0){successors.add(new MazeState(new Position(row,col-1)));}
        if(col+1 < myMaze.getColumns() && myMaze.getMazeContent()[row][col+1] == 0){successors.add(new MazeState(new Position(row,col+1)));}
        if(row-1>=0 && myMaze.getMazeContent()[row-1][col] == 0){successors.add(new MazeState(new Position(row-1,col)));}
        if(row+1 < myMaze.getRows() && myMaze.getMazeContent()[row+1][col] == 0){successors.add(new MazeState(new Position(row+1,col)));}
        //add the neighbors diagonally, which can be reached through one of the non-diagonal neighbors
        if(row-1>=0 && col-1 >=0 && myMaze.getMazeContent()[row-1][col-1] == 0 &&
                (myMaze.getMazeContent()[row-1][col] == 0 || myMaze.getMazeContent()[row][col-1] == 0)){successors.add(new MazeState(new Position(row-1,col-1)));}

        if(row+1 < myMaze.getRows() && col+1 < myMaze.getColumns() && myMaze.getMazeContent()[row+1][col+1] == 0 &&
                (myMaze.getMazeContent()[row+1][col] == 0 || myMaze.getMazeContent()[row][col+1] == 0)){successors.add(new MazeState(new Position(row+1,col+1)));}

        if(row-1>=0 && col+1 < myMaze.getColumns() && myMaze.getMazeContent()[row-1][col+1] == 0 &&
                (myMaze.getMazeContent()[row-1][col] == 0 || myMaze.getMazeContent()[row][col+1] == 0)){successors.add(new MazeState(new Position(row-1,col+1)));}

        if(row+1 < myMaze.getRows() && col-1 >=0 && myMaze.getMazeContent()[row+1][col-1] == 0 &&
                (myMaze.getMazeContent()[row+1][col] == 0 || myMaze.getMazeContent()[row][col-1] == 0)){successors.add(new MazeState(new Position(row+1,col-1)));}

        return successors;
        }
    }

