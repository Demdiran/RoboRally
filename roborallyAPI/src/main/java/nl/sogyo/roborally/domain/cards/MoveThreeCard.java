package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class MoveThreeCard extends Card{

    public MoveThreeCard(){
        super();
    }

    public MoveThreeCard(int speed){
        super(speed);
    }

    public void doCardAction(Robot robot, Board board, List<Robot> robots){
        boolean hasMoved = moveRobotInDirectionIfPossible(robot, robot.getOrientation(), board, robots);
        if(hasMoved && robot.isOnBoard()){
            hasMoved = moveRobotInDirectionIfPossible(robot, robot.getOrientation(), board, robots);            
            if(hasMoved && robot.isOnBoard()){
                moveRobotInDirectionIfPossible(robot, robot.getOrientation(), board, robots);
            }
        }
    }

    public String getName(){
        return "MoveThreeCard";
    }
}