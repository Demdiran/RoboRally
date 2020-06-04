package nl.sogyo.roborally.domain.squares;

import java.util.List;

import nl.sogyo.roborally.domain.*;
import nl.sogyo.roborally.domain.robots.Robot;

public abstract class Square{
    protected boolean northWall;
    protected boolean eastWall;
    protected boolean southWall;
    protected boolean westWall;
    protected boolean respawnSquare;
    protected Direction dir;

    public Square(){
    }

    public Square(String input){
		if(input.contains("N")){
			this.northWall = true;
		}
		if(input.contains("E")){
			this.eastWall = true;
        }
        if(input.contains("S")){
			this.southWall = true;
		}
		if(input.contains("W")){
			this.westWall = true;
        }
        if(input.contains("R")){
            this.respawnSquare = true;
            setDirection(input);
        }
    }

    public boolean hasWallAt(Direction direction){
        switch(direction){
            case NORTH: return northWall;
            case EAST: return eastWall;
            case SOUTH: return southWall;
            case WEST: return westWall;
            default: throw new RuntimeException("Somehow there are more than four directions.");
        }
    }

    private void setDirection(String str){       
        if(str.contains("e"))  this.dir = Direction.EAST;
		if(str.contains("w"))  this.dir = Direction.WEST;       
        if(str.contains("n"))  this.dir = Direction.NORTH;
		if(str.contains("s"))  this.dir = Direction.SOUTH;        
    }
    
    public abstract String getType();

    public abstract void doSquareAction(Robot robot, Board board, List<Robot> robots);
}