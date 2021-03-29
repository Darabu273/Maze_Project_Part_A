package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        Position StartPos = new Position(0, 0);
        Position EndPos = new Position(rows-1, columns-1);
        maze.setStartPosition(StartPos); //set the start position of the maze
        maze.setGoalPosition(EndPos); //set the end position of the maze

        return maze;
    }
}
