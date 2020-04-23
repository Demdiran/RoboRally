package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class RotateLeftCard implements ICard{

    public void doMove(Robot robot, Board board){    
        robot.turnLeft();    
    }
}