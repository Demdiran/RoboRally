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
            System.out.println("Trying to add " + robot.getName() + " to the fast conveyorbelt list.");
            if(robot.isOnBoard()){
                Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                if(currentPosition instanceof FastConveyorbelt & !(robotsOnFastConveyorbelt.contains(robot))){
                    robotsOnFastConveyorbelt.add(robot);
                    System.out.println("Added " + robot.getName() + " to the fast conveyorbelt list.");
                    System.out.println("The fast conveyorbelt list now holds the following robots: ");
                    for(Robot robotOnList : robotsOnFastConveyorbelt) System.out.print(robotOnList.getName());
                    System.out.println();
                }
            }
        }
    }

    public static void clearListRobotsOnFastConveyorbelt(){
        System.out.println("Clearing fast conveyorbelt list.");
        robotsOnFastConveyorbelt.clear();
    }

    private boolean moveRobotInDirectionIfPossible(Robot robot, Board board, List<Robot> robots){
        System.out.println("Trying to move " + robot.getName() + " if possible.");
        System.out.println(robot.getName() + " at the beginning of the function call is at position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
        boolean canMove = true;
        FastConveyorbelt currentPosition = (FastConveyorbelt) board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        boolean isBlockedByWall = currentPosition.hasWallAt(this.movementDirection);
        boolean robotHasNotAlreadyMovedOnBelt = robotsOnFastConveyorbelt.contains(robot);
        Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        if(!isBlockedByWall & robotHasNotAlreadyMovedOnBelt & !robotsHaveSameDestination(robot, board)){
            if(destination == null){
                System.out.println(robot.getName() + " has moved off the board and will respawn in time.");
                robot.setOffBoard();
            }else{
                System.out.println(robot.getName() + " can move to a non-null location on the board.");
                canMove = moveRobotIfSquareIsNotNull(robot, destination, board, robots);
            }
        }else{
            System.out.println(robot.getName() + " cannot move due to a wall, having moved before, or another robot having the same destination.");
            canMove = false;
        } 
        robotsOnFastConveyorbelt.remove(robot);
        System.out.println(robot.getName() + " at the end of its function call is at position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
        System.out.println(robot.getName() + " is now removed from the fast conveyorbelt list. The remaining robots are: ");
        for(Robot robotOnList : robotsOnFastConveyorbelt) System.out.print(robotOnList.getName());
        System.out.println();
        return canMove;
    }

    private boolean moveRobotIfSquareIsNotNull(Robot robot, Square destination, Board board, List<Robot> robots){
        System.out.println("Inside moveRobotIfSquareIsNotNull() for " + robot.getName());
        boolean canMove = true;
        Robot otherRobotAtDestination = findOtherRobotAtDestination(robot, board, destination, robots);
        if(otherRobotAtDestination == null){
            System.out.println("No other robot is in the way of " + robot.getName() + ", which will now move.");
            turnRobotIfNecessary(robot, board);
            robot.move(this.movementDirection);
            setRobotOffBoardIfNecessary(robot, board);
        }else{
            System.out.println(otherRobotAtDestination.getName() + " is in the way of " + robot.getName() + ".");
            canMove = moveOtherRobotOutOfTheWayIfPossible(robot, otherRobotAtDestination, destination, board, robots);
        }
        return canMove;
    }

    private boolean moveOtherRobotOutOfTheWayIfPossible(Robot robot, Robot otherRobot, Square destination, Board board, List<Robot> robots){
        System.out.println("Checking if " + otherRobot.getName() + " can move out of the way of " + robot.getName() + ".");
        boolean canMove = false;
        if(destination instanceof FastConveyorbelt){
            FastConveyorbelt destinationBelt = (FastConveyorbelt) destination;
            boolean otherRobotMoved = destinationBelt.moveRobotInDirectionIfPossible(otherRobot, board, robots);
            if(otherRobotMoved){
                System.out.println(otherRobot.getName() + " moved out of the way. So " + robot.getName() + " can now move.");
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

    // /***
    //  * This method returns the expected destination of a FastConveyorbelt.
    //  * It checks if the square in the movement direction of the FastConveyorbelt is
    //  * also an instance of FastConveyorbelt and returns the destination accordingly (one or two steps ahead).
    //  * @param xCoordinate The x-coordinate of the origin FastConveyorbelt
    //  * @param yCoordinate The y-coordinate of the origin FastConveyorbelt
    //  * @param board The current board
    //  * @return The expected destination of a FastConveyorbelt
    //  */
    // private Square getDestination(int xCoordinate, int yCoordinate, Board board){
    //     FastConveyorbelt origin = (FastConveyorbelt) board.getSquare(xCoordinate, yCoordinate);
    //     boolean onlyOneStepForward = true;
    //     switch(origin.movementDirection){
    //         case NORTH: int yCoordinateIntermediateDestinationNorth = yCoordinate - 1;
    //                     if(board.getSquare(xCoordinate, yCoordinateIntermediateDestinationNorth) instanceof FastConveyorbelt) onlyOneStepForward = false;
    //                     if(onlyOneStepForward) yCoordinate--;
    //                     else yCoordinate = yCoordinate-2;
    //                     break;
    //         case EAST: int xCoordinateIntermediateDestinationEast = xCoordinate + 1;
    //                     if(board.getSquare(xCoordinateIntermediateDestinationEast, yCoordinate) instanceof FastConveyorbelt) onlyOneStepForward = false;
    //                     if(onlyOneStepForward) xCoordinate++;
    //                     else xCoordinate = xCoordinate+2;
    //                     break;
    //         case SOUTH: int yCoordinateIntermediateDestinationSouth = yCoordinate + 1;
    //                     if(board.getSquare(xCoordinate, yCoordinateIntermediateDestinationSouth) instanceof FastConveyorbelt) onlyOneStepForward = false;
    //                     if(onlyOneStepForward) yCoordinate++;
    //                     else yCoordinate = yCoordinate+2;
    //                     break;
    //         case WEST: int xCoordinateIntermediateDestinationWest = xCoordinate - 1;
    //                     if(board.getSquare(xCoordinateIntermediateDestinationWest, yCoordinate) instanceof FastConveyorbelt) onlyOneStepForward = false;
    //                     if(onlyOneStepForward) xCoordinate--;
    //                     else xCoordinate = xCoordinate-2;
    //                     break;
    //     }
    //     if(xCoordinate < 0 || yCoordinate < 0 || xCoordinate >= board.getWidth() || yCoordinate >= board.getHeight()) return null;
    //     else return board.getSquare(xCoordinate, yCoordinate);
    // }

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