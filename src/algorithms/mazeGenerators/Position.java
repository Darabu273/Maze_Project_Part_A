package algorithms.mazeGenerators;

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

    public void setRowIndex(int my_row) {
        this.my_row = my_row;}

    public int getColumnIndex() {
        return my_col;}

    public void setColumnIndex(int my_col) {
        this.my_col = my_col;}

    @Override
    public String toString() {
        return "{" +my_row + "," + my_col+"}";
    }
}