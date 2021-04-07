package algorithms.maze3D;

/**
 * IMazeGenerator3D is an interface, includes the definition of generate,measureAlgorithmTimeMillis methods.
 */

public interface IMazeGenerator3D {

    //create Maze instance
    Maze3D generate(int depth, int row, int column);

    //measureAlgorithmTimeMillis will calculate the time of creating a new 3D Maze
    long measureAlgorithmTimeMillis(int depth, int row, int column);

}
