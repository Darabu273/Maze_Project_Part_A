package algorithms.mazeGenerators;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

public class MyMazeGenerator extends AMazeGenerator  {
    private Stack<Position> stack = new Stack<>();
    private Random Ran = new Random();

    @Override
    //iterative DFS
    public Maze generate(int rows, int columns) {
        Maze myMaze = new Maze(rows, columns); //create an empty maze
        //change all cells to 1
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                myMaze.mazeContent[i][j]= 1; }}
        //create a matrix of visited marks (0 = unvisited, 1=visited)
        int [][] visitedMat = new int[rows][columns];
        //choose random start position cell in the maze
        int randomRow = Ran.nextInt((rows-1)); //select random row number
        Position StartPos = new Position(randomRow, 0);
        myMaze.mazeContent[randomRow][0]= 0; //change selected start position to 0
        myMaze.setStartPosition(StartPos); //set the start position of the maze
        visitedMat[randomRow][0]=1; //mark as visited
        //choose random goal position cell in the maze
        int randomRow_2 = Ran.nextInt((rows-1)); //select random row number
        Position EndPos = new Position(randomRow_2, (columns-1));
        myMaze.mazeContent[randomRow_2][(columns-1)]= 0; //change selected goal position to 0
        myMaze.setGoalPosition(EndPos); //set the start position of the maze
        visitedMat[randomRow_2][(columns-1)]=1; //mark as visited
        //building the maze
        stack.push(StartPos); //push start position into the stack
        while (!stack.empty()) {
            Position curr = stack.pop();
            //check if the current cell has any neighbours which have not been visited, and Choose one of the unvisited neighbours
            Position Neighbor = ChooseNeighbors(curr, visitedMat, rows, columns);
            if(Neighbor != null){
                stack.push(curr); //push curr position into the stack
                myMaze.mazeContent[Neighbor.getRowIndex()][Neighbor.getColumnIndex()]= 0; //change selected Neighbor position to 0
                visitedMat[Neighbor.getRowIndex()][Neighbor.getColumnIndex()]= 1; //change selected Neighbor position to 1 (mark as visited)
                removeWall(curr, Neighbor, visitedMat, myMaze);
                stack.push(Neighbor); //push Neighbor into the stack
            }
        }

        //handle odd maze problems - open the walls which surround the goal cell
        for (int r = EndPos.getRowIndex()-1; r < EndPos.getRowIndex()+2; r++) {
            for (int c = EndPos.getColumnIndex()-1; c < EndPos.getColumnIndex()+1; c++) {
                if (r >= 0 && r < rows && c >= 0 && c < columns){
                    myMaze.mazeContent[r][c]= 0; //change selected cell to 0
                }
            }}
        return myMaze;
    }

    //Remove the wall between the current cell and the chosen cell
    private void removeWall(Position curr, Position neighbor, int[][] visitedMat, Maze myMaze) {
        int currR =curr.getRowIndex();
        int currC = curr.getColumnIndex();
        int neiR =neighbor.getRowIndex();
        int neiC = neighbor.getColumnIndex();
        int wallR;
        int wallC;
        if(currR<neiR){
            wallR=currR+1;
            wallC = currC;}
        else if(currR>neiR){
            wallR=currR-1;
            wallC = currC;}
        else{ //currR=neiR
            wallR = currR;
            if(currC<neiC){
                wallC = currC+1;}
            else{
                wallC = currC-1;}}
        myMaze.mazeContent[wallR][wallC]= 0; //change wall position to 0
        visitedMat[wallR][wallC]= 1; //change wall position to 1 (mark as visited)
    }

    //find if the given cell have unvisited-Neighbors
    //Choose one of the unvisited neighbours of the current Cell
    private Position ChooseNeighbors(Position cell, int[][] visited, int rows, int columns) {
        int currR =cell.getRowIndex();
        int currC = cell.getColumnIndex();
        ArrayList<Position> neighbors = new ArrayList<>(); //create list of all unvisited neighbors
        for (int r = currR-2; r < currR+3; r++) {
            for (int c = currC-2; c < currC+3; c++) {
                //make sure that this position isn't equal to the cell itself, isn't a corner,
                //isn't outside the maze, isn't visited
                if (Check4Ways(r,c,cell) && r >= 0 && r < rows && c >= 0 && c < columns && visited[r][c] == 0) {
                    neighbors.add(new Position(r, c));}}}
        if(neighbors.size()>0){
            int rand = Ran.nextInt(neighbors.size());
            Position chosen = neighbors.get(rand);
            return chosen;
        }
        return null;
    }

    //make sure that the given index is a valid Neighbor (up/down/left/right with one space between)
    private boolean Check4Ways(int row, int col, Position Curr){
        int currR =Curr.getRowIndex();
        int currC = Curr.getColumnIndex();
        if(row==currR && col==(currC-2)){return true;}
        else if(row==currR && col==(currC+2)){return true;}
        else if(col==currC && row==(currR-2)){return true;}
        else if(col==currC && row==(currR+2)){return true;}
        return false;
    }

}