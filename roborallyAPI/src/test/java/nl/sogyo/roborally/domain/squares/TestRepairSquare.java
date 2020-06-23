package nl.sogyo.roborally.domain.squares;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.Roborally;
import nl.sogyo.roborally.domain.cards.DoNothingCard;
import nl.sogyo.roborally.domain.cards.MoveOneCard;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.BoardFactory;

public class TestRepairSquare {
    private Board repairBoard = BoardFactory.createRepairSquareBoard();

    @Test
    public void testRobotReceivesHealthIfEndsOnRepairSquare(){
        Robot robot = new Robot(1,0, Direction.SOUTH);
        Roborally roborally = new Roborally(repairBoard, robot);
        robot.takeDamage(5);
        int[] cards = {0,7,7,7,7};
        robot.program(cards);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getHealth() == 6);
    }

    @Test
    public void testRobotReceivesNoHealthIfRepairSquareIsNotTheEnd(){
        Robot robot = new Robot(1,0, Direction.SOUTH);
        Roborally roborally = new Roborally(repairBoard, robot);
        robot.takeDamage(5);
        int[] cards = {0,0,7,7,7};
        robot.program(cards);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getHealth() == 4);
    }



}