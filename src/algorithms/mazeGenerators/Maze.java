package algorithms.mazeGenerators;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

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


    //constructor
    public Maze(int rows, int columns) {
        Rows=rows;
        Columns=columns;
        start = null;
        end = null;
        mazeContent = new int[Rows][Columns];
    }

    //constructor - input = maze data (dimensions, content, start & goal position - uncompressed), build a new maze
    //our byte-array format: rows, columns, start position (x-row,y-column), goal position (x,y), maze content (row by row)
    public Maze(byte[] mazeArr) throws Exception {
        //convert the given bytes array to int-array
        int intArr[] = new int[mazeArr.length / 4];
        int offset = 0;
        for(int i = 0; i < intArr.length; i++) {
            intArr[i] = (mazeArr[3 + offset] & 0xFF) | ((mazeArr[2 + offset] & 0xFF) << 8) | ((mazeArr[1 + offset] & 0xFF) << 16) | ((mazeArr[0 + offset] & 0xFF) << 24);
            offset += 4;
        }
        //update all data members with the correct values (coordinate to the bytes values)
        Rows = intArr[0];
        Columns= intArr[1];
        start = new Position(intArr[2], intArr[3]);
        end = new Position(intArr[4], intArr[5]);
        mazeContent = new int[Rows][Columns];
        int Curr = 6;
        for(int i=0; i< Rows; i++){ //update the maze content
            for (int j = 0; j < Columns; j++) {
                mazeContent[i][j] = intArr[Curr];
                Curr++;
            }
        }
    }

    //getter & setter
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

    public void setStartPosition(Position start) throws Exception{
        if (start == null)
            throw new Exception("have to get parameter not null ");
        this.start = start;}

    public Position getGoalPosition() {
        return end;}

    public void setGoalPosition(Position end) throws Exception{
        if (end == null)
            throw new Exception("have to get parameter not null ");
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

    //return byte array which represent the maze data (uncompressed info) - dimensions, content, start & goal position
    //our byte-array format: rows, columns, start position (x-row,y-column), goal position (x,y), maze content (row by row)
    public byte[] toByteArray() throws Exception{
        int intArrSize = 6+(this.Columns*this.Rows);
        int[] MazeValues = new int[intArrSize]; //6 ints for rows,columns, start&goal position x&y + this.Columns*this.Rows for maze content
        MazeValues[0]= Rows;
        MazeValues[1] = Columns;
        MazeValues[2] = getStartPosition().getRowIndex();
        MazeValues[3] = getStartPosition().getColumnIndex();
        MazeValues[4] = getGoalPosition().getRowIndex();
        MazeValues[5] = getGoalPosition().getColumnIndex();
        int Curr = 6;
        for(int i=0; i< Rows; i++){
            for (int j = 0; j < Columns; j++) {
                MazeValues[Curr]= this.mazeContent[i][j];
                Curr++;
            }
        }
        //convert int array to bytes array
        ByteBuffer byteBuffer = ByteBuffer.allocate(intArrSize * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(MazeValues);
        byte[] array = byteBuffer.array(); //the return array
        return array;
    }
}
