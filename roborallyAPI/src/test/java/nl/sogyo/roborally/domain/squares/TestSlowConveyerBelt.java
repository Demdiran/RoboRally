package nl.sogyo.roborally.domain.squares;

import org.junit.Before;
import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.Roborally;
import nl.sogyo.roborally.domain.cards.Card;
import nl.sogyo.roborally.domain.cards.DoNothingCard;
import nl.sogyo.roborally.domain.robots.Robot;


public class TestSlowConveyerBelt {
    private Board SLOWCONVEYORBELTTESTBOARD = null;
    private Board SLOWCONVEYORBELTTESTBOARDOTHER = null;
    private Board MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD = null;
    private Board MULTIPLEROBOTSONSLOWCONVEYORBELTWITHPITTESTBOARD = null;
    private Board MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHOUTPITTESTBOARD = null;
    private Board MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHPITTESTBOARD = null;
    private Board SLOWCONVERYORBELTROTATIONTESTBOARD = null; 
// --------------------------------------------------SlowconveyerBelt START
@Before
public void initializeBoards(){

    SLOWCONVEYORBELTTESTBOARD = BoardFactory.createSlowConveyorbeltTestBoard();
    SLOWCONVEYORBELTTESTBOARDOTHER = BoardFactory.createSlowConveyorbeltTestBoardOther();
    MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD = BoardFactory.createMultipleRobotsOnSlowConveyorbeltTestBoard();
    MULTIPLEROBOTSONSLOWCONVEYORBELTWITHPITTESTBOARD = BoardFactory.createMultipleRobotsOnSlowConveyorbeltWithPitTestBoard();
    MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHOUTPITTESTBOARD = BoardFactory.createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard();
    MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHPITTESTBOARD = BoardFactory.createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard();
    SLOWCONVERYORBELTROTATIONTESTBOARD = BoardFactory.createSlowConveyorbeltRotationTestBoard(); 
}

@Test
public void testMovementRobotOnSlowConveyorbeltNORTH(){
    Robot robot = new Robot(0,2);
    robot.program(new DoNothingCard());
    Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARD, robot);
    roborally.playAllRegistersIfRobotsReady();
    assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
}

@Test
public void testMovementRobotOnSlowConveyorbeltEAST(){
    Robot robot = new Robot(0,0);
    robot.program(new DoNothingCard());
    Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARD, robot);
    roborally.playAllRegistersIfRobotsReady();
    assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 0);
}

@Test
public void testMovementRobotOnSlowConveyorbeltSOUTH(){
    Robot robot = new Robot(2,0);
    robot.program(new DoNothingCard());
    Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARD, robot);
    roborally.playAllRegistersIfRobotsReady();
    assert(robot.getXCoordinate() == 2 && robot.getYCoordinate() == 1);
}

@Test
public void testMovementRobotOnSlowConveyorbeltWEST(){
    Robot robot = new Robot(2,2);
    robot.program(new DoNothingCard());
    Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARD, robot);
    roborally.playAllRegistersIfRobotsReady();
    assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 2);
}

@Test
public void testSlowConveyorbeltWall(){
    Robot robot = new Robot(0,0);
    robot.program(new DoNothingCard());
    Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARDOTHER, robot);
    roborally.playAllRegistersIfRobotsReady();
    assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 0);
}

@Test
public void testSlowConveyorbeltPit(){
    Robot robot = new Robot(2,0);
    robot.program(new DoNothingCard());
    robot.setRespawnPoint(0, 1);
    Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARDOTHER, robot);
    roborally.playAllRegistersIfRobotsReady();
    assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
}

@Test
public void testSlowConveyorbeltOffBoard(){
    Robot robot = new Robot(2,2);
    robot.program(new DoNothingCard());
    robot.setRespawnPoint(1, 1);
    Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARDOTHER, robot);
    roborally.playAllRegistersIfRobotsReady();
    assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
}

@Test
public void testRobotTurningLeftOnRotatingSlowConveyorbelt(){
    Robot robot = new Robot(0, 1, Direction.EAST);
    Card card = new DoNothingCard();
    robot.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
    roborally.activateAllBoardElements();
    assert(1 == robot.getXCoordinate());
    assert(1 == robot.getYCoordinate());
    assert(robot.getOrientation() == Direction.NORTH);
}

