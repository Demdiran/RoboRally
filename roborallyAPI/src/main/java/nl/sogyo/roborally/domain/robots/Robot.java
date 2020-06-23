package nl.sogyo.roborally.domain.robots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//import jdk.javadoc.internal.doclets.toolkit.resources.doclets;
import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.DoNothingCard;
import nl.sogyo.roborally.domain.cards.Card;
import nl.sogyo.roborally.domain.cards.Deck;
import nl.sogyo.roborally.domain.cards.MoveBackCard;
import nl.sogyo.roborally.domain.cards.MoveOneCard;
import nl.sogyo.roborally.domain.cards.MoveThreeCard;
import nl.sogyo.roborally.domain.cards.MoveTwoCard;
import nl.sogyo.roborally.domain.cards.RotateLeftCard;
import nl.sogyo.roborally.domain.cards.RotateRightCard;
import nl.sogyo.roborally.domain.cards.UTurnCard;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Square;

public class Robot{
    private final String[] colours = {"green", "black", "purple", "blue", "red", "brown"};
    List<Card> hand = new ArrayList<Card>();

    Direction orientation = Direction.NORTH;
    Card[] programmedCards = {new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
    private ArrayList<Card> lockedCards = new ArrayList<Card>();
    int health = 9;
    int xCoordinate;
    int yCoordinate;
    int respawnX;
    int respawnY;
    boolean ready;
    boolean wantsToExecuteNextMove;
    boolean onBoard = true;
    String name = "defaultname";
    String colour = "orange";
    ActivityLevel activitylevel = ActivityLevel.ACTIVE;
    boolean hasReachedCheckpoint;
    boolean hasWonTheGame;
    int lives = 3;
    boolean alive = true;
    
    public Robot(){
    }

    public Robot(String name, int colourNr){
        this.name = name;
        if(colourNr < 6) this.colour = colours[colourNr];
        else this.colour = "orange";
    }

    public Robot(String name, Board board, int initNumber){
        this.name = name;
        if(initNumber < 6) this.colour = colours[initNumber];
        else this.colour = "orange";
        board.setRespawnSquare(this, initNumber);

    }

    public Robot(int xCoordinate, int yCoordinate, String name, int colourNr){
        this(xCoordinate, yCoordinate);
        this.name = name;
        if(colourNr < 6) this.colour = colours[colourNr];
        else this.colour = "orange";
    }

    public Robot(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.respawnX = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.respawnY = yCoordinate;
    }

    public Robot(int xCoordinate, int yCoordinate, Direction orientation){
        this(xCoordinate, yCoordinate);
        this.orientation = orientation;
    }

    public String getColour() {
        return colour;
    }

    public String getName(){
        return name;
    }

    public Card getCard(int CardNr){
        return this.programmedCards[CardNr];
    }

    public int getXCoordinate(){
        return this.xCoordinate;
    }

    public int getYCoordinate(){
        return this.yCoordinate;
    }

    public int getHealth(){
        return this.health;
    }

    public Direction getOrientation(){
        return this.orientation;
    }

    public void setOrientation(Direction newOrientation){
        this.orientation = newOrientation;
    }

    public ActivityLevel getActivitylevel() {
        return activitylevel;
    }

    public boolean takeDamageIfHit(int xCoordinate, int yCoordinate, int damage){
        if(this.xCoordinate == xCoordinate && this.yCoordinate == yCoordinate){
            takeDamage(damage);
            return true;
        }
        else{
            return false;
        }
    }

    public void takeDamage(int damage){
        this.health -= damage;
        if(this.health <= 0 ){
            setOffBoard();
        }
    }

    public void receiveHealth(int healthPoints){
        this.health += healthPoints;
        if(this.health > 9){
            this.health = 9;
        }
    }

    public boolean isAt(int xCoordinate, int yCoordinate){
        if(this.xCoordinate == xCoordinate && this.yCoordinate == yCoordinate)
            return true;
        else
            return false;
    }

    public void setRespawnPointAndCurrentPosition(int x, int y, Direction dir){
        this.respawnX = x;
        this.respawnY = y;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.orientation = dir;
    }

    public void setRespawnPoint(int x, int y){
        this.respawnX = x;
        this.respawnY = y;
    }

    public void moveForward(){
        move(this.orientation);
    }

    public void moveBackwards(){
        move(this.orientation.getReverse());
    }

    public void move(Direction direction){
        switch(direction){
            case NORTH: this.yCoordinate--;
                        break;
            case EAST: this.xCoordinate++;
                        break;
            case SOUTH: this.yCoordinate++;
                        break;
            case WEST: this.xCoordinate--;
                        break;
        }
    }

    public void programOneCard(Card card, int index){
        this.programmedCards[index] = card;
    }

    public void programOneCard(int cardnr, int index){
        switch(cardnr){
            case 0: programOneCard(new MoveOneCard(), index);
                    break;
            case 1: programOneCard(new RotateRightCard(), index);
                    break;
            case 2: programOneCard(new RotateLeftCard(), index);
                    break;
            case 3: programOneCard(new UTurnCard(), index);
                    break;
            case 4: programOneCard(new MoveTwoCard(), index);
                    break;
            case 5: programOneCard(new MoveThreeCard(), index);
                    break;
            case 6: programOneCard(new MoveBackCard(), index);
                    break;
            case 7: programOneCard(new DoNothingCard(), index);
                    break;
            default: throw new RuntimeException("Invalid cardnr");
        }
    }

    public void program(int[] cardnrs){
        if(this.activitylevel != ActivityLevel.INACTIVE){
            int cardPosition = 0;
            for(int cardNr:cardnrs){
                programOneCard(cardNr, cardPosition);
                cardPosition++;
            }
        }
        this.ready = true;
    }

    public void program(int cardnr){
        int[] cardnrs = {cardnr, 7, 7, 7, 7};
        program(cardnrs);
    }

    public void program(Card[] cards){
        if(this.activitylevel != ActivityLevel.INACTIVE){
            for(int cardPosition = 0;cardPosition<5;cardPosition++){
                programOneCard(cards[cardPosition], cardPosition);
            }
        }
         this.ready = true;
    }

    public void program(Card card){
        Card[] cards = {card, new DoNothingCard(), new DoNothingCard(), new DoNothingCard(), new DoNothingCard()};
        program(cards);
    }

    public void programFromHand(int cardnr){
        this.program(hand.get(cardnr));
    }

    public void programFromHand(int[] cardnrs){
        this.ready = true;
        if(cardnrs.length == 5){
            for(int i = 0; i < 5 - this.lockedCards.size(); i++){
                programOneCard(this.hand.get(cardnrs[i]), i);
            }
            int count = 0;
            for(int i = this.hand.size(); i < 5; i++){
                programOneCard(this.lockedCards.get(count), i);
                count++;
            }
            this.ready = true;
        }
        else{
            throw new RuntimeException("must program 5 cards");
        }
    }

    public void turnRight(){
        this.orientation = this.orientation.getRight();
    }

    public void turnLeft(){
        this.orientation = this.orientation.getLeft();
    }

    public void turnReverse(){
        this.orientation = this.orientation.getReverse();
    }

    public void respawnIfNecessary(Board board, List<Robot> robots){
        if(!this.onBoard && this.lives >= 0){
            this.xCoordinate = this.respawnX;
            this.yCoordinate = this.respawnY;
            this.orientation = board.getSquare(this.xCoordinate, this.yCoordinate).getRespawnDirection();
            this.health = 9;
            for(Robot r: robots){
                if(!r.equals(this)&& r.getXCoordinate() == this.respawnX && r.getYCoordinate() == this.respawnY){
                    r.moveToSurroundingSquare(board, robots);
                }
            }
            this.onBoard = true;
        }
    }

    private void moveToSurroundingSquare(Board board, List<Robot> robots){
        for(int attempt = 1 ; attempt<9;attempt++){
            if(moveToSurroundingSquareAttempt(board, robots, attempt)) break;
        }

    }

    private boolean moveToSurroundingSquareAttempt(Board board, List<Robot> robots, int attempt){
        switch(attempt){
            case 1:
                if( nextSquareIsFree(board, robots, 1,0) ) return true;
                break;
            case 2:
                if( nextSquareIsFree(board, robots, 0,1) ) return true;
                break;
            case 3:
                if( nextSquareIsFree(board, robots, -1,0) ) return true;
                break;
            case 4:
                if( nextSquareIsFree(board, robots, 0,-1) ) return true;
                break;
            case 5:
                if( nextSquareIsFree(board, robots, 1,1) ) return true;
                break;
            case 6:
                if( nextSquareIsFree(board, robots, -1,1) ) return true;
                break;
            case 7:
                if( nextSquareIsFree(board, robots, 1,-1) ) return true;
                break;
            case 8:
                if( nextSquareIsFree(board, robots, -1,-1) ) return true;
                break;
        }
        return false;

    }

    private boolean nextSquareIsFree(Board board, List<Robot> robots, int xDistance, int yDistance){
        int xCoor = getXCoordinate() + xDistance;
        int yCoor = getYCoordinate() + yDistance;
        for(Robot r: robots){
            if( r.isOnCoordinates(xCoor, yCoor) && board.squareExists(xCoor, xCoor) ){
                this.xCoordinate += xDistance;
                this.yCoordinate += yDistance;
                return true;
             }
        }
        return false;
    }

    private boolean isOnCoordinates(int x, int y){
        return getXCoordinate() == x && getYCoordinate() == y;
    }

    public void setXCoordinate(int xCoordinate){
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(int yCoordinate){
        this.yCoordinate = yCoordinate;
    }

    public int getRespawnXCoordinate(){
        return this.respawnX;
    }

    public int getRespawnYCoordinate(){
        return this.respawnY;
    }

    public static Comparator<Robot> COMPARE_BY_CARD(int registernr){
        return new Comparator<Robot>() {
            @Override
            public int compare(Robot robot1, Robot robot2) {
                return robot1.getCard(registernr).getSpeed() - robot2.getCard(registernr).getSpeed();
            }
        };
    };
    
    public static Comparator<Robot> COMPARE_BY_NAME = new Comparator<Robot>(){
        @Override
        public int compare(Robot robot1, Robot robot2) {
            return robot1.getName().compareTo(robot2.getName());
        }
    };

    public void unready(){
        this.ready = false;
    }

    public boolean isReady(){
        return this.ready;
    }

    public void hasExecutedNextMove(){
        this.wantsToExecuteNextMove = false;
    }

    public boolean readyForNextMove(){
        return this.wantsToExecuteNextMove;
    }

    public void wantsToExecuteNextMove(){
        this.wantsToExecuteNextMove = true;
    }

    public void drawCards(Deck deck){
        this.hand = deck.createHand(9-getHealth()); 
    }
    
    public List<Card> getHand(){
        return this.hand;
    }

    public void clearHand(Deck deck){
        this.lockedCards.clear();
        if(this.health > 4){
            for(Card card:this.hand){
                deck.addCard(card);
            }
            this.hand.clear();
        }else if(this.health > 0 && this.health < 5){
            lockCards(deck, 5 - this.health);
        }else{
            lockCards(deck, 5);
        }
    }

    private void lockCards(Deck deck, int nrOfCardsToLock){
        List<Card> disposableCards = new ArrayList<>();
        for(Card card: this.hand){
            if(!Arrays.asList(programmedCards).contains(card)){
                disposableCards.add(card);
                deck.addCard(card);
            }
        }
        int position = 0 ;
        for(Card card:this.programmedCards){
            if(position < 5 - nrOfCardsToLock){
                disposableCards.add(card);
                deck.addCard(card);
            }else{
                this.lockedCards.add(card);
            }
            position++;
        }
        this.hand.removeAll(disposableCards);
    }    

    public List<Card> getLockedCards(){
        return this.lockedCards;
    }

    public boolean isInactive(){
        return this.activitylevel == ActivityLevel.INACTIVE;
    }

    public boolean isPoweringDown(){
        return this.activitylevel == ActivityLevel.POWERINGDOWN;
    }

    public void shutDown(Deck d){
        this.activitylevel = ActivityLevel.INACTIVE;
        for(int i=0;i<5;i++){
            this.programmedCards[i] = new DoNothingCard();
        }
        for(Card card: this.lockedCards){
            d.addCard(card);
        }
        this.health = 9;
    }

    public void activate(){
        this.activitylevel = ActivityLevel.ACTIVE;
    }

    public void cyclePowerState(Deck d){
        if(this.activitylevel == ActivityLevel.POWERINGDOWN){
            this.shutDown(d);
        }
        else if(this.activitylevel == ActivityLevel.INACTIVE){
            this.activate();
        }
    }

    public void fireLaser(List<Robot> robots, Board board){
        if(isOnBoard()){
            switch(this.orientation){
                case NORTH: fireNorth(robots, board);
                            break;
                case EAST: fireEast(robots, board);
                            break;                        
                case SOUTH: fireSouth(robots, board);
                            break;            
                case WEST: fireWest(robots, board);
                            break;
            }
        }
    }

    private void fireNorth(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
        if(!currentSquare.hasWallAt(Direction.NORTH)){
            yCoordinate--;
            while(yCoordinate >= 0){
                boolean hitRobot = false;
                for(Robot robot : robots){
                    hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, 1);
                }
                currentSquare = board.getSquare(xCoordinate, yCoordinate);
                if(hitRobot || currentSquare.hasWallAt(Direction.NORTH)){
                    break;
                }
                yCoordinate--;
            }
        }
    }

    private void fireEast(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
        if(!currentSquare.hasWallAt(Direction.EAST)){
            xCoordinate++;
            while(xCoordinate < board.getWidth()){
                boolean hitRobot = false;
                for(Robot robot : robots){
                    hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, 1);
                }
                currentSquare = board.getSquare(xCoordinate, yCoordinate);
                if(hitRobot || currentSquare.hasWallAt(Direction.EAST)){
                    break;
                }
                xCoordinate++;
            }
        }       
    }

