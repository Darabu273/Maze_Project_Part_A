package algorithms.maze3D;

/**
 * IMaze3DGenerator is an interface, includes the definition of generate,measureAlgorithmTimeMillis methods.
 */

public interface IMaze3DGenerator {
    //create Maze instance
    Maze3D generate(int depth, int row, int column) throws Exception;

    //measureAlgorithmTimeMillis will calculate the time of creating a new 3D Maze
    long measureAlgorithmTimeMillis(int depth, int row, int column) throws Exception;

}