@Test
public void testRobotTurningRightOnRotatingSlowConveyorbelt(){
    Robot robot = new Robot(0, 2, Direction.EAST);
    Card card = new DoNothingCard();
    robot.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
    roborally.activateAllBoardElements();
    assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
    assert(robot.getOrientation() == Direction.SOUTH);
}

@Test
public void testRobotTurningReverseOnRotatingSlowConveyorbelt(){
    Robot robot = new Robot(0, 4, Direction.EAST);
    Card card = new DoNothingCard();
    robot.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
    roborally.activateAllBoardElements();
    assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 4);
    assert(robot.getOrientation() == Direction.WEST);
}

@Test
public void testRobotNotTurningOnRotatingSlowConveyorbelt(){
    Robot robot = new Robot(1, 2, Direction.WEST);
    Card card = new DoNothingCard();
    robot.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
    roborally.activateAllBoardElements();
    assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
    assert(robot.getOrientation() == Direction.WEST);
}

@Test
public void testRobotNotTurningOnSlowConveyorbelt(){
Robot robot = new Robot(1, 2, Direction.NORTH);
Card card = new DoNothingCard();
robot.program(card);
Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
roborally.activateAllBoardElements();
assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
assert(robot.getOrientation() == Direction.NORTH);
}

@Test
public void testRobotNotTurningWhenPushedOffSlowConveyorbelt(){
    Robot robot = new Robot(1, 1, Direction.NORTH);
    Card card = new DoNothingCard();
    robot.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
    roborally.activateAllBoardElements();
    assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 0);
    assert(robot.getOrientation() == Direction.NORTH);
}

@Test
public void testRobotPushedOffSlowConveyorbeltIntoOtherRobot(){
    Robot robot1 = new Robot(1, 0, Direction.NORTH);
    Robot robot2 = new Robot(1, 1, Direction.NORTH);
    Card card = new DoNothingCard();
    robot1.program(card);
    robot2.program(card);
    Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.activateAllBoardElements();
    assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 0);
    assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
}

@Test
public void testTwoRobotsNextToEachOtherOnSlowConveyorbelt(){
    Robot robot1 = new Robot(1, 1, Direction.NORTH);
    Robot robot2 = new Robot(1, 2, Direction.NORTH);
    Card card = new DoNothingCard();
    robot1.program(card);
    robot2.program(card);
    Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.activateAllBoardElements();
    assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 0);
    assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
}

@Test
public void testThreeRobotsNextToEachOtherOnSlowConveyorbelt(){
    Robot robot1 = new Robot(1, 1, "Robot1", 7);
    Robot robot2 = new Robot(1, 2, "Robot2", 7);
    Robot robot3 = new Robot(1, 3, "Robot3", 7);
    Card card = new DoNothingCard();
    robot1.program(card);
    robot2.program(card);
    robot3.program(card);
    Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.addRobot(robot3);
    roborally.activateAllBoardElements();
    assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 0);
    assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
    assert(robot3.getXCoordinate() == 1 && robot3.getYCoordinate() == 2);        
}

@Test
public void testThreeRobotsNextToEachOtherOnSlowConveyorbeltOtherOrder(){
    Robot robot1 = new Robot(1, 3, "Robot1", 7);
    Robot robot2 = new Robot(1, 2, "Robot2", 7);
    Robot robot3 = new Robot(1, 1, "Robot3", 7);
    Card card = new DoNothingCard();
    robot1.program(card);
    robot2.program(card);
    robot3.program(card);
    Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.addRobot(robot3);
    roborally.activateAllBoardElements();
    assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 2);
    assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
    assert(robot3.getXCoordinate() == 1 && robot3.getYCoordinate() == 0);        
}

@Test
public void testThreeRobotsNextToEachOtherOnSlowConveyorbeltWithPit(){
    Robot robot1 = new Robot(1, 3, "Robot1", 7);
    robot1.setRespawnPoint(0, 4);
    Robot robot2 = new Robot(1, 2, "Robot2", 7);
    robot2.setRespawnPoint(1, 4);
    Robot robot3 = new Robot(1, 1, "Robot3", 7);
    robot3.setRespawnPoint(0, 5);
    Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTWITHPITTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.addRobot(robot3);
    roborally.activateAllBoardElements();
    assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 2);
    assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
    assert(robot3.getXCoordinate() == -1 && robot3.getYCoordinate() == -1);        
}

