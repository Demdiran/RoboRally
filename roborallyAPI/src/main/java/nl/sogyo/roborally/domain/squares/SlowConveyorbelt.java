package nl.sogyo.roborally.domain.squares;

import java.util.ArrayList;
import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;

public class SlowConveyorbelt extends Square{
    private Direction movementDirection;
    private static List<Robot> robotsOnSlowConveyorbelt = new ArrayList<>();

    public SlowConveyorbelt(){
    }

    public SlowConveyorbelt(String walls){
        super(walls);
    }

    public SlowConveyorbelt(Direction direction){
        this.movementDirection = direction;
    }

    public SlowConveyorbelt(Direction direction, String walls){
        super(walls);
        this.movementDirection = direction;
    }

    public Direction getMovementDirection(){
        return this.movementDirection;
    }

    @Override
    public String getType(){
        return "SlowConveyorbelt" + this.movementDirection;
    }

    @Override
    public void doSquareAction(Robot robot, Board board, List<Robot> robots){
        if(robot.isOnBoard()){
            moveRobotInDirectionIfPossible(robot, board, robots);
        }
    }

    public static void addRobotsToSlowConveyorbeltList(Board board, List<Robot> robots){
        for(Robot robot : robots){
            if(robot.isOnBoard()){
                Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                if(currentPosition instanceof SlowConveyorbelt & !(robotsOnSlowConveyorbelt.contains(robot))){
                    robotsOnSlowConveyorbelt.add(robot);
                }
            }
        }
    }

    public static void clearListRobotsOnSlowConveyorbelt(){
        robotsOnSlowConveyorbelt.clear();
    }

    private boolean moveRobotInDirectionIfPossible(Robot robot, Board board, List<Robot> robots){
        boolean canMove = true;
        SlowConveyorbelt currentPosition = (SlowConveyorbelt) board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        boolean isBlockedByWall = currentPosition.hasWallAt(this.movementDirection);
        boolean robotHasNotAlreadyMovedOnBelt = robotsOnSlowConveyorbelt.contains(robot);
        Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        if(!isBlockedByWall & robotHasNotAlreadyMovedOnBelt &! robotsHaveSameDestination(robot, board)){
            if(destination == null){
                robot.setOffBoard();
            }else{
                canMove = moveRobotIfSquareIsNotNull(robot, destination, board, robots);
            }
        }else{
            canMove = false;
        } 
        robotsOnSlowConveyorbelt.remove(robot);
        return canMove;
    }

    private boolean moveRobotIfSquareIsNotNull(Robot robot, Square destination, Board board, List<Robot> robots){
        boolean canMove = true;
        Robot otherRobotAtDestination = findOtherRobotAtDestination(robot, board, destination, robots);
        if(otherRobotAtDestination == null){
            turnRobotIfNecessary(robot, board);
            robot.move(this.movementDirection);
            setRobotOffBoardIfNecessary(robot, board);
        }else{
            canMove = moveRobotWhenOtherRobotIsOnItsDestination(robot, otherRobotAtDestination, destination, board, robots);
        }
        return canMove;
    }

    private boolean moveRobotWhenOtherRobotIsOnItsDestination(Robot robot, Robot otherRobot, Square destination, Board board, List<Robot> robots){
        boolean canMove = false;
        if(destination instanceof SlowConveyorbelt){
            SlowConveyorbelt destinationBelt = (SlowConveyorbelt) destination;
            boolean otherRobotMoved = destinationBelt.moveRobotInDirectionIfPossible(otherRobot, board, robots);
            if(otherRobotMoved){
                turnRobotIfNecessary(robot, board);
                robot.move(this.movementDirection);
                setRobotOffBoardIfNecessary(robot, board);
                canMove = true;
            }
        }
        return canMove;
    }

    private Robot findOtherRobotAtDestination(Robot robot, Board board, Square destination, List<Robot> robots){
        for(Robot otherRobot : robots){
            Square otherRobotPosition = board.getSquare(otherRobot.getXCoordinate(), otherRobot.getYCoordinate());
            if(otherRobot != robot && destination == otherRobotPosition){
                return otherRobot;
            }
        }
        return null;
    }

    private boolean robotsHaveSameDestination(Robot robot, Board board){
        Square currentRobotDestination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        boolean destinationMatch = false;
        for(Robot otherRobot : robotsOnSlowConveyorbelt){
            Square otherRobotDestination = getDestination(otherRobot.getXCoordinate(), otherRobot.getYCoordinate(), board);
            if(otherRobotDestination == currentRobotDestination &&
                        otherRobot != robot && !(currentRobotDestination instanceof Pit) ){
                destinationMatch = true;
                robotsOnSlowConveyorbelt.remove(otherRobot);
            }                
        }
        return destinationMatch;
    }

    private void turnRobotIfNecessary(Robot robot, Board board){
        Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        if(destination instanceof SlowConveyorbelt){
            SlowConveyorbelt destinationBelt = (SlowConveyorbelt) destination;
            Direction destinationDirection = destinationBelt.getMovementDirection();
            if(destinationDirection == movementDirection.getLeft()) robot.turnLeft();
            else if(destinationDirection == movementDirection.getRight()) robot.turnRight();
            else if(destinationDirection == movementDirection.getReverse()) robot.turnReverse();
        }
    }

    private Square getDestination(int xCoordinate, int yCoordinate, Board board){
        SlowConveyorbelt origin = (SlowConveyorbelt) board.getSquare(xCoordinate, yCoordinate);
        switch(origin.movementDirection){
            case NORTH: yCoordinate--;
                        break;
            case EAST: xCoordinate++;
                        break;
            case SOUTH: yCoordinate++;
                        break;
            case WEST: xCoordinate--;
                        break;
        }
        if(xCoordinate < 0 || yCoordinate < 0 || xCoordinate >= board.getWidth() || yCoordinate >= board.getHeight()) return null;
        else return board.getSquare(xCoordinate, yCoordinate);
    }
    
    private void setRobotOffBoardIfNecessary(Robot robot, Board board){        
        if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) {
            robot.setOffBoard();
        }
    }
    
    private boolean robotNotOnBoard(Robot robot, Board board){
        return robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
    }
    
    private boolean robotInPit(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return (currentPosition instanceof Pit);
    }

}