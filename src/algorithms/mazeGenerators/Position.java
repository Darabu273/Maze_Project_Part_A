package algorithms.mazeGenerators;

/**
 * this class will represent a position in the 2D maze,
 * this position have row & column indexes
 */

public class Position {

    private int my_row;
    private int my_col;

    public Position(int row, int column) {
        my_row=row;
        my_col=column;
    }

    //compare two Position objects
    public boolean equals(Position other) {
        return (this.my_row == other.my_row) && (this.my_col == other.my_col);
    }

    public int getRowIndex() {
        return my_row;}

    public int getColumnIndex() {
        return my_col;}


    @Override
    public String toString() {
        return "{" +my_row + "," + my_col+"}";
    }
}