package algorithms.maze3D;


/**
 * AMaze3DGenerator is like AMazeGenerator abstract class  (just for 3D maze):
 * this class implements IMazeGenerator3D interface
 * generate will remain an abstract method
 *  measureAlgorithmTimeMillis will calculate the time of creating a new 3D Maze (using generate function), by using currentTimeMillis meth
 */
public abstract class AMaze3DGenerator implements IMazeGenerator3D {

    public abstract Maze3D generate(int depth, int row, int column);

    public long measureAlgorithmTimeMillis(int depth, int row, int column){
        long s_time = System.currentTimeMillis();
        generate(depth ,row , column);
        long e_time =System.currentTimeMillis();
        return (e_time-s_time); //return end time minus start time
    }

}
