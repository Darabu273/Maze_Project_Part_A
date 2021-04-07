package algorithms.maze3D;

/**
 * this class will represent a position in the 3D maze,
 * this position have row, column and depth indexes
 */
public class Position3D {

    private int my_row;
    private int my_col;
    private int my_depth;

    //constructor
    public Position3D(int my_depth,int my_row, int my_col) throws Exception{
        if (my_row < 0 || my_col < 0 || my_depth < 0 )
            throw new Exception("Invalid inputs 3D-Maze position can not be negative ");
        this.my_row = my_row;
        this.my_col = my_col;
        this.my_depth = my_depth;
    }

    //getters
    public int getDepthIndex(){return my_depth;}
    public int getRowIndex(){return my_row;}
    public int getColumnIndex(){return my_col;}

    //compare two Position3D
    public boolean equals(Position3D other){
        return (this.my_row == other.my_row) && (this.my_col == other.my_col) && (this.my_depth == other.my_depth);
    }

    @Override
    public String toString() {
        return "{" +my_depth + "," + my_row + "," + my_col +"}";
    }


}
