package nl.sogyo.roborally.domain;

import java.util.ArrayList;
import java.util.List;


import nl.sogyo.roborally.domain.cards.Card;
import nl.sogyo.roborally.domain.cards.Deck;
import nl.sogyo.roborally.domain.elements.Laser;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

public class Roborally{

    List<Robot> robots = new ArrayList<Robot>();
    Board board;
    private Robot winner = null;
    Deck deck = new Deck();
    private int nextRegisterToBePlayed = 0;
    private boolean readyToDealCards = false;
    private boolean programmingPhase = true;
    
    public Roborally(){
        this.board = BoardFactory.createTESTBOARD4X4();
    }

    public Roborally(Robot robot){
        this.robots.add(robot);
        this.board = BoardFactory.createTESTBOARD4X4();
    }

    public Roborally(Board board){
        this.board = board;
    }

    public Roborally(Board board, Robot robot){
        this.board = board;
        this.robots.add(robot);
    }

    public List<Robot> getRobots(){
        return this.robots;
    }

    public Board getBoard(){
        return this.board;
    }

    public boolean allRobotsReady(){
        boolean robotsReady = true;
        for(Robot robot : robots){
            robotsReady &= (robot.isReady() || robot.isInactive() || !robot.isAlive());
        }
        return robotsReady;
    }

    public boolean allRobotsReadyForNextMove(){
        boolean robotsReadyForNextMove = true;
        for(Robot robot : robots){
            robotsReadyForNextMove &= (robot.readyForNextMove() || robot.isInactive() || !robot.isAlive());
        }
        return robotsReadyForNextMove;
    }

    public void playAllRegistersIfRobotsReady(){
        if(allRobotsReady()){
            for(int registernr=0;registernr<5;registernr++){
                playRegister(registernr);
                if(this.winner != null) break;
            }
            prepareNextRound();
        }
    }

    public void playNextRegisterIfAllRobotsReadyAndWantToExecuteNextMove(){
        if(this.nextRegisterToBePlayed < 5){
            if(allRobotsReady() & allRobotsReadyForNextMove()){
                playRegister(this.nextRegisterToBePlayed);
                this.nextRegisterToBePlayed++;
            }
        }
        if(this.nextRegisterToBePlayed == 5){
            setReadyToDealCards();
        }
    }   

    private void playRegister(int registernr){
        robots.sort(Robot.COMPARE_BY_CARD(registernr));
        for(Robot robot : robots){
            robotPlaysCard(robot, registernr);
            robot.wantsToExecuteNextMove();
            if(robotIsWinner(robot, this.board)) this.winner = robot;
        }
        if(this.winner == null){
            activateFastConveyorbelt();
            activateSlowConveyorbelt();
            activateBoardElements(Gear180.class);
            activateBoardElements(GearRight.class);
            activateBoardElements(GearLeft.class);
            fireBoardLasers();
            fireRobotLasers();
            activateBoardElements(Checkpoint.class);
            activateBoardElements(RepairSquare.class);
        }
        for(Robot robot : robots){
            if(robotIsWinner(robot, this.board)) this.winner = robot;
        }
    }

    private boolean robotIsWinner(Robot robot, Board board){
        boolean isWinner = false;
        if(robot.isOnBoard()){
            Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
            if(currentPosition instanceof FinalCheckPoint && robot.hasReachedCheckpoint()){
                robot.setToWinner();
                isWinner = true;
            } 
        }
        return isWinner;            
    }

    public void prepareNextRound(){
        robots.sort(Robot.COMPARE_BY_NAME);
        for(Robot robot : robots){
            robot.cyclePowerState(deck);
            robot.unready();
            robot.respawnIfNecessary(board, robots);
            robot.clearHand(deck);
            robot.hasExecutedNextMove();
        }
        removeUnactiveRobots();
        this.nextRegisterToBePlayed = 0;
        for(Robot robot : robots){
            robot.drawCards(deck);
        }
    }

