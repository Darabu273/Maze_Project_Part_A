package algorithms.mazeGenerators;
import java.util.Stack;
import java.util.Random;
import java.util.ArrayList;

public class MyMazeGenerator extends AMazeGenerator {

    private Stack<Position> stack = new Stack<>();
    private Random Ran = new Random();

    @Override
    public Maze generate(int rows, int columns) {
        Maze myMaze = new Maze(rows, columns); //create an empty maze
        for (int r = 0; r <rows ; r++) { //init to 1 all the cells
            for (int c = 0; c <columns ; c++) {
                myMaze.mazeContent[r][c]= 1;
            }
        }
        boolean [][] VisitedCells = new boolean[rows][columns]; //create an boolean matrix that saved cell as visited or not
        int randomRow = Ran.nextInt((rows)); //select random row number
        Position StartPos = new Position(randomRow, 0);
        myMaze.mazeContent[randomRow][0]= 0; //change selected start position to 0
        myMaze.setStartPosition(StartPos); //set the start position of the maze
        changeStatus(randomRow,0,VisitedCells); //change Start position as visited
        //choose random goal position cell in the maze
        int randomRow_2 = Ran.nextInt((rows)); //select random row number
        Position EndPos = new Position(randomRow_2, (columns-1));
        myMaze.mazeContent[randomRow_2][(columns-1)]= 0; //change selected goal position to 0
        myMaze.setGoalPosition(EndPos); //set the start position of the maze
        changeStatus(randomRow_2,(columns-1),VisitedCells);//mark as visited
        stack.push(StartPos);
        while (!stack.empty()) {
            Position curr = stack.pop();
            ArrayList<Position> neighbors = findNeighbors(curr, myMaze, VisitedCells);
            if (neighbors.size() > 0) {
                stack.push(curr);
                myMaze.mazeContent[curr.getRowIndex()][curr.getColumnIndex()] = 0;
                Position Neighbor = ChooseNeighbor(neighbors); //chosen randomly neighbor
                changeStatus(Neighbor.getRowIndex(),Neighbor.getColumnIndex(), VisitedCells);
                removeWall(curr, Neighbor, VisitedCells, myMaze); //remove the wall & mark as visited
                stack.push(Neighbor);
            }
        }
        //handle odd maze problems - open the walls which surround the goal cell
        for (int r = EndPos.getRowIndex()-1; r < EndPos.getRowIndex()+2; r++) {
            for (int c = EndPos.getColumnIndex()-1; c < EndPos.getColumnIndex()+1; c++) {
                if (pointOnGrid(r,c,myMaze)){
                    myMaze.mazeContent[r][c]= 0; //change selected cell to 0
                    changeStatus(r,c,VisitedCells);
                }
            }}
        return myMaze;
    }

    private boolean Visited(int row, int col ,boolean[][] VisitedCells){// check status cell, if the cell as visited already.
        if (VisitedCells[row][col] == true)
            return true;
        return false;
    }
    private void changeStatus(int row, int col ,boolean[][] VisitedCells){ //change status cell as visited.
        VisitedCells[row][col] = true;
    }


    private Position ChooseNeighbor(ArrayList<Position> neighbors) {
        //can't be null - we check before neighbors is not empty
        int rand= Ran.nextInt(neighbors.size());
        Position Neighbor = neighbors.get(rand);
        neighbors.remove(rand);
        return Neighbor;
    }

    private ArrayList<Position> findNeighbors(Position current, Maze myMaze,boolean [][]VisitedCells) {
        ArrayList<Position> neighbors = new ArrayList<>();
        for (int x = current.getRowIndex()-2; x < current.getRowIndex()+3; x=x+2) {
            for (int y = current.getColumnIndex()-2; y < current.getColumnIndex()+3 ; y=y+2) {
                if (pointOnGrid(x, y, myMaze) && pointNotCorner(current, x, y)
                        && pointNotMyCell(current, x, y) && !(Visited(x,y,VisitedCells))) {
                    neighbors.add(new Position(x, y));
                }
            }
        }
        return neighbors;
    }

    //Remove the wall between the current cell and the chosen cell
    private void removeWall(Position curr, Position neighbor, boolean[][] VisitedCells, Maze myMaze) {
        int currR =curr.getRowIndex();
        int currC = curr.getColumnIndex();
        int neiR =neighbor.getRowIndex();
        int neiC = neighbor.getColumnIndex();
        int wallR;
        int wallC;
        if(currR < neiR){
            wallR = currR+1;
            wallC = currC;}
        else if(currR > neiR){
            wallR = currR-1;
            wallC = currC;}
        else{ //currR = neiR
            wallR = currR;
            if(currC < neiC){
                wallC = currC+1;}
            else{
                wallC = currC-1;}}
        myMaze.mazeContent[wallR][wallC]= 0; //change wall position to 0
        changeStatus(wallR,wallC, VisitedCells); //change wall position to 1 (mark as visited)
    }
    private Boolean pointOnGrid(int x, int y, Maze myMaze) {
        return x >= 0 && y >= 0 && y < myMaze.Columns && x < myMaze.Rows;
    }

    private Boolean pointNotCorner(Position cell, int x, int y) {
        return (y == cell.getColumnIndex() || x == cell.getRowIndex());
    }

    private Boolean pointNotMyCell(Position cell, int x, int y) {
        return !(y == cell.getColumnIndex() && x == cell.getRowIndex());
    }

}
