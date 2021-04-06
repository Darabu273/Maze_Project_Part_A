package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.Objects;

public class Position3D {

    private int my_row;
    private int my_col;
    private int my_depth;

    public Position3D(int my_depth,int my_row, int my_col) {
        this.my_row = my_row;
        this.my_col = my_col;
        this.my_depth = my_depth;
    }

    public int getDepthIndex(){return my_depth;}
    public int getRowIndex(){return my_row;}
    public int getColumnIndex(){return my_col;}

    public boolean equals(Position3D other) {
        return (this.my_row == other.my_row) && (this.my_col == other.my_col) && (this.my_depth == other.my_depth);
    }

    @Override
    public String toString() {
        return "{" +my_depth + "," + my_row + "," + my_col +"}";
    }


}
