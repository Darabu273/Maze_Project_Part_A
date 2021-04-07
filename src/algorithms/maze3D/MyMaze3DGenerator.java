package algorithms.maze3D;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * this class extends the AMaze3DGenerator abstract class
 * this class generate the 3D maze
 * it contains stack
 */
public class MyMaze3DGenerator extends AMaze3DGenerator{
    private Stack<Position3D> stack = new Stack<>();
    private Random Ran = new Random();

    //generate the Maze itself
    public Maze3D generate(int depth, int row, int column) {
        Maze3D myMaze = new Maze3D(depth,row, column); //create an empty maze
        for (int r = 0; r <row ; r++) { //init to 1 all the cells
            for (int c = 0; c <column ; c++) {
                for (int d = 0; d < depth; d++) {
                    myMaze.maze[d][r][c]= 1;

                }
            }
        }
        boolean [][][] VisitedCells = new boolean[depth][row][column]; //create an boolean matrix that saved cell as visited or not
        int randomDepth = Ran.nextInt((depth)); //select random depth number
        int randomRow = Ran.nextInt((row)); //select random row number
        Position3D StartPos = new Position3D(randomDepth,randomRow, 0);
        myMaze.maze[randomDepth][randomRow][0]= 0; //change selected start position to 0
        myMaze.setStartPosition(StartPos); //set the start position of the maze
        changeStatus(randomDepth,randomRow,0,VisitedCells); //change Start position as visited
        //choose random goal position cell in the maze
        int randomDepth_2 = Ran.nextInt((depth)); //select random depth number
        int randomRow_2 = Ran.nextInt((row)); //select random row number
        Position3D EndPos = new Position3D(randomDepth_2,randomRow_2, (column-1));
        myMaze.maze[randomDepth_2][randomRow_2][(column-1)]= 0; //change selected goal position to 0
        myMaze.setGoalPosition(EndPos); //set the start position of the maze
        changeStatus(randomDepth_2,randomRow_2,(column-1),VisitedCells);//mark as visited
        stack.push(StartPos);
        while (!stack.empty()) {
            Position3D curr = stack.pop();
            //check if the current cell have any neighbors which have not been visited, and find them all
            ArrayList<Position3D> neighbors = findNeighbors(curr, myMaze, VisitedCells); //find all options of moving forward
            if (neighbors.size() > 0) { //iterate over all neighbors
                stack.push(curr); //push curr into the stack
                myMaze.maze[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()] = 0; //change it's place to 1
                Position3D Neighbor = ChooseNeighbor(neighbors); //choose one unvisited random neighbor
                changeStatus(Neighbor.getDepthIndex(),Neighbor.getRowIndex(),Neighbor.getColumnIndex(), VisitedCells); //mark him as visited neighbor
                removeWall(curr, Neighbor, VisitedCells, myMaze); //remove the wall & mark as visited
                stack.push(Neighbor);
            }
        }
        //handle odd maze problems - open the walls which surround the goal cell
        for (int d = EndPos.getDepthIndex()-1; d < EndPos.getDepthIndex()+2; d++) {
            for (int r = EndPos.getRowIndex()-1; r < EndPos.getRowIndex()+2; r++) {
                for (int c = EndPos.getColumnIndex()-1; c < EndPos.getColumnIndex()+1; c++) {
                    if (pointOnGrid(d,r,c,myMaze)){
                        myMaze.maze[d][r][c]= 0; //change selected cell to 0
                        changeStatus(d,r,c,VisitedCells);
                    }
                }}
        }
        return myMaze;
    }

    private boolean Visited(int depth, int row, int col ,boolean[][][] VisitedCells){// check status cell, if the cell as visited already.
        if (VisitedCells[depth][row][col] == true)
            return true;
        return false;
    }
    private void changeStatus(int depth, int row, int col ,boolean[][][] VisitedCells){ //change status cell as visited.
        VisitedCells[depth][row][col] = true;
    }


    private Position3D ChooseNeighbor(ArrayList<Position3D> neighbors) {
        //can't be null - we check before neighbors is not empty
        int rand= Ran.nextInt(neighbors.size());
        Position3D Neighbor = neighbors.get(rand);
        neighbors.remove(rand);
        return Neighbor;
    }

