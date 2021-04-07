package algorithms.maze3D;

/**
 * IMazeGenerator3D is an interface, includes the definition of generate,measureAlgorithmTimeMillis methods.
 */

public interface IMazeGenerator3D {

    Maze3D generate(int depth, int row, int column);
    long measureAlgorithmTimeMillis(int depth, int row, int column);

}
