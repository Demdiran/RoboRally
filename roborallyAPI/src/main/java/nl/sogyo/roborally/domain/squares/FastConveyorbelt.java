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
        return "FastConveyerBelt" + this.movementDirection;
    }

    @Override
    public void doSquareAction(Robot robot, Board board, List<Robot> robots) {
        moveRobotInDirectionIfPossible(robot, movementDirection, board, robots);
    }
    
    public static void addRobotsToFastConveyorbeltList(Board board, List<Robot> robots){
        System.out.println("Inside addRobotsToFastConveyorbeltList()... ");
        for(Robot robot : robots){
            Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
            if(currentPosition instanceof FastConveyorbelt & !(robotsOnFastConveyorbelt.contains(robot))){
                System.out.println("Added " + robot.getName() + " to robotsOnFastConveyorbeltList.");
                robotsOnFastConveyorbelt.add(robot);
            }
        }
    }

    public static void clearListRobotsOnFastConveyorbelt(){
        robotsOnFastConveyorbelt.clear();
    }

    private boolean moveRobotInDirectionIfPossible(Robot robot, Direction direction, Board board, List<Robot> otherRobots) {
        System.out.println("Entering moveRobotInDirectionIfPossible for: " + robot.getName() + "...");
        System.out.println(robot.getName() + " at the beginning of its function call is at position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
        boolean canMove = true;
        FastConveyorbelt currentPosition = (FastConveyorbelt) board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        boolean isBlockedByWall = currentPosition.hasWallAt(direction);
        boolean robotHasNotAlreadyMovedOnBelt = robotsOnFastConveyorbelt.contains(robot);
        if(!isBlockedByWall & robotHasNotAlreadyMovedOnBelt & !robotsHaveSameDestination(robot, board)){
            Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
            if(destination != null){
                System.out.println("The destination of " + robot.getName() + " is on the board.");
                System.out.println(robot.getName() + " checks if no one is in the way at destination.");
                boolean otherRobotAtDestination = false;
                for(Robot otherRobot : otherRobots){
                    System.out.println("Checking via for-loop if " + otherRobot.getName() + " is in the way of " + robot.getName() + ".");
                    otherRobotAtDestination = checkIfOtherRobotIsAtDestination(otherRobot, board, destination);
                    if(otherRobotAtDestination && (otherRobot != robot & destination instanceof FastConveyorbelt)){
                        System.out.println(otherRobot.getName() + " is in the way of " + robot.getName() + " at fast conveyorbelt position [" + otherRobot.getXCoordinate() + "," + otherRobot.getYCoordinate() + "].");
                        FastConveyorbelt destinationBelt = (FastConveyorbelt) destination;
                        boolean otherRobotMoved = destinationBelt.moveRobotInDirectionIfPossible(otherRobot, destinationBelt.getMovementDirection(), board, otherRobots);
                        if(otherRobotMoved){
                            System.out.println(otherRobot.getName() + " has moved out of the way and is now at position [" + otherRobot.getXCoordinate() + "," + otherRobot.getYCoordinate() + "].");
                            turnRobotIfNecessary(robot, board);
                            robot.move(direction); //does this need to be called twice in certain situations?
                            respawnRobotIfNecessary(robot, board);
                        }
                        else{
                            canMove = false;
                            System.out.println(otherRobot.getName() + " has not moved out of the way of " + robot.getName() + " and is still at position [" + otherRobot.getXCoordinate() + "," + otherRobot.getYCoordinate() + "].");
                            System.out.println("Therefore " + robot.getName() + " cannot move and is still at [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");    
                        } 
                        System.out.println("Exiting for loop for " + otherRobot.getName() + " in original function-call for " + robot.getName());
                        break;
                    } else if(otherRobotAtDestination && (otherRobot != robot & !(destination instanceof FastConveyorbelt))){
                        canMove = false;
                        System.out.println(otherRobot.getName() + " is not on a fast conveyorbelt and can therefore not move out of the way of " + robot.getName() + ".");
                        System.out.println("So " + robot.getName() + " cannot move and is still at its starting position at [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
                        break;
                    }
                }
                if(!otherRobotAtDestination){
                    System.out.println("No other robot is in the way and " + robot.getName() + " can now move.");
                    turnRobotIfNecessary(robot, board);
                    robot.move(direction);
                    respawnRobotIfNecessary(robot, board);
                }
            }
            else{
                System.out.println("The destination of " + robot.getName() + " is off the board, so " + robot.getName() + " respawns.");
                robot.respawn();
            }
        }
        else canMove = false;
        robotsOnFastConveyorbelt.remove(robot);
        System.out.println(robot.getName() + " at the end of its function call is at position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
        System.out.println("Exiting moveRobotInDirectionIfPossible for: " + robot.getName() + "...");
        return canMove;
    }
    
    private boolean checkIfOtherRobotIsAtDestination(Robot otherRobot, Board board, Square destination){
        System.out.println("Checking if " + otherRobot.getName() + " is at destination.");
        Square otherRobotPosition = board.getSquare(otherRobot.getXCoordinate(), otherRobot.getYCoordinate());
        if(destination == otherRobotPosition){
            System.out.println(otherRobot.getName() + " is at destination.");
            return true;
        }
        else{
            System.out.println(otherRobot.getName() + " is not at destination");
            return false;
        }
    }

    private boolean robotsHaveSameDestination(Robot robot, Board board){
        System.out.println("Checking if another robot has the same destination as " + robot.getName() + "...");
        Square currentRobotDestination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        boolean destinationMatch = false;
        if(!(currentRobotDestination instanceof Pit)){
            ArrayList<Robot> copyrobotsOnFastConveyorbelt = new ArrayList<Robot>();
            for(Robot robotOnOriginalList : robotsOnFastConveyorbelt) copyrobotsOnFastConveyorbelt.add(robotOnOriginalList);
            for(Robot otherRobot : copyrobotsOnFastConveyorbelt){
                if(robotsOnFastConveyorbelt.size() > 1){
                    System.out.println("Checking if " + otherRobot.getName() + " has the same destination as " + robot.getName() + "...");
                    Square otherRobotDestination = getDestination(otherRobot.getXCoordinate(), otherRobot.getYCoordinate(), board);
                    if((otherRobotDestination == currentRobotDestination) && (otherRobot != robot)){
                        destinationMatch = true;
                        robotsOnFastConveyorbelt.remove(otherRobot);
                        System.out.println(otherRobot.getName() + " has the same destination as " + robot.getName());
                        System.out.println(otherRobot.getName() + " is removed from robotsOnFastConveyorbelt.");    
                        }
                } else break;
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

    /***
     * This method returns the expected destination of a FastConveyorbelt.
     * It checks if the square in the movement direction of the FastConveyorbelt is
     * also an instance of FastConveyorbelt and returns the destination accordingly (one or two steps ahead).
     * @param xCoordinate The x-coordinate of the origin FastConveyorbelt
     * @param yCoordinate The y-coordinate of the origin FastConveyorbelt
     * @param board The current board
     * @return The expected destination of a FastConveyorbelt
     */
    private Square getDestination(int xCoordinate, int yCoordinate, Board board){
        System.out.println("Getting the destination of the square at position [" + xCoordinate + "," + yCoordinate + "].");
        FastConveyorbelt origin = (FastConveyorbelt) board.getSquare(xCoordinate, yCoordinate);
        boolean onlyOneStepForward = true;
        switch(origin.movementDirection){
            case NORTH: int yCoordinateIntermediateDestinationNorth = yCoordinate - 1;
                        if(board.getSquare(xCoordinate, yCoordinateIntermediateDestinationNorth) instanceof FastConveyorbelt) onlyOneStepForward = false;
                        if(onlyOneStepForward) yCoordinate--;
                        else yCoordinate = yCoordinate-2;
                        break;
            case EAST: int xCoordinateIntermediateDestinationEast = xCoordinate + 1;
                        if(board.getSquare(xCoordinateIntermediateDestinationEast, yCoordinate) instanceof FastConveyorbelt) onlyOneStepForward = false;
                        if(onlyOneStepForward) xCoordinate++;
                        else xCoordinate = xCoordinate+2;
                        break;
            case SOUTH: int yCoordinateIntermediateDestinationSouth = yCoordinate + 1;
                        if(board.getSquare(xCoordinate, yCoordinateIntermediateDestinationSouth) instanceof FastConveyorbelt) onlyOneStepForward = false;
                        if(onlyOneStepForward) yCoordinate++;
                        else yCoordinate = yCoordinate+2;
                        break;
            case WEST: int xCoordinateIntermediateDestinationWest = xCoordinate - 1;
                        if(board.getSquare(xCoordinateIntermediateDestinationWest, yCoordinate) instanceof FastConveyorbelt) onlyOneStepForward = false;
                        if(onlyOneStepForward) xCoordinate--;
                        else xCoordinate = xCoordinate-2;
                        break;
        }
        System.out.println("The destination of the current square is at position [" + xCoordinate + "," + yCoordinate + "].");
        System.out.println("The current robot needs to move only one step forward: " + onlyOneStepForward);
        if(xCoordinate < 0 || yCoordinate < 0 || xCoordinate >= board.getWidth() || yCoordinate >= board.getHeight()) return null;
        else return board.getSquare(xCoordinate, yCoordinate);
    }

    private boolean respawnRobotIfNecessary(Robot robot, Board board){        
        if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) {
            robot.respawn();
            return true;
        }
        return false;
    }
    
    private boolean robotNotOnBoard(Robot robot, Board board){
        return robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
    }
    
    private boolean robotInPit(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return (currentPosition instanceof Pit);
    }
}