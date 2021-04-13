package algorithms.search;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch best = new BestFirstSearch();
    IMazeGenerator mazeGenerator_Empty = new EmptyMazeGenerator();
    IMazeGenerator mazeGenerator_Simple = new SimpleMazeGenerator();
    IMazeGenerator mazeGenerator_My = new MyMazeGenerator();
    IMaze3DGenerator mazeGenerator3D = new MyMaze3DGenerator();


    public void getName() throws Exception{
        assertEquals(best.getName(),"BestFirstSearch");
    }
    @Test
    public void testCreationTime() throws Exception {
        //check if that measure Algorithm BEST time is less than 60 seconds in the 3 mazes that we created.

        //check EmptyMazeGenerator()
        long time_1 = mazeGenerator_Empty.measureAlgorithmTimeMillis(1000/*rows*/,1000/*columns*/);
        assertTrue(time_1 < 60000);//check if that measure Algorithm time is less than 60 seconds
        //check SimpleMazeGenerator()
        long time_2 = mazeGenerator_Simple.measureAlgorithmTimeMillis(1000/*rows*/,1000/*columns*/);
        assertTrue(time_2 < 60000);//check if that measure Algorithm time is less than 60 seconds
        //check MyMazeGenerator()
        long time_3 = mazeGenerator_My.measureAlgorithmTimeMillis(1000/*rows*/,1000/*columns*/);
        assertTrue(time_3 < 60000);//check if that measure Algorithm time is less than 60 seconds
        //check MyMaze3DGenerator()
        long time_4 = mazeGenerator3D.measureAlgorithmTimeMillis(100,100/*rows*/,100/*columns*/);
        assertTrue(time_4 < 60000);//check if that measure Algorithm time is less than 60 seconds
    }

    @Test
    public void testSolutionTime2D() throws Exception {
        //check the time of creation of the maze & solution time with the searching Algorithm BEST and if it less then 60 seconds
        long s_time_0 = System.currentTimeMillis();
        Maze mazeMy = mazeGenerator_My.generate(1000, 1000);
        SearchableMaze searchableMazeMy = new SearchableMaze(mazeMy);
        best.solve(searchableMazeMy);
        long e_time_0 =System.currentTimeMillis();
        assertTrue((e_time_0-s_time_0) < 60000);

        long s_time_1 = System.currentTimeMillis();
        Maze mazeS = mazeGenerator_Simple.generate(1000, 1000);
        SearchableMaze searchableMazeSimple = new SearchableMaze(mazeS);
        best.solve(searchableMazeSimple);
        long e_time_1 =System.currentTimeMillis();
        assertTrue((e_time_1 - s_time_1) < 60000);


        long s_time_2 = System.currentTimeMillis();
        Maze mazeE = mazeGenerator_Simple.generate(1000, 1000);
        SearchableMaze searchableMazeEmpty = new SearchableMaze(mazeE);
        best.solve(searchableMazeEmpty);
        long e_time_2 =System.currentTimeMillis();
        assertTrue((e_time_2 - s_time_2) < 60000);
    }

    @Test
    public void testSolutionTime3D() throws Exception {
        //check the time of creation of the maze & solution time with the searching Algorithm BEST and if it less then 60 seconds
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(100,100,100);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        long s_time = System.currentTimeMillis();
        BestFirstSearch bs = new BestFirstSearch();
        bs.solve(searchableMaze);
        long e_time =System.currentTimeMillis();
        assertTrue((e_time - s_time) < 60000);
    }

    @Test
    //make sure Exception have been thrown
    public void addCostTest() throws Exception {
        boolean not_Except = false;
        try {
            best.addCost(null,null);
        } catch (Exception expectedException) {
            not_Except=true;
        }
        assertTrue(not_Except);

    }

    @Test
    //make sure Exception have been thrown
    public void CreateSolutionTest() throws Exception {
        boolean not_Except = false;
        try {
            best.CreateSolution(true, null, null);

        } catch (Exception expectedException) {
            not_Except=true;
        }
        assertTrue(not_Except);
    }

    @Test
    //make sure Exception have been thrown
    public void SolutionTest() throws Exception {
        boolean not_Except = false;
        try {
            best.solve(null);
        } catch (Exception expectedException) {
            not_Except=true;
        }
        assertTrue(not_Except);
    }

    @Test
    //make sure Exception have been thrown
    public void CreateSmall3DMazeTest() throws Exception {
        boolean not_Except = false;
        try {
            mazeGenerator3D.generate(3,3,1);
        } catch (Exception expectedException) {
            not_Except=true;
        }
        assertTrue(not_Except);
    }

    @Test
    //make sure Exception have been thrown
    public void CreateSmall2DMazeTest() throws Exception {
        boolean not_Except = false;
        try {
            mazeGenerator_My.generate(3,-1);
        } catch (Exception expectedException) {
            not_Except=true;
        }
        assertTrue(not_Except);
    }

    @Test
    //make sure solution is right
    public void CheckSolutionPosition3D() throws Exception {
        Maze3D maze = mazeGenerator3D.generate(100,100,100);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        Solution solution = best.solve(searchableMaze);
        AState start = solution.getSolutionPath().get(0);
        AState end = solution.getSolutionPath().get(solution.getSolutionPath().size()-1);
        AState originStart = searchableMaze.getStartState();
        AState originGoal = searchableMaze.getGoalState();
        assertTrue(start.equals(originStart));
        assertTrue(end.equals(originGoal));
    }

    @Test
    //make sure solution is right
    public void CheckSolutionPosition2D() throws Exception {
        Maze maze = mazeGenerator_My.generate(1000,1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = best.solve(searchableMaze);
        AState start = solution.getSolutionPath().get(0);
        AState end = solution.getSolutionPath().get(solution.getSolutionPath().size()-1);
        AState originStart = searchableMaze.getStartState();
        AState originGoal = searchableMaze.getGoalState();
        assertTrue(start.equals(originStart));
        assertTrue(end.equals(originGoal));
    }


}