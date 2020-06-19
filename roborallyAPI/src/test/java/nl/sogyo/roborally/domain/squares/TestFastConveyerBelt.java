package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.Roborally;
import nl.sogyo.roborally.domain.cards.*;

import org.junit.Before;
import org.junit.Test;
import nl.sogyo.roborally.domain.cards.DoNothingCard;
import nl.sogyo.roborally.domain.robots.Robot;

public class TestFastConveyerBelt {
    private Board FASTCONVEYORBELTTESTBOARD = null;
    private Board MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYFASTCONVEYORBELTWITHOUTPITTESTBOARD = null;
    private Board MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYFASTCONVEYORBELTWITHPITTESTBOARD = null;
    private Board BIGFASTCONVEYORBELTTESTBOARD = null;
    private Board FASTSLOWCONVEYORSINTERACTIONTESTBOARD = null;
    
    @Before
    public void initializeBoards(){
        FASTCONVEYORBELTTESTBOARD = BoardFactory.createSmallBoardWithFastConveyers();
        MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYFASTCONVEYORBELTWITHOUTPITTESTBOARD = BoardFactory.createMultipleRobotsPushedOnFastConveyersWithoutPit();
        MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYFASTCONVEYORBELTWITHPITTESTBOARD = BoardFactory.createMultipleRobotsPushedOnFastConveyersWithPit();
        BIGFASTCONVEYORBELTTESTBOARD = BoardFactory.createBigFastConveyorBeltTestBoard();
        FASTSLOWCONVEYORSINTERACTIONTESTBOARD = BoardFactory.createFastSlowConveyorsInteractionTestBoard();
    }

