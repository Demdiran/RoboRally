package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestBoard{


    @Test
    public void testGetSquare(){
        Board board = BoardFactory.createSimpleBoard();
        ArrayList<ArrayList<Square>> list = board.getBoard();
        assertEquals(list.get(1).get(0), board.getSquare(0, 1));
    }

    @Test
    public void testWallConsistencyCheckTrue(){
        Board board = BoardFactory.createSimpleBoard();
        assert(board.hasConsistentWalls());
    }

    @Test
    public void testWallConsistencyCheckFalse(){
        Board board = BoardFactory.createWrongWalls();
        assert(!board.hasConsistentWalls());
    }

    @Test
    public void testRectangularBoardTrue(){
        Board board = BoardFactory.createSimpleBoard();
        assert(board.isRectangular());
    }
    
    @Test
    public void testRectangularBoardFalse(){
        Board board = BoardFactory.createNonRectangularBoard();
        assert(!board.isRectangular());
    }

    @Test
    public void testLaserTestBoard(){
        Board board = BoardFactory.createBoardlaserTestBoard();
        assert(board.isRectangular());
        assert(board.hasConsistentWalls());
    }

    @Test
    public void testAllLasersOnWallTrue(){
        Board board = BoardFactory.createBoardlaserTestBoard();
        assert(board.allLasersOnWalls());
    }

    @Test
    public void testAllLasersOnWallFalse(){
        Board board = BoardFactory.createFaultyLaserTestBoard();
        assert(!board.allLasersOnWalls());
    }

    @Test
    public void testTESTBOARD4X4(){
        Board TESTBOARD4X4 = BoardFactory.createTESTBOARD4X4();
        assert(TESTBOARD4X4.isRectangular());
        assert(TESTBOARD4X4.hasConsistentWalls());
        assert(TESTBOARD4X4.allLasersOnWalls());
    }

    @Test
    public void testPitTestBoard(){
        Board pitTestBoard = BoardFactory.createPitTestBoard();        
        assert(pitTestBoard.isRectangular());
        assert(pitTestBoard.hasConsistentWalls());
        assert(pitTestBoard.allLasersOnWalls());
    }

    @Test
    public void testSlowConveyorbeltTestBoard(){        
        Board slowConveyorbeltTestBoard = BoardFactory.createSlowConveyorbeltTestBoard();
        assert(slowConveyorbeltTestBoard.isRectangular());
        assert(slowConveyorbeltTestBoard.hasConsistentWalls());
        assert(slowConveyorbeltTestBoard.allLasersOnWalls());
    }

    @Test
    public void testSlowConveyorbeltTestBoardWalls(){
        Board slowConveyorbeltTestBoardWalls = BoardFactory.createSlowConveyorbeltTestBoardOther();
        assert(slowConveyorbeltTestBoardWalls.isRectangular());
        assert(slowConveyorbeltTestBoardWalls.hasConsistentWalls());
        assert(slowConveyorbeltTestBoardWalls.allLasersOnWalls());
    }

    @Test
    public void testGearTestBoard(){
        Board gearTestBoard = BoardFactory.createGearTestBoard();
        assert(gearTestBoard.isRectangular());
        assert(gearTestBoard.hasConsistentWalls());
        assert(gearTestBoard.allLasersOnWalls());
    }

    @Test
    public void testRobotLaserTestBoard(){
        Board robotLaserTestBoard = BoardFactory.createRobotLaserWallTestBoard();
        assert(robotLaserTestBoard.isRectangular());
        assert(robotLaserTestBoard.hasConsistentWalls());
        assert(robotLaserTestBoard.allLasersOnWalls());
    }

    @Test
    public void testSmallCompleteBoard(){
        Board smallCompleteBoard = BoardFactory.createSmallCompleteBoard();
        assert(smallCompleteBoard.isRectangular());
        assert(smallCompleteBoard.hasConsistentWalls());
        assert(smallCompleteBoard.allLasersOnWalls());
    }

    @Test
    public void testSlowConveyorbeltRotationTestBoard(){
        Board slowConveyorbeltRotationTestBoard = BoardFactory.createSlowConveyorbeltRotationTestBoard();
        assert(slowConveyorbeltRotationTestBoard.isRectangular());
        assert(slowConveyorbeltRotationTestBoard.hasConsistentWalls());
        assert(slowConveyorbeltRotationTestBoard.allLasersOnWalls());
    }

    @Test
    public void testMultipleRobotsOnSlowConveyorbeltTestBoard(){
        Board multipleRobotsOnSlowConveyorbeltTestBoard = BoardFactory.createMultipleRobotsOnSlowConveyorbeltTestBoard();
        assert(multipleRobotsOnSlowConveyorbeltTestBoard.isRectangular());
        assert(multipleRobotsOnSlowConveyorbeltTestBoard.hasConsistentWalls());
        assert(multipleRobotsOnSlowConveyorbeltTestBoard.allLasersOnWalls());
    }

    @Test
    public void testMultipleRobotsOnSlowConveyorbeltWithPitTestBoard(){
        Board multipleRobotsOnSlowConveyorbeltWithPitTestBoard = BoardFactory.createMultipleRobotsOnSlowConveyorbeltWithPitTestBoard();
        assert(multipleRobotsOnSlowConveyorbeltWithPitTestBoard.isRectangular());
        assert(multipleRobotsOnSlowConveyorbeltWithPitTestBoard.hasConsistentWalls());
        assert(multipleRobotsOnSlowConveyorbeltWithPitTestBoard.allLasersOnWalls());
    }

    @Test
    public void testMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard(){
        Board multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard = BoardFactory.createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard();
        assert(multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard.isRectangular());
        assert(multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard.hasConsistentWalls());
        assert(multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard.allLasersOnWalls());
    }

    @Test
    public void testMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard(){
        Board multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard = BoardFactory.createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard();
        assert(multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard.isRectangular());
        assert(multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard.hasConsistentWalls());
        assert(multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard.allLasersOnWalls());
    }

    @Test
    public void testSmallBoardWithFastConveyers(){
        Board smallBoardWithFastConveyers = BoardFactory.createSmallBoardWithFastConveyers();
        assert(smallBoardWithFastConveyers.isRectangular());
        assert(smallBoardWithFastConveyers.hasConsistentWalls());
        assert(smallBoardWithFastConveyers.allLasersOnWalls());
    }

    @Test
    public void testFastConveyersWithPits(){
        Board fastConveyersWithPits = BoardFactory.createFastConveyersWithPits();
        assert(fastConveyersWithPits.isRectangular());
        assert(fastConveyersWithPits.hasConsistentWalls());
        assert(fastConveyersWithPits.allLasersOnWalls());
    }

    @Test
    public void testMultipleRobotsPushedOnFastConveyersWithPit(){
        Board multipleRobotsPushedOnFastConveyersWithPit = BoardFactory.createMultipleRobotsPushedOnFastConveyersWithPit();
        assert(multipleRobotsPushedOnFastConveyersWithPit.isRectangular());
        assert(multipleRobotsPushedOnFastConveyersWithPit.hasConsistentWalls());
        assert(multipleRobotsPushedOnFastConveyersWithPit.allLasersOnWalls());
    }

    @Test
    public void testMultipleRobotsPushedOnFastConveyersWithoutPit(){
        Board multipleRobotsPushedOnFastConveyersWithoutPit = BoardFactory.createMultipleRobotsPushedOnFastConveyersWithoutPit();
        assert(multipleRobotsPushedOnFastConveyersWithoutPit.isRectangular());
        assert(multipleRobotsPushedOnFastConveyersWithoutPit.hasConsistentWalls());
        assert(multipleRobotsPushedOnFastConveyersWithoutPit.allLasersOnWalls());
    }

    @Test
    public void testBigFastConveyorBeltTestBoard(){
        Board bigFastConveyorBeltTestBoard = BoardFactory.createBigFastConveyorBeltTestBoard();
        assert(bigFastConveyorBeltTestBoard.isRectangular());
        assert(bigFastConveyorBeltTestBoard.hasConsistentWalls());
        assert(bigFastConveyorBeltTestBoard.allLasersOnWalls());
    }

    @Test
    public void testFastConveyorBeltFrontEndGameBoard(){
        Board fastConveyorBeltFrontEndGameBoard = BoardFactory.createFastConveyorBeltFrontEndGameBoard();
        assert(fastConveyorBeltFrontEndGameBoard.isRectangular());
        assert(fastConveyorBeltFrontEndGameBoard.hasConsistentWalls());
        assert(fastConveyorBeltFrontEndGameBoard.allLasersOnWalls());
    }
    
    @Test
    public void testFastSlowConveyorsInteractionTestBoard(){
        Board fastSlowConveyorsInteractionTestBoard = BoardFactory.createFastSlowConveyorsInteractionTestBoard();
        assert(fastSlowConveyorsInteractionTestBoard.isRectangular());
        assert(fastSlowConveyorsInteractionTestBoard.hasConsistentWalls());
    }

    @Test
    public void testBeginner12x12GameBoard(){
        Board beginner12x12GameBoard = BoardFactory.createBeginner12x12GameBoard();
        assert(beginner12x12GameBoard.isRectangular());
        assert(beginner12x12GameBoard.hasConsistentWalls());
        assert(beginner12x12GameBoard.allLasersOnWalls());
    }

    @Test
    public void testIntermediate12x12GameBoard(){
        Board intermediate12x12GameBoard = BoardFactory.createIntermediate12x12GameBoard();
        assert(intermediate12x12GameBoard.isRectangular());
        assert(intermediate12x12GameBoard.hasConsistentWalls());
        assert(intermediate12x12GameBoard.allLasersOnWalls());
    }
}