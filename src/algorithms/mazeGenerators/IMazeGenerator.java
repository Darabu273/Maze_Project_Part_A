package algorithms.mazeGenerators;

/**
 * IMazeGenerator is an interface, includes the definition of generate,measureAlgorithmTimeMillis methods.
 */

public interface IMazeGenerator {

    Maze generate(int rows, int columns); //create Maze instance

    //measureAlgorithmTimeMillis will calculate the time of creating a new 2D Maze
    long measureAlgorithmTimeMillis(int rows, int columns);
}
