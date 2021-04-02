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
    public ArrayList<AState> getAllSuccessors(AState s) {
        ArrayList<AState> successors = new ArrayList<>();
        int row = ((MazeState)s).position.getRowIndex();
        int col = ((MazeState)s).position.getColumnIndex();
        if(col-1 >=0 && col-1 < myMaze.getColumns() && myMaze.getMazeContent()[row][col-1] == 0){successors.add(new MazeState(new Position(row,col-1)));}
        if(col+1 < myMaze.getColumns() && myMaze.getMazeContent()[row][col+1] == 0){successors.add(new MazeState(new Position(row,col+1)));}
        if(row-1>=0 && row-1 < myMaze.getRows() && myMaze.getMazeContent()[row-1][col] == 0){successors.add(new MazeState(new Position(row-1,col)));}
        if(row+1 < myMaze.getRows() && myMaze.getMazeContent()[row+1][col] == 0){successors.add(new MazeState(new Position(row+1,col)));}

            return successors;
        }
    }