    private void fireSouth(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
        if(!currentSquare.hasWallAt(Direction.SOUTH)){
            yCoordinate++;
            while(yCoordinate < board.getHeight()){
                boolean hitRobot = false;
                for(Robot robot : robots){
                    hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, 1);
                }
                currentSquare = board.getSquare(xCoordinate, yCoordinate);
                if(hitRobot || currentSquare.hasWallAt(Direction.SOUTH)){
                    break;
                }
                yCoordinate++;
            }
        }       
    }

    private void fireWest(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
        if(!currentSquare.hasWallAt(Direction.WEST)){
            xCoordinate--;
            while(xCoordinate >= 0){
                boolean hitRobot = false;
                for(Robot robot : robots){
                    hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, 1);
                }
                currentSquare = board.getSquare(xCoordinate, yCoordinate);
                if(hitRobot || currentSquare.hasWallAt(Direction.WEST)){
                    break;
                }
                xCoordinate--;
            }
        }  
    }

    public void turnOnOrOff(){
        if(this.activitylevel == ActivityLevel.ACTIVE) this.activitylevel = ActivityLevel.POWERINGDOWN;
        else if(this.activitylevel == ActivityLevel.POWERINGDOWN) this.activitylevel = ActivityLevel.ACTIVE;
    }

    public void setToWinner(){
        this.hasWonTheGame = true;
    }

    public boolean isWinner(){
        if(this.hasWonTheGame) return true;
        return false;
    }

    public void reachCheckpoint(){
        this.respawnX = getXCoordinate();
        this.respawnY = getYCoordinate();
        this.hasReachedCheckpoint = true;
    }

    public boolean hasReachedCheckpoint(){
        if(this.hasReachedCheckpoint) return true;
        return false;
    }

	public void updateCurrentCard() {
    }
    
    public boolean isOnBoard(){
        return this.onBoard;
    }

    public void setOffBoard(){
        this.onBoard = false;
        this.xCoordinate = -1;
        this.yCoordinate = -1;
        this.lives--;
    }

    public boolean isAlive(){
        if(this.lives > 0) return true;
        return false;
    }

}