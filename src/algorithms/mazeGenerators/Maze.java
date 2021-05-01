package algorithms.mazeGenerators;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * this class will represent a maze of 2D
 * this class have rows, columns sizes, start position and goal position (the target)
 * moreover, it have a  mazeContent data member, which contains the maze itself ([][] int array)
 */
public class Maze implements Serializable {
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
    public Maze(byte[] mazeArr)  throws Exception {
        //convert the given bytes array to int-array
        int intArr[] = new int[6+ (mazeArr.length-12)];
        int offset = 0;
        //the bytes array using 2-bytes for each value of the meta data of the maze (rows, columns, start position (x-row,y-column), goal position (x,y))
        for(int i = 0; i < 6; i++) {
            intArr[i] = (mazeArr[1 + offset] & 0xFF) | ((mazeArr[0 + offset] & 0xFF) << 8) | ((0 & 0xFF) << 16) | ((0 & 0xFF) << 24);
            offset += 2;
        }
        //the bytes array using 1-bytes for each value of the maze content cells (0/1)
        for(int i = 0; i < (mazeArr.length-12); i++) {
            intArr[i+6] = (int)mazeArr[12+i];
        }
        //update all data members with the correct values (coordinate to the bytes values)
        Rows = intArr[0];
        Columns= intArr[1];
        if (Rows < 2 || Columns < 2 ) //maze must have at least 2 rows and two columns
            throw new Exception("Invalid inputs Maze most be at least 2x2");
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
        int[] MazeValues = new int[6]; //6 ints for rows,columns, start&goal position x&y + this.Columns*this.Rows for maze content
        MazeValues[0]= Rows;
        MazeValues[1] = Columns;
        MazeValues[2] = getStartPosition().getRowIndex();
        MazeValues[3] = getStartPosition().getColumnIndex();
        MazeValues[4] = getGoalPosition().getRowIndex();
        MazeValues[5] = getGoalPosition().getColumnIndex();

        //convert Maze meta data to bytes
        ByteBuffer byteBuffer = ByteBuffer.allocate(24); //start with 4 bytes for the rows,columns, start&goal position values, and one byte for each maze content cell value
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(MazeValues);
        byte[] array = byteBuffer.array(); //the return array

        //add meta data in bytes int into the final-result array
        //change to two bytes for the rows,columns, start&goal position values (because their max value = 1000, can be represented by 2 bytes), and one byte for each maze content cell value
        byte[] MazeInBytes = new byte[12+ (this.Columns*this.Rows)];
        int k = 2;
        for (int i = 0; i < 12; i++) {
            MazeInBytes[i]= array[k];
            k++;
            i++;
            MazeInBytes[i]= array[k];
            k=k+3;
        }

        //add maze content to the final-result array - 1 byte per each cell value
        int Curr = 12;
        for(int i=0; i< Rows; i++){
            for (int j = 0; j < Columns; j++) {
                MazeInBytes[Curr]= (byte)this.mazeContent[i][j];
                Curr++;
            }
        }
        return MazeInBytes;
    }
}