    private void removeUnactiveRobots(){
        List<Robot> copyRobots = new ArrayList<Robot>(robots);
        for(Robot r:copyRobots){
            if(!r.isOnBoard() || !r.isAlive() ){
                robots.remove(r);
            }
        }
    }

    public Robot getWinner(){
        return this.winner;
    }

    public void activateAllBoardElements(){
        activateFastConveyorbelt();
        activateSlowConveyorbelt();
        activateBoardElements(Gear180.class);
        activateBoardElements(GearRight.class);
        activateBoardElements(GearLeft.class);
        fireBoardLasers();
        fireRobotLasers();
        activateBoardElements(Checkpoint.class);
    }

    private void robotPlaysCard(Robot robot, int cardNr){
        if(!robot.isInactive()){
            Card playingCard = robot.getCard(cardNr);
            playingCard.doCardAction(robot, board, robots);            
        }
    }

    public void program(int cardnr){
        this.robots.get(0).program(cardnr);
    }

    private <T extends Square> void activateBoardElements(Class<T> elementTypeToActivate){
        for(Robot robot : robots){
            if(robot.isOnBoard()){
                Square position = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                if(elementTypeToActivate.isInstance(position)) position.doSquareAction(robot, board, robots);
            }
        }
    }

    private void activateFastConveyorbelt(){
        FastConveyorbelt.addRobotsToFastConveyorbeltList(board, robots);
        for(Robot robot: robots){
            if(robot.isOnBoard()){
                Square position = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                if(position instanceof FastConveyorbelt) position.doSquareAction(robot, board, robots);
            }
        }
        FastConveyorbelt.clearListRobotsOnFastConveyorbelt();
        FastConveyorbelt.addRobotsToFastConveyorbeltList(board, robots);
        for(Robot robot: robots){
            if(robot.isOnBoard()){
                Square position = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                if(position instanceof FastConveyorbelt) position.doSquareAction(robot, board, robots);
            }
        }
        FastConveyorbelt.clearListRobotsOnFastConveyorbelt();
    }

    private void activateSlowConveyorbelt(){
        SlowConveyorbelt.addRobotsToSlowConveyorbeltList(board, robots);
        for(Robot robot: robots){
            if(robot.isOnBoard()){
                Square position = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                if(position instanceof SlowConveyorbelt) position.doSquareAction(robot, board, robots);
            }
        }
        SlowConveyorbelt.clearListRobotsOnSlowConveyorbelt();
    }

    private void fireBoardLasers(){
        for(Laser laser : board.getLasers()){
            laser.fire(robots, board);
        }
    }

    private void fireRobotLasers(){
        for(Robot robot : robots){
            if(robot.isOnBoard()){
                robot.fireLaser(robots, board);
            }
        }
    }

    public void addRobot(Robot robot){
        this.robots.add(robot);
        robot.drawCards(deck);
        robots.sort(Robot.COMPARE_BY_NAME);
    }

    public void removeRobot(Robot robot){
        robot.clearHand(deck);
        this.robots.remove(robot);
    }

    public Deck getDeck(){
        return this.deck;
    }

    public int getNextRegisterToBePlayed(){
        return this.nextRegisterToBePlayed+1;
    }

    public void resetNextRegisterToBePlayed(){
        this.nextRegisterToBePlayed = 0;
    }

    public void resetWinner(){
        this.winner = null;
    }

    public boolean isReadyToDealCards(){
        return this.readyToDealCards;
    }

    public void dealtCards(){
        this.readyToDealCards = false;
    }

    public void setReadyToDealCards(){
        this.readyToDealCards = true;
    }

    public boolean inProgrammingPhase(){
        return this.programmingPhase;
    }

    public void entersProgrammingPhase(){
        this.programmingPhase = true;
    }

    public void leavesProgrammingPhase(){
        this.programmingPhase = false;
    }
}