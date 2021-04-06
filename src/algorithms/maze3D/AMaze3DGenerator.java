package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {

    public abstract Maze3D generate(int depth, int row, int column);

    public long measureAlgorithmTimeMillis(int depth, int row, int column){
        long s_time = System.currentTimeMillis();
        generate(depth ,row , column);
        long e_time =System.currentTimeMillis();
        return (e_time-s_time); //return end time minus start time
    }

}
