package nl.sogyo.roborally.domain.squares;

import java.util.ArrayList;
import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;

public class FastConveyorbelt extends Square {
    private Direction movementDirection;
    private static List<Robot> robotsOnFastConveyorbelt = new ArrayList<>();

    public FastConveyorbelt(){
    }

    public FastConveyorbelt(String walls){
        super(walls);
    }

    public FastConveyorbelt(Direction direction){
        this.movementDirection = direction;
    }

    public FastConveyorbelt(Direction direction, String walls){
        super(walls);
        this.movementDirection = direction;
    }

    public Direction getMovementDirection(){
        return this.movementDirection;
    }

    @Override
    public String getType() {
        return "FastConveyorbelt" + this.movementDirection;
    }

    @Override
    public void doSquareAction(Robot robot, Board board, List<Robot> robots){
        if(robot.isOnBoard()){
            moveRobotInDirectionIfPossible(robot, board, robots);
        }
    }
    
    public static void addRobotsToFastConveyorbeltList(Board board, List<Robot> robots){
        for(Robot robot : robots){
            if(robot.isOnBoard()){
                Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                if(currentPosition instanceof FastConveyorbelt & !(robotsOnFastConveyorbelt.contains(robot))){
                    robotsOnFastConveyorbelt.add(robot);
                }
            }
        }
    }

    public static void clearListRobotsOnFastConveyorbelt(){
        robotsOnFastConveyorbelt.clear();
    }

    private boolean moveRobotInDirectionIfPossible(Robot robot, Board board, List<Robot> robots){
        boolean canMove = true;
        FastConveyorbelt currentPosition = (FastConveyorbelt) board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        boolean isBlockedByWall = currentPosition.hasWallAt(this.movementDirection);
        boolean robotHasNotAlreadyMovedOnBelt = robotsOnFastConveyorbelt.contains(robot);
        Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        if(!isBlockedByWall & robotHasNotAlreadyMovedOnBelt & !robotsHaveSameDestination(robot, board)){
            if(destination == null){
                robot.setOffBoard();
            }else{
                canMove = moveRobotIfSquareIsNotNull(robot, destination, board, robots);
            }
        }else{
            canMove = false;
        } 
        robotsOnFastConveyorbelt.remove(robot);
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
            canMove = moveOtherRobotOutOfTheWayIfPossible(robot, otherRobotAtDestination, destination, board, robots);
        }
        return canMove;
    }

    private boolean moveOtherRobotOutOfTheWayIfPossible(Robot robot, Robot otherRobot, Square destination, Board board, List<Robot> robots){
        boolean canMove = false;
        if(destination instanceof FastConveyorbelt){
            FastConveyorbelt destinationBelt = (FastConveyorbelt) destination;
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
            if(otherRobot.isOnBoard()){
                Square otherRobotPosition = board.getSquare(otherRobot.getXCoordinate(), otherRobot.getYCoordinate());
                if(otherRobot != robot && destination == otherRobotPosition){
                    return otherRobot;
                }
            }
        }
        return null;
    }

    private boolean robotsHaveSameDestination(Robot robot, Board board){
        Square currentRobotDestination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        boolean destinationMatch = false;
        if(!(currentRobotDestination instanceof Pit)){
            List<Robot> copyRobotsOnFastConveyorbelt = new ArrayList<>();            
            for(Robot robotOnOriginalList : robotsOnFastConveyorbelt){
                copyRobotsOnFastConveyorbelt.add(robotOnOriginalList);
            }  
            for(Robot otherRobot : copyRobotsOnFastConveyorbelt){
                Square otherRobotDestination = getDestination(otherRobot.getXCoordinate(), otherRobot.getYCoordinate(), board);
                if(otherRobotDestination == currentRobotDestination &&
                            otherRobot != robot){
                    destinationMatch = true;
                    robotsOnFastConveyorbelt.remove(otherRobot);
                }
            }
        }
        return destinationMatch;
    }

    private void turnRobotIfNecessary(Robot robot, Board board){
        Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        if(destination instanceof FastConveyorbelt){
            FastConveyorbelt destinationBelt = (FastConveyorbelt) destination;
            Direction destinationDirection = destinationBelt.getMovementDirection();
            if(destinationDirection == movementDirection.getLeft()) robot.turnLeft();
            else if(destinationDirection == movementDirection.getRight()) robot.turnRight();
            else if(destinationDirection == movementDirection.getReverse()) robot.turnReverse();
        }
    }

    private Square getDestination(int xCoordinate, int yCoordinate, Board board){
        FastConveyorbelt origin = (FastConveyorbelt) board.getSquare(xCoordinate, yCoordinate);
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