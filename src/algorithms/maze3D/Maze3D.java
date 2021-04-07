package algorithms.maze3D;

/**
 * this class will represent a maze of 3D
 * this class have depths, rows, columns sizes, start position and goal position (the target)
 * moreover, it have a  maze data member, which contains the maze itself ([][][] int array)
 */

public class Maze3D {
    int rows;
    int columns;
    int depths;
    private Position3D start;
    private Position3D end;
    int [][][] maze;

    //constructor
    public Maze3D(int depths,int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.depths = depths;
        this.start = null;
        this.end = null;
        this.maze = new int [depths][rows][columns];
    }

    //getter & setter
    public int getRows() { return rows;}

    public int getColumns() {return columns;}

    public int getDepths() { return depths; }

    public Position3D getStartPosition(){
        return start;
    }
    public Position3D getGoalPosition(){
        return end;
    }

    public void setStartPosition(Position3D start) throws Exception {
        if (start == null)
            throw new Exception("have to get parameter not null ");
        this.start = start;
    }

    public void setGoalPosition(Position3D end) throws Exception{
        if (end == null)
            throw new Exception("have to get parameter not null ");
        this.end = end;
    }

    //return the Map (the maze)
    public int[][][] getMap(){
        return maze;
    }

    public void print() {
        System.out.println("{");
        for (int i = 0; i < depths; i++) { //change all cells to 0/1
            for (int j = 0; j < rows; j++) {
                {
                    System.out.print("{");

                    for (int k = 0; k <columns ; k++) {
                        if(i==start.getDepthIndex() && j ==start.getRowIndex() && k==start.getColumnIndex()){
                            System.out.print("S ");
                        }
                        else if(i==end.getDepthIndex() && j ==end.getRowIndex() && k==end.getColumnIndex()){
                            System.out.print("E ");
                        }
                        else{
                            System.out.print(maze[i][j][k] + " ");
                        }
                    }
                }
                System.out.println("}");
            }
            if (i != depths-1)
                System.out.println("--------------------");
        }
        System.out.println("}");
    }
}
