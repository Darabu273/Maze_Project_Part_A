package algorithms.mazeGenerators;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{

    @Override
    //generate a simple maze
    public Maze generate(int rows, int columns) {
        Maze simpleMaze = new Maze(rows, columns); //create an empty maze

        //add randomly 0 and 1 to all the unchanged cells (remain 2)
        Random Ran = new Random();
        for (int i = 0; i < rows; i++) { //change all cells to 0/1
            for (int j = 0; j < columns; j++) {
                int randomInt = Ran.nextInt(2); //select random 0/1 number
                simpleMaze.mazeContent[i][j]= randomInt; }}

        //choose random start position cell in the maze
        Position StartPos = choosePosition(rows, columns);
        simpleMaze.mazeContent[StartPos.getRowIndex()][StartPos.getColumnIndex()]= 0; //change selected start position to 0
        simpleMaze.setStartPosition(StartPos); //set the start position of the maze
        //choose random end position cell in the maze
        Position EndPos = choosePosition(rows, columns);
        //make sure that StartPos != EndPos
        while (StartPos.equals(EndPos)){
            EndPos = choosePosition(rows, columns);}
        simpleMaze.mazeContent[EndPos.getRowIndex()][EndPos.getColumnIndex()]= 0; //change selected end position to 0
        simpleMaze.setGoalPosition(EndPos); //set the end position of the maze

        //create one solution (at least) for the maze
        int currR = StartPos.getRowIndex();
        int currC = StartPos.getColumnIndex();

        BuildSolutionByCol(currC, EndPos.getColumnIndex(),simpleMaze);
        BuildSolutionByRow(currR, EndPos.getRowIndex(), simpleMaze);

        return simpleMaze;
    }

    //this function will choose random position in the maze
    public Position choosePosition(int rows, int columns) {
        Random Ran = new Random();
        int randomRow = Ran.nextInt((rows-1)); //select random row number
        int randomCol;
        if(randomRow== 0 || randomRow==(rows-1)){ //if the chosen row is an edge row
            randomCol = Ran.nextInt((columns-1)); }//select random column number
        else { //if the chosen row isn't an edge row: we can choose only edge-columns
            double chooseHalf= Math.random(); //chose randomly: 0 / 1 , by using Math.random() function
            if(chooseHalf<0.4){
                randomCol=0;}
            else{
                randomCol=(columns-1);}}
        return new Position(randomRow,randomCol); //create the new random-chosen position, and return it
    }

    //help function  - change currC accordingly  to EndCol value
    public void BuildSolutionByCol(int currC , int EndCol, Maze maze) {
        while (!(currC==maze.getGoalPosition().getColumnIndex())){
            if(currC<EndCol){
                currC++;}
            else{ //they can't we equals in this part (checked before)
                currC--;}
            maze.mazeContent[maze.getStartPosition().getRowIndex()][currC]= 0;
        }

    }

    /*     while (!(currR==EndPos.getRowIndex())){
            BuildSolutionByRow(currR, EndPos.getRowIndex());
            simpleMaze.mazeContent[currR][currC]= 0;}*/

    //help function  - change currR accordingly  to EndRow value
    public void BuildSolutionByRow(int currR, int EndRow, Maze maze) {
        while(!(currR==maze.getGoalPosition().getRowIndex())){
            if(currR<EndRow){
                currR++;}
            else{ //they can't we equals in this part (checked before)
                currR--;}

            maze.mazeContent[currR][maze.getGoalPosition().getColumnIndex()]= 0;

        }

    }
}