    @Test
    public void testMovementRobotOnFastConveyorbeltNORTH(){
        Robot robot = new Robot(0,4, "Robot1", 7);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 2);
    }
    
    @Test
    public void testMovementRobotMovesOneOnFastConveyorbeltNORTH(){
        Robot robot = new Robot(0,3);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 2);
    }
    
    @Test
    public void testMovementRobotOnFastConveyorbeltEAST(){
        Robot robot = new Robot(0,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 2 && robot.getYCoordinate() == 0);
    }
    
    @Test
    public void testMovementRobotMovesOneOnFastConveyorbeltEAST(){
        Robot robot = new Robot(1,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 2 && robot.getYCoordinate() == 0);
    }
    
    @Test
    public void testMovementRobotOnFastConveyorbeltSOUTH(){
        Robot robot = new Robot(4,2);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 4 && robot.getYCoordinate() == 4);
    }
    
    @Test
    public void testMovementRobotMovesOneOnFastConveyorbeltSOUTH(){
        Robot robot = new Robot(4,3);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 4 && robot.getYCoordinate() == 4);
    }
    
    @Test
    public void testMovementRobotOnFastConveyorbeltWEST(){
        Robot robot = new Robot(4,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 2 && robot.getYCoordinate() == 0);
    }
    
    @Test
    public void testMovementRobotMovesOneOnFastConveyorbeltWEST(){
        Robot robot = new Robot(3,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 2 && robot.getYCoordinate() == 0);
    }
    
    @Test
    public void testFastConveyorbeltWall(){
        Robot robot = new Robot(1,1);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
    }
    
    @Test
    public void testFastConveyorbeltWallAfterOneMove(){
        Robot robot = new Robot(0,1);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(FASTCONVEYORBELTTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
    }
    
    @Test
    public void testThreeRobotsBehindEachOtherOnFastConveyorBeltOtherOrder(){
        Robot robot1 = new Robot(9, 8, "Robot1", 7);
        Robot robot2 = new Robot(9, 7, "Robot2", 7);
        Robot robot3 = new Robot(9, 6, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 6);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 5);
        assert(robot3.getXCoordinate() == 9 && robot3.getYCoordinate() == 4);
    }
    
    @Test
    public void testThreeRobotsBehindEachOtherOnFastConveyorBelt(){
        Robot robot1 = new Robot(9, 6, "Robot1", 7);
        Robot robot2 = new Robot(9, 7, "Robot2", 7);
        Robot robot3 = new Robot(9, 8, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 4);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 5);
        assert(robot3.getXCoordinate() == 9 && robot3.getYCoordinate() == 6);
    }
    
    @Test
    public void testThreeRobotsBehindEachOtherWithOneSquareBetweenOnFastConveyorbeltOtherOrder(){
        Robot robot1 = new Robot(9, 9, "Robot1", 7);
        Robot robot2 = new Robot(9, 7, "Robot2", 7);
        Robot robot3 = new Robot(9, 5, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 7);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 5);
        assert(robot3.getXCoordinate() == 9 && robot3.getYCoordinate() == 3);
    }
    
    @Test
    public void testThreeRobotsBehindEachOtherFirstRobotPushedOffFastConveyor(){
        Robot robot1 = new Robot(9, 1, "Robot1", 7);
        Robot robot2 = new Robot(9, 2, "Robot2", 7);
        Robot robot3 = new Robot(9, 3, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 1);
        assert(robot3.getXCoordinate() == 9 && robot3.getYCoordinate() == 2);
    }
    
    @Test
    public void testThreeRobotsBehindEachOtherThirdRobotPushedOffFastConveyor(){
        Robot robot1 = new Robot(9, 3, "Robot1", 7);
        Robot robot2 = new Robot(9, 2, "Robot2", 7);
        Robot robot3 = new Robot(9, 1, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 1);
        assert(robot3.getXCoordinate() == 9 && robot3.getYCoordinate() == 0);
    }
    
    @Test
    public void testThreeRobotsBehindEachOtherInFrontOfPitOnFastConveyor(){
        Robot robot1 = new Robot(2, 3, "Robot1", 7);
        Robot robot2 = new Robot(2, 2, "Robot2", 7);
        Robot robot3 = new Robot(2, 1, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 2 && robot1.getYCoordinate() == 1);
        assert(robot2.getXCoordinate() == -1 && robot2.getYCoordinate() == -1);
        assert(robot3.getXCoordinate() == -1 && robot3.getYCoordinate() == -1);
    }
    
    @Test
    public void testThreeRobotsBehindEachOtherInFrontOfPitWithOneSquareInBetweenOnFastConveyor(){
        Robot robot1 = new Robot(2, 4, "Robot1", 7);
        Robot robot2 = new Robot(2, 3, "Robot2", 7);
        Robot robot3 = new Robot(2, 2, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 2 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 2 && robot2.getYCoordinate() == 1);
        assert(robot3.getXCoordinate() == -1 && robot3.getYCoordinate() == -1);
    }
    
    @Test
    public void testThreeRobotsPushedOffBoardAfterZeroStepsOnFastConveyor(){
        Robot robot1 = new Robot(1, 2, "Robot1", 7);
        Robot robot2 = new Robot(1, 1, "Robot2", 7);
        Robot robot3 = new Robot(1, 0, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == -1 && robot2.getYCoordinate() == -1);
        assert(robot3.getXCoordinate() == -1 && robot3.getYCoordinate() == -1);
    }
    
    @Test
    public void testThreeRobotsPushedOffBoardAfterOneStepOnFastConveyor(){
        Robot robot1 = new Robot(1, 3, "Robot1", 7);
        Robot robot2 = new Robot(1, 2, "Robot2", 7);
        Robot robot3 = new Robot(1, 1, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 1);
        assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 0);
        assert(robot3.getXCoordinate() == -1 && robot3.getYCoordinate() == -1);
    }
    
    @Test
    public void testThreeRobotsPushedIntoWallAfterZeroStepsOnFastConveyor(){
        Robot robot1 = new Robot(0, 2, "Robot1", 7);
        Robot robot2 = new Robot(0, 1, "Robot2", 7);
        Robot robot3 = new Robot(0, 0, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 0 && robot2.getYCoordinate() == 1);
        assert(robot3.getXCoordinate() == 0 && robot3.getYCoordinate() == 0);
    }
    
    @Test
    public void testThreeRobotsPushedIntoWallAfterOneStepOnFastConveyor(){
        Robot robot1 = new Robot(0, 3, "Robot1", 7);
        Robot robot2 = new Robot(0, 2, "Robot2", 7);
        Robot robot3 = new Robot(0, 1, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);        
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 0 && robot2.getYCoordinate() == 1);
        assert(robot3.getXCoordinate() == 0 && robot3.getYCoordinate() == 0);
    }
    
    @Test
    public void testRobotPushedOffBeltIntoOtherRobotAfterZeroStepsOnFastConveyor(){
        Robot robot1 = new Robot(9, 0, "Robot1", 7);
        Robot robot2 = new Robot(9, 1, "Robot2", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 1);
    }
    
    @Test
    public void testRobotPushedOffBeltIntoOtherRobotAfterOneStepOnFastConveyor(){
        Robot robot1 = new Robot(9, 0, "Robot1", 7);
        Robot robot2 = new Robot(9, 2, "Robot2", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 1);
    }
    
    @Test
    public void testRobotTurningLeftOnRotatingFastConveyorbeltAfterOneStep(){
        Robot robot = new Robot(8, 2, Direction.EAST);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 9 && robot.getYCoordinate() == 1);
        assert(robot.getOrientation() == Direction.NORTH);
    }
    
    @Test
    public void testRobotTurningLeftOnRotatingFastConveyorbeltAfterTwoSteps(){
        Robot robot = new Robot(7, 2, Direction.EAST);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 9 && robot.getYCoordinate() == 2);
        assert(robot.getOrientation() == Direction.NORTH);
    }
    
    @Test
    public void testRobotTurningRightOnRotatingFastConveyorbeltAfterOneStep(){
        Robot robot = new Robot(5, 3, Direction.NORTH);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 6 && robot.getYCoordinate() == 2);
        assert(robot.getOrientation() == Direction.EAST);
    }
    
    @Test
    public void testRobotTurningRightOnRotatingFastConveyorbeltAfterTwoSteps(){
        Robot robot = new Robot(5, 4, Direction.NORTH);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 5 && robot.getYCoordinate() == 2);
        assert(robot.getOrientation() == Direction.EAST);
    }
    
    @Test
    public void testRobotTurningReverseOnRotatingFastConveyorbeltAfterOneStepEndsInSamePosition(){
        Robot robot = new Robot(3, 5, Direction.NORTH);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 3 && robot.getYCoordinate() == 5);
        assert(robot.getOrientation() == Direction.NORTH);
    }
    
    @Test
    public void testRobotTurningReverseOnRotatingFastConveyorbeltAfterTwoSteps(){
        Robot robot = new Robot(3, 6, Direction.NORTH);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 3 && robot.getYCoordinate() == 4);
        assert(robot.getOrientation() == Direction.SOUTH);
    }
    
    @Test
    public void testRobotNotTurningOnFastConveyorbelt(){
        Robot robot = new Robot(9, 9, Direction.NORTH);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 9 && robot.getYCoordinate() == 7);
        assert(robot.getOrientation() == Direction.NORTH);
    }
    
    @Test
    public void testRobotNotTurningWhenPushedOffFastConveyorbelt(){
        Robot robot = new Robot(9, 1, Direction.NORTH);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 9 && robot.getYCoordinate() == 0);
        assert(robot.getOrientation() == Direction.NORTH);
    }
    
    @Test
    public void testTwoRobotsWithSameDestinationAfterOneStepNotMoving(){
        Robot robot1 = new Robot(8, 2, "Robot1", 7);
        Robot robot2 = new Robot(9, 3, "Robot2", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 8 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 3);
    }
    
    @Test
    public void testTwoRobotsWithSameDestinationAfterTwoStepsMoveOneStep(){
        Robot robot1 = new Robot(7, 2, "Robot1", 7);
        Robot robot2 = new Robot(9, 4, "Robot2", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 8 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 3);
    }
    
    @Test
    public void testRobotChainNotMovingDueToSameDestinationAfterOneStep(){
        Robot robot1 = new Robot(9, 4, "Robot1", 7);
        Robot robot2 = new Robot(9, 3, "Robot2", 7);
        Robot robot3 = new Robot(8, 2, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 4);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 3);
        assert(robot3.getXCoordinate() == 8 && robot3.getYCoordinate() == 2);
    }
    
    @Test
    public void testRobotChainMovingOnlyOneStepDueToSameDestinationAfterTwoSteps(){
        Robot robot1 = new Robot(9, 5, "Robot1", 7);
        Robot robot2 = new Robot(9, 4, "Robot2", 7);
        Robot robot3 = new Robot(7, 2, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 4);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 3);
        assert(robot3.getXCoordinate() == 8 && robot3.getYCoordinate() == 2);
    }
    
    @Test
    public void testRobotMovingBehindOtherCrossingRobot(){
        Robot robot1 = new Robot(7, 2, "Robot1", 7);
        Robot robot2 = new Robot(9, 3, "Robot2", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 9 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 1);
    }
    
    @Test
    public void testRobotMovingOnlyOneStepDueToOtherCrossingRobots(){
        Robot robot1 = new Robot(7, 2, "Robot1", 7);
        Robot robot2 = new Robot(9, 3, "Robot2", 7);
        Robot robot3 = new Robot(9, 4, "Robot3", 7);
        Roborally roborally = new Roborally(BIGFASTCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 8 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 9 && robot2.getYCoordinate() == 1);
        assert(robot3.getXCoordinate() == 9 && robot3.getYCoordinate() == 3);
    }
    
    @Test
    public void testFourRobotsPushedToSamePitByFastConveyorbelt(){
        Robot robot1 = new Robot(2, 0, "Robot1", 7);
        Robot robot2 = new Robot(4, 2, "Robot2", 7);
        Robot robot3 = new Robot(2, 4, "Robot3", 7);
        Robot robot4 = new Robot(0, 2, "Robot4", 7);
        robot1.setRespawnPoint(0, 0);
        robot2.setRespawnPoint(4, 0);
        robot3.setRespawnPoint(4, 4);
        robot4.setRespawnPoint(0, 4);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        robot4.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYFASTCONVEYORBELTWITHPITTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.addRobot(robot4);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 4 && robot2.getYCoordinate() == 0);
        assert(robot3.getXCoordinate() == 4 && robot3.getYCoordinate() == 4);
        assert(robot4.getXCoordinate() == 0 && robot4.getYCoordinate() == 4);
    }
    
    @Test
    public void testFourRobotsPushedToSameSquareByFastConveyorbeltMoveOnlyOneStep(){
        Robot robot1 = new Robot(2, 0, "Robot1", 7);
        Robot robot2 = new Robot(4, 2, "Robot2", 7);
        Robot robot3 = new Robot(2, 4, "Robot3", 7);
        Robot robot4 = new Robot(0, 2, "Robot4", 7);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        robot4.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYFASTCONVEYORBELTWITHOUTPITTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.addRobot(robot4);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 2 && robot1.getYCoordinate() == 1);
        assert(robot2.getXCoordinate() == 3 && robot2.getYCoordinate() == 2);
        assert(robot3.getXCoordinate() == 2 && robot3.getYCoordinate() == 3);
        assert(robot4.getXCoordinate() == 1 && robot4.getYCoordinate() == 2);
    }
    
    @Test
    public void testRobotPushedFromFastToSlowConveyorbeltTwoSteps(){
        Robot robot = new Robot(1, 3);
        Roborally roborally = new Roborally(FASTSLOWCONVEYORSINTERACTIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 6);
    }
    
    @Test
    public void testRobotPushedFromFastToSlowConveyorbeltOneSteps(){
        Robot robot = new Robot(1, 4);
        Roborally roborally = new Roborally(FASTSLOWCONVEYORSINTERACTIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 6);
    }
    
    @Test
    public void testRobotPushedFromSlowToFastConveyorbelt(){
        Robot robot = new Robot(3, 5);
        Roborally roborally = new Roborally(FASTSLOWCONVEYORSINTERACTIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 3 && robot.getYCoordinate() == 4);
    }
    
    @Test
    public void testRobotNotTurningWhenPushedFromFastToSlowConveyorbelt(){
        Robot robot = new Robot(6, 9, Direction.EAST);
        Roborally roborally = new Roborally(FASTSLOWCONVEYORSINTERACTIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 7 && robot.getYCoordinate() == 8);
        assert(robot.getOrientation() == Direction.EAST);
    }
    
    @Test
    public void testRobotNotTurningWhenPushedFromSlowToFastConveyorbelt(){
        Robot robot = new Robot(6, 1, Direction.EAST);
        Roborally roborally = new Roborally(FASTSLOWCONVEYORSINTERACTIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 7 && robot.getYCoordinate() == 1);
        assert(robot.getOrientation() == Direction.EAST);
    }
}

