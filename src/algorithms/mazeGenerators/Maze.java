package algorithms.mazeGenerators;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * this class will represent a maze of 2D
 * this class have rows, columns sizes, start position and goal position (the target)
 * moreover, it have a  mazeContent data member, which contains the maze itself ([][] int array)
 */
public class Maze {
    int Rows;
    int Columns;
    private Position start;
    private Position end;
    int [][] mazeContent;

    public Maze(int rows, int columns) {
        Rows=rows;
        Columns=columns;
        start = null;
        end = null;
        mazeContent = new int[Rows][Columns];
    }

    public int getRows() {
        return Rows;
    }
    public int getColumns() {
        return Columns;
    }
    public int[][] getMazeContent(){
        return mazeContent;
    }

    public Position getStartPosition() {
        return start;}

    public void setStartPosition(Position start) {
        this.start = start;}

    public Position getGoalPosition() {
        return end;}

    public void setGoalPosition(Position end) {
        this.end = end;}

    public void print() {
        for (int i = 0; i < Rows; i++) { //change all cells to 0/1
            System.out.print("{ ");
            for (int j = 0; j < Columns; j++) {
                if(i==start.getRowIndex() && j ==start.getColumnIndex()){
                    System.out.print("S ");
                }
                else if(i==end.getRowIndex() && j ==end.getColumnIndex()){
                    System.out.print("E ");
                }
                else{
                    System.out.print(mazeContent[i][j] + " ");
                }
            }
            System.out.println("}");
        }
    }
}
