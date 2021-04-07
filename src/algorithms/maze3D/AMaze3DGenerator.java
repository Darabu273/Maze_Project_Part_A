package algorithms.maze3D;


/**
 * AMaze3DGenerator is like AMazeGenerator abstract class  (just for 3D maze):
 * this class implements IMazeGenerator3D interface
 * generate will remain an abstract method
 *  measureAlgorithmTimeMillis will calculate the time of creating a new 3D Maze (using generate function), by using currentTimeMillis meth
 */
public abstract class AMaze3DGenerator implements IMazeGenerator3D {

    public abstract Maze3D generate(int depth, int row, int column) throws Exception; //create Maze instance

    public long measureAlgorithmTimeMillis(int depth, int row, int column) throws Exception {
        if ((row < 2) || (column < 2 )|| (depth < 2) ){
            throw new Exception("Invalid inputs Maze most be at least 2x2x2");}
        long s_time = System.currentTimeMillis();
        generate(depth ,row , column);
        long e_time =System.currentTimeMillis();
        return (e_time-s_time); //return end time minus start time
    }

}