    //find all the unvisited neighbors of the current position
    private ArrayList<Position3D> findNeighbors(Position3D current, Maze3D myMaze,boolean [][][]VisitedCells) {
        ArrayList<Position3D> neighbors = new ArrayList<>();
        //for each neighbor, add him into the neighbors array, if it's on the grid (maze), if it's not a corner, not the current position itself, and it's unvisited
        //iterate over all neighbors in the same depth
        for (int x = current.getRowIndex()-2; x < current.getRowIndex()+3; x=x+2) {
            for (int y = current.getColumnIndex()-2; y < current.getColumnIndex()+3 ; y=y+2) {
                if (pointOnGrid(current.getDepthIndex(),x, y, myMaze) && pointNotCorner(current ,current.getDepthIndex(), x, y)
                        && pointNotMyCell(current,current.getDepthIndex(), x, y) && !(Visited(current.getDepthIndex(),x,y,VisitedCells))) {
                    neighbors.add(new Position3D(current.getDepthIndex(),x, y));
                }
            }
        }
        //check the options from the other depths

        if (pointOnGrid(current.getDepthIndex()+2,current.getRowIndex(), current.getColumnIndex(), myMaze) && !(Visited(current.getDepthIndex()+2,current.getRowIndex(),current.getColumnIndex(),VisitedCells))){
            neighbors.add(new Position3D(current.getDepthIndex()+2,current.getRowIndex(), current.getColumnIndex()));
        }
        if (pointOnGrid(current.getDepthIndex()-2,current.getRowIndex(), current.getColumnIndex(), myMaze) && !(Visited(current.getDepthIndex()-2,current.getRowIndex(),current.getColumnIndex(),VisitedCells))){
            neighbors.add(new Position3D(current.getDepthIndex()-2,current.getRowIndex(), current.getColumnIndex()));
        }
        return neighbors;
    }

    //Remove the wall between the current cell and the chosen cell
    private void removeWall(Position3D curr, Position3D neighbor, boolean[][][] VisitedCells, Maze3D myMaze) {
        int currR =curr.getRowIndex();
        int currC = curr.getColumnIndex();
        int currD =curr.getDepthIndex();
        int neiR =neighbor.getRowIndex();
        int neiC = neighbor.getColumnIndex();
        int neiD = neighbor.getDepthIndex();
        int wallR;
        int wallC;
        int wallD;
        //check the relative position of the neighbor to the current, and find the wall (position) that we need to break
        if(currR < neiR){
            wallD = currD;
            wallR = currR+1;
            wallC = currC;}
        else if(currR > neiR){
            wallD = currD;
            wallR = currR-1;
            wallC = currC;}
        else{// currR = neiR
            wallR = currR;
            if (currC == neiC){
                wallC = currC;
                if(currD < neiD){
                    wallD = currD+1;}
                else{
                    wallD = currD-1;}}
            else{
                wallD = currD;
                if(currC < neiC){
                    wallC = currC+1;}
                else{
                    wallC = currC-1;}}
            }

        myMaze.maze[wallD][wallR][wallC]= 0; //change wall position to 0 (break wall)
        changeStatus(wallD,wallR,wallC, VisitedCells); //change wall position to 1 (mark as visited)
    }

    //check if this position is on the grid of the maze
    private Boolean pointOnGrid(int d,int r, int c, Maze3D myMaze) {
        return (d >= 0 && r >= 0 && c >= 0) && (d<myMaze.depths && c < myMaze.columns && r < myMaze.rows);
    }

    //check that this given position is not a corner (of the current position)
    private Boolean pointNotCorner(Position3D cell,int d, int r, int c) {

        return d == cell.getDepthIndex() && (r == cell.getRowIndex() || c == cell.getColumnIndex());
    }

    //check that this given position is not a the same as the current position
    private Boolean pointNotMyCell(Position3D cell,int d, int r, int c) {
        return !(d == cell.getDepthIndex() && c == cell.getColumnIndex() && r == cell.getRowIndex());
    }

}
