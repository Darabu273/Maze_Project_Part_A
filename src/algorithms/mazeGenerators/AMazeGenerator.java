package algorithms.mazeGenerators;

/**
 * AMazeGenerator abstract class, implements IMazeGenerator interface
 * generate will remain an abstract method
 * measureAlgorithmTimeMillis will calculate the time of creating a new Maze (using generate function), by using currentTimeMillis method
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    public abstract Maze generate(int rows, int columns) throws Exception; //create Maze instance

    public long measureAlgorithmTimeMillis(int Rows, int Columns) throws Exception {
        if (Rows < 2 || Columns < 2 )
            throw new Exception("Invalid inputs Maze most be at least 2x2");
        long s_time = System.currentTimeMillis();
        generate(Rows, Columns);
        long e_time =System.currentTimeMillis();
        return (e_time-s_time); //return end time minus start time
    }


}
