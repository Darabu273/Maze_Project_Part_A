package algorithms.mazeGenerators;

/**
 * AMazeGenerator abstract class, implements IMazeGenerator interface
 * generate will remain an abstract method
 * measureAlgorithmTimeMillis will calculate the time of creating a new Maze (using generate function), by using currentTimeMillis method
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    public abstract Maze generate(int rows, int columns); //create Maze instance

    public long measureAlgorithmTimeMillis(int Rows, int Columns){
        long s_time = System.currentTimeMillis();
        generate(Rows, Columns);
        long e_time =System.currentTimeMillis();
        return (e_time-s_time); //return end time minus start time
    }


}
