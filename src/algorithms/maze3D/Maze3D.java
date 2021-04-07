package algorithms.maze3D;

public class Maze3D {
    int rows;
    int columns;
    int depths;
    private Position3D start;
    private Position3D end;
    int [][][] maze;

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

    public void setStartPosition(Position3D start) {
        this.start = start;
    }

    public void setGoalPosition(Position3D end) {
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