@Test
public void testTwoRobotsOnSlowConveyorbeltWithWallInFront(){
    Robot robot1 = new Robot(0, 0, "Robot1", 7);
    robot1.setRespawnPoint(1, 2);
    Robot robot2 = new Robot(0, 1, "Robot2", 7);
    Card card = new DoNothingCard();
    robot1.program(card);
    robot2.program(card);
    Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.playAllRegistersIfRobotsReady();
    assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 0);
    assert(robot2.getXCoordinate() == 0 && robot2.getYCoordinate() == 1);        
}

@Test
public void testRobotNotTurningOnSlowConveyorbeltWithOtherRobotInFrontOfObstacle(){
    Robot robot1 = new Robot(0, 1, "Robot1", 7);
    robot1.setOrientation(Direction.EAST);
    Robot robot2 = new Robot(1, 1, "Robot2", 7);
    robot2.setOrientation(Direction.NORTH);
    Robot robot3 = new Robot(1, 0, "Robot3", 7);
    robot3.setOrientation(Direction.WEST);
    Card card = new DoNothingCard();
    robot1.program(card);
    robot2.program(card);
    robot3.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.addRobot(robot3);
    roborally.playAllRegistersIfRobotsReady();
    assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 1);
    assert(robot1.getOrientation() == Direction.EAST);
    assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);
    assert(robot2.getOrientation() == Direction.NORTH);
    assert(robot3.getXCoordinate() == 1 && robot3.getYCoordinate() == 0);
    assert(robot3.getOrientation() == Direction.WEST);
}

@Test
public void testRobotChainBehindTwoRobotsWithSameDestination(){
    Robot robot1 = new Robot(1, 3, "Robot1", 7);
    Robot robot2 = new Robot(1, 2, "Robot2", 7);
    Robot robot3 = new Robot(0, 1, "Robot3", 7);
    Card card = new DoNothingCard();
    robot1.program(card);
    robot2.program(card);
    robot3.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.addRobot(robot3);
    roborally.activateAllBoardElements();
    assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 3);
    assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 2);
    assert(robot3.getXCoordinate() == 0 && robot3.getYCoordinate() == 1);
}
    
// --------- "T-Section" tests (4 tests) ---------
    @Test
    public void testTwoRobotsPushedToSameSquareByConveyorBelt(){
    Robot robot1 = new Robot(0, 1, "Robot1", 7);
    Robot robot2 = new Robot(1, 2, "Robot2", 7);
    Card card = new DoNothingCard();
    robot1.program(card);
    robot2.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.activateAllBoardElements();
    assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 1);
    assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 2);
    }

    @Test
    public void testFourRobotsPushedToSamePitBySlowConveyorbelt(){
        Robot robot1 = new Robot(1, 0, "Robot1", 7);
        Robot robot2 = new Robot(2, 1, "Robot2", 7);
        Robot robot3 = new Robot(1, 2, "Robot3", 7);
        Robot robot4 = new Robot(0, 1, "Robot4", 7);
        robot1.setRespawnPoint(0, 0);
        robot2.setRespawnPoint(2, 0);
        robot3.setRespawnPoint(2, 2);
        robot4.setRespawnPoint(0, 2);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        robot4.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHPITTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.addRobot(robot4);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 2 && robot2.getYCoordinate() == 0);
        assert(robot3.getXCoordinate() == 2 && robot3.getYCoordinate() == 2);
        assert(robot4.getXCoordinate() == 0 && robot4.getYCoordinate() == 2);
    }

    @Test
    public void testFourRobotsPushedToSameSquareBySlowConveyorbeltDontMove(){
        Robot robot1 = new Robot(1, 0, "Robot1", 7);
        Robot robot2 = new Robot(2, 1, "Robot2", 7);
        Robot robot3 = new Robot(1, 2, "Robot3", 7);
        Robot robot4 = new Robot(0, 1, "Robot4", 7);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        robot4.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHOUTPITTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.addRobot(robot4);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 2 && robot2.getYCoordinate() == 1);
        assert(robot3.getXCoordinate() == 1 && robot3.getYCoordinate() == 2);
        assert(robot4.getXCoordinate() == 0 && robot4.getYCoordinate() == 1);
    }
}