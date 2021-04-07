package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) throws Exception{
        if (rows < 2 || columns < 2 )
            throw new Exception("Invalid inputs Maze most be at least 2x2");
        Maze maze = new Maze(rows, columns);
        Position StartPos = new Position(0, 0);
        Position EndPos = new Position(rows-1, columns-1);
        maze.setStartPosition(StartPos); //set the start position of the maze
        maze.setGoalPosition(EndPos); //set the end position of the maze

        return maze;
    }
}
