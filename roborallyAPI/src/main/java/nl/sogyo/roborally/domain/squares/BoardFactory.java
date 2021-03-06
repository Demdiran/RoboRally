package nl.sogyo.roborally.domain.squares;

import java.util.ArrayList;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.elements.Laser;

public class BoardFactory{
	public static Board createSimpleBoard(){
		Board simpleBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		square00.eastWall = true;
		square00.southWall = true;
		square10.westWall = true;
		square01.northWall = true;
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row2.add(square01);
		row2.add(square11);
		simpleBoard.addRow(row1);
		simpleBoard.addRow(row2);
		return simpleBoard;
	}

	public static Board createWrongWalls(){
		//square00 needs an eastwall
		//square10 needs a northwall
		Board wrongWallsBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		square00.southWall = true;
		square10.westWall = true;
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row2.add(square01);
		row2.add(square11);
		wrongWallsBoard.addRow(row1);
		wrongWallsBoard.addRow(row2);
		return wrongWallsBoard;
	}

	public static Board createNonRectangularBoard(){
		Board simpleBoard = new Board();
		Square square00 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square10 = new EmptySquare();
		square00.eastWall = true;
		square00.southWall = true;
		square10.westWall = true;
		square01.northWall = true;
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row2.add(square01);
		simpleBoard.addRow(row1);
		simpleBoard.addRow(row2);
		return simpleBoard;
	}

	public static Board createBoardlaserTestBoard(){
		Board laserTestBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		Square square02 = new EmptySquare();
		Square square12 = new EmptySquare();

		square00.northWall = true;

		square00.southWall = true;
		square01.northWall = true;
		
		square01.westWall = true;

		square10.southWall = true;
		square11.northWall = true;

		square11.southWall = true;
		square12.northWall = true;

		square12.eastWall = true;
		
		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(square01);
		row2.add(square11);
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(square02);
		row3.add(square12);
		laserTestBoard.addRow(row1);
		laserTestBoard.addRow(row2);
		laserTestBoard.addRow(row3);

		Laser laser1 = new Laser(0,0, Direction.SOUTH, 1);
		Laser laser2 = new Laser(0,1, Direction.EAST, 2);
		Laser laser3 = new Laser(1,1, Direction.NORTH, 1);
		Laser laser4 = new Laser(1,2, Direction.WEST, 3);

		laserTestBoard.addLaser(laser1);
		laserTestBoard.addLaser(laser2);
		laserTestBoard.addLaser(laser3);
		laserTestBoard.addLaser(laser4);

		return laserTestBoard;
	}

	public static Board createFaultyLaserTestBoard(){
		Board faultyLaserTestBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		Square square02 = new EmptySquare();
		Square square12 = new EmptySquare();


		square00.southWall = true;
		square01.northWall = true;

		square10.southWall = true;
		square11.northWall = true;

		
		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(square01);
		row2.add(square11);
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(square02);
		row3.add(square12);
		faultyLaserTestBoard.addRow(row1);
		faultyLaserTestBoard.addRow(row2);
		faultyLaserTestBoard.addRow(row3);
		Laser laser1 = new Laser(0,0, Direction.SOUTH, 1);
		Laser laser2 = new Laser(0,1, Direction.EAST, 1);
		Laser laser3 = new Laser(1,2, Direction.NORTH, 1);

		faultyLaserTestBoard.addLaser(laser1);
		faultyLaserTestBoard.addLaser(laser2);
		faultyLaserTestBoard.addLaser(laser3);

		return faultyLaserTestBoard;		
	}

	public static Board createSlowConveyorbeltTestBoard(){
		Board conveyorbeltTestBoard = new Board();

		Square square00 = new SlowConveyorbelt(Direction.EAST);
		Square square10 = new EmptySquare();
		Square square20 = new SlowConveyorbelt(Direction.SOUTH);
		
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		Square square21 = new EmptySquare();
		
		Square square02 = new SlowConveyorbelt(Direction.NORTH);
		Square square12 = new EmptySquare();
		Square square22 = new SlowConveyorbelt(Direction.WEST);

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row1.add(square20);
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(square01);
		row2.add(square11);
		row2.add(square21);
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(square02);
		row3.add(square12);
		row3.add(square22);
		
		conveyorbeltTestBoard.addRow(row1);
		conveyorbeltTestBoard.addRow(row2);
		conveyorbeltTestBoard.addRow(row3);

		return conveyorbeltTestBoard;
	}

	public static Board createRobotLaserWallTestBoard(){
		Board robotLaserTestBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		square00.eastWall = true;
		square00.southWall = true;
		square10.westWall = true;
		square10.southWall = true;
		square01.northWall = true;
		square01.eastWall = true;
		square11.northWall = true;
		square11.westWall = true;
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row2.add(square01);
		row2.add(square11);
		robotLaserTestBoard.addRow(row1);
		robotLaserTestBoard.addRow(row2);
		return robotLaserTestBoard;

	}

	public static Board createGearTestBoard(){
		Board gearTestBoard = new Board();

		Square square00 = new GearLeft();
		Square square10 = new GearRight();
		Square square20 = new Gear180();

		ArrayList<Square> row = new ArrayList<>();
		row.add(square00);
		row.add(square10);
		row.add(square20);

		gearTestBoard.addRow(row);

		return gearTestBoard;
	}

	public static Board createSlowConveyorbeltTestBoardOther(){
		Board conveyorbeltTestBoard = new Board();

		Square square00 = new SlowConveyorbelt(Direction.EAST);
		square00.eastWall = true;
		Square square10 = new EmptySquare();
		square10.westWall = true;
		Square square20 = new SlowConveyorbelt(Direction.SOUTH);
		
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		Square square21 = new Pit();
		
		Square square02 = new SlowConveyorbelt(Direction.NORTH);
		Square square12 = new EmptySquare();
		Square square22 = new SlowConveyorbelt(Direction.SOUTH);

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row1.add(square20);
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(square01);
		row2.add(square11);
		row2.add(square21);
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(square02);
		row3.add(square12);
		row3.add(square22);

		conveyorbeltTestBoard.addRow(row1);
		conveyorbeltTestBoard.addRow(row2);
		conveyorbeltTestBoard.addRow(row3);

		return conveyorbeltTestBoard;
	}

	public static Board createSlowConveyorbeltRotationTestBoard(){
		Board slowConveryorbeltRotationTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.EAST));
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new SlowConveyorbelt(Direction.NORTH));
		row4.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new SlowConveyorbelt(Direction.EAST));
		row5.add(new SlowConveyorbelt(Direction.WEST));

		slowConveryorbeltRotationTestBoard.addRow(row1);
		slowConveryorbeltRotationTestBoard.addRow(row2);
		slowConveryorbeltRotationTestBoard.addRow(row3);
		slowConveryorbeltRotationTestBoard.addRow(row4);
		slowConveryorbeltRotationTestBoard.addRow(row5);

		return slowConveryorbeltRotationTestBoard;
	}

	public static Board createMultipleRobotsOnSlowConveyorbeltTestBoard(){
		Board multipleRobotsOnSlowConveyorbeltTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new SlowConveyorbelt(Direction.NORTH, "N"));
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new SlowConveyorbelt(Direction.NORTH));
		row4.add(new SlowConveyorbelt(Direction.NORTH));

		multipleRobotsOnSlowConveyorbeltTestBoard.addRow(row1);
		multipleRobotsOnSlowConveyorbeltTestBoard.addRow(row2);
		multipleRobotsOnSlowConveyorbeltTestBoard.addRow(row3);
		multipleRobotsOnSlowConveyorbeltTestBoard.addRow(row4);

		return multipleRobotsOnSlowConveyorbeltTestBoard;
	}

	public static Board createMultipleRobotsOnSlowConveyorbeltWithPitTestBoard(){
		Board multipleRobotsOnSlowConveyorbeltWithPitTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new SlowConveyorbelt(Direction.NORTH));
		row1.add(new Pit());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new SlowConveyorbelt(Direction.NORTH));
		row4.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());

		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());

		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row1);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row2);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row3);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row4);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row5);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row6);

		return multipleRobotsOnSlowConveyorbeltWithPitTestBoard;
	}

	public static Board createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard(){
		Board multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new SlowConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.EAST));
		row2.add(new Pit());
		row2.add(new SlowConveyorbelt(Direction.WEST));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new EmptySquare());

		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard.addRow(row1);
		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard.addRow(row2);
		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard.addRow(row3);
		
		return multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard;
	}

	public static Board createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard(){
		Board multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new SlowConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.EAST));
		row2.add(new EmptySquare());
		row2.add(new SlowConveyorbelt(Direction.WEST));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new EmptySquare());

		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard.addRow(row1);
		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard.addRow(row2);
		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard.addRow(row3);
		
		return multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard;
	}

	public static Board createPitTestBoard(){
		Board pitTestBoard = new Board();

		Square square0 = new EmptySquare();
		Square square1 = new EmptySquare();
		Square square2 = new EmptySquare();
		Square pit = new Pit();

		ArrayList<Square> row = new ArrayList<>();
		row.add(square0);
		row.add(square1);
		row.add(square2);
		row.add(pit);

		pitTestBoard.addRow(row);

		return pitTestBoard;
	}

	public static Board createTESTBOARD4X4(){
		Board defaultBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare("Rs"));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare("N"));
		row1.add(new EmptySquare());

		
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare("WRs"));
		row2.add(new Checkpoint());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare("Rs"));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare("E"));
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare("Rs"));
		row4.add(new EmptySquare("S"));
		row4.add(new EmptySquare());
		row4.add(new FinalCheckPoint());

		defaultBoard.addRow(row1);
		defaultBoard.addRow(row2);
		defaultBoard.addRow(row3);
		defaultBoard.addRow(row4);

		return defaultBoard;
	
	}

	public static Board createWinningBoard(){
		Board winningBoard = new Board();
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new FinalCheckPoint());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new Checkpoint());
		winningBoard.addRow(row1);
		winningBoard.addRow(row2);
		return winningBoard;
	} 

	public static Board createLockingCardsTestBoard(){
		Board winningBoard = new Board();
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		ArrayList<Square> row3 = new ArrayList<>();
		row1.add(new EmptySquare("N"));
		row1.add(new EmptySquare("N"));
		row1.add(new EmptySquare("N"));
		row2.add(new EmptySquare("W"));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row3.add(new EmptySquare("S"));
		row3.add(new EmptySquare("S"));
		row3.add(new EmptySquare("S"));
		Laser laser1 = new Laser(0,1, Direction.EAST, 1);
		winningBoard.addRow(row1);
		winningBoard.addRow(row2);
		winningBoard.addRow(row3);
		winningBoard.addLaser(laser1);
		return winningBoard;
	} 

	public static Board createSmallCompleteBoard(){
		Board smallCompleteBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare("S"));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare("S"));
		row1.add(new EmptySquare());
		
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare("E"));
		row2.add(new GearRight("NW"));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new GearLeft("NE"));
		row2.add(new EmptySquare("W"));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new Checkpoint());
		row3.add(new SlowConveyorbelt(Direction.EAST));
		row3.add(new SlowConveyorbelt(Direction.SOUTH));
		row3.add(new Pit());
		row3.add(new EmptySquare());
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new Pit());
		row4.add(new SlowConveyorbelt(Direction.NORTH));
		row4.add(new SlowConveyorbelt(Direction.WEST));
		row4.add(new Checkpoint());
		row4.add(new EmptySquare());
		
		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare("E"));
		row5.add(new Gear180("SW"));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new Gear180("SE"));
		row5.add(new EmptySquare("W"));
		
		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare());
		row6.add(new EmptySquare("N"));
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare("N"));
		row6.add(new FinalCheckPoint());

		smallCompleteBoard.addRow(row1);
		smallCompleteBoard.addRow(row2);
		smallCompleteBoard.addRow(row3);
		smallCompleteBoard.addRow(row4);
		smallCompleteBoard.addRow(row5);
		smallCompleteBoard.addRow(row6);

		Laser laser1 = new Laser(1, 1, Direction.EAST, 3);
		Laser laser2 = new Laser(4, 1, Direction.SOUTH, 2);
		Laser laser3 = new Laser(4, 4, Direction.WEST, 3);
		Laser laser4 = new Laser(1, 4, Direction.NORTH, 2);

		smallCompleteBoard.addLaser(laser1);
		smallCompleteBoard.addLaser(laser2);
		smallCompleteBoard.addLaser(laser3);
		smallCompleteBoard.addLaser(laser4);
		return smallCompleteBoard;
	}

	public static Board createSmallBoardWithFastConveyers(){
		Board smallFastConveyerBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new FastConveyorbelt(Direction.EAST));
		row1.add(new FastConveyorbelt(Direction.EAST));
		row1.add(new EmptySquare());
		row1.add(new FastConveyorbelt(Direction.WEST));
		row1.add(new FastConveyorbelt(Direction.WEST));

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.EAST, "E"));
		row2.add(new EmptySquare("WE"));
		row2.add(new FastConveyorbelt(Direction.WEST, "W"));
		row2.add(new FastConveyorbelt(Direction.WEST));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new EmptySquare("S"));
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new FastConveyorbelt(Direction.NORTH, "N"));
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.SOUTH, "S"));
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		
		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare("N"));
		row5.add(new EmptySquare());

		smallFastConveyerBoard.addRow(row1);
		smallFastConveyerBoard.addRow(row2);
		smallFastConveyerBoard.addRow(row3);
		smallFastConveyerBoard.addRow(row4);
		smallFastConveyerBoard.addRow(row5);

		return smallFastConveyerBoard;
	}

	public static Board createFastConveyersWithPits(){
		Board smallFastConveyerBoardWithPits = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new FastConveyorbelt(Direction.EAST));
		row1.add(new FastConveyorbelt(Direction.EAST));
		row1.add(new Pit());
		row1.add(new FastConveyorbelt(Direction.NORTH));
		row1.add(new FastConveyorbelt(Direction.SOUTH));

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.NORTH));
		row2.add(new FastConveyorbelt(Direction.SOUTH));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new Pit());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new Pit());
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.EAST));
		row4.add(new FastConveyorbelt(Direction.EAST));
		
		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new FastConveyorbelt(Direction.SOUTH));
		row5.add(new Pit());
		row5.add(new FastConveyorbelt(Direction.WEST));
		row5.add(new FastConveyorbelt(Direction.WEST));

		smallFastConveyerBoardWithPits.addRow(row1);
		smallFastConveyerBoardWithPits.addRow(row2);
		smallFastConveyerBoardWithPits.addRow(row3);
		smallFastConveyerBoardWithPits.addRow(row4);
		smallFastConveyerBoardWithPits.addRow(row5);

		return smallFastConveyerBoardWithPits;
	}

	public static Board createMultipleRobotsPushedOnFastConveyersWithPit(){
		Board smallFastConveyerBoardWithPits = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new FastConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.SOUTH));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new FastConveyorbelt(Direction.EAST));
		row3.add(new FastConveyorbelt(Direction.EAST));
		row3.add(new Pit());
		row3.add(new FastConveyorbelt(Direction.WEST));
		row3.add(new FastConveyorbelt(Direction.WEST));
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		
		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());

		smallFastConveyerBoardWithPits.addRow(row1);
		smallFastConveyerBoardWithPits.addRow(row2);
		smallFastConveyerBoardWithPits.addRow(row3);
		smallFastConveyerBoardWithPits.addRow(row4);
		smallFastConveyerBoardWithPits.addRow(row5);

		return smallFastConveyerBoardWithPits;
	}

		public static Board createMultipleRobotsPushedOnFastConveyersWithoutPit(){
		Board smallFastConveyerBoardWithoutPit = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new FastConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.SOUTH));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new FastConveyorbelt(Direction.EAST));
		row3.add(new FastConveyorbelt(Direction.EAST));
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.WEST));
		row3.add(new FastConveyorbelt(Direction.WEST));
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		
		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());

		smallFastConveyerBoardWithoutPit.addRow(row1);
		smallFastConveyerBoardWithoutPit.addRow(row2);
		smallFastConveyerBoardWithoutPit.addRow(row3);
		smallFastConveyerBoardWithoutPit.addRow(row4);
		smallFastConveyerBoardWithoutPit.addRow(row5);

		return smallFastConveyerBoardWithoutPit;
	}

	public static Board createRepairSquareBoard(){
		Board board = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		ArrayList<Square> row3 = new ArrayList<>();

		row1.add(new EmptySquare("NW"));
		row1.add(new EmptySquare("N"));
		row1.add(new EmptySquare("NE"));

		row2.add(new EmptySquare("W"));
		row2.add(new RepairSquare(2));
		row2.add(new EmptySquare("E"));

		row3.add(new EmptySquare("SW"));
		row3.add(new EmptySquare("S"));
		row3.add(new EmptySquare("SE"));
		Laser laser1 = new Laser(2,0, Direction.SOUTH, 1);
		board.addLaser(laser1);
		board.addRow(row1);
		board.addRow(row2);
		board.addRow(row3);
		return board;
	}

	public static Board createBigFastConveyorBeltTestBoard(){
		Board bigFastConveyorBeltTestBoard = new Board();

		ArrayList<Square> row0 = new ArrayList<>();
		row0.add(new FastConveyorbelt(Direction.NORTH, "N"));
		row0.add(new FastConveyorbelt(Direction.NORTH));
		row0.add(new Pit());
		row0.add(new FastConveyorbelt(Direction.WEST));
		row0.add(new FastConveyorbelt(Direction.WEST));
		row0.add(new FastConveyorbelt(Direction.WEST));
		row0.add(new FastConveyorbelt(Direction.WEST));
		row0.add(new FastConveyorbelt(Direction.WEST));
		row0.add(new FastConveyorbelt(Direction.WEST));
		row0.add(new EmptySquare());

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new FastConveyorbelt(Direction.NORTH));
		row1.add(new FastConveyorbelt(Direction.NORTH));
		row1.add(new FastConveyorbelt(Direction.NORTH));
		row1.add(new FastConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new FastConveyorbelt(Direction.NORTH));
		
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new FastConveyorbelt(Direction.NORTH));
		row2.add(new FastConveyorbelt(Direction.NORTH));
		row2.add(new FastConveyorbelt(Direction.NORTH));
		row2.add(new FastConveyorbelt(Direction.SOUTH));
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.NORTH));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new FastConveyorbelt(Direction.NORTH));
		row3.add(new FastConveyorbelt(Direction.NORTH));
		row3.add(new FastConveyorbelt(Direction.NORTH));
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.NORTH));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.NORTH));
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.NORTH));

		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new EmptySquare());
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new FastConveyorbelt(Direction.NORTH));

		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new FastConveyorbelt(Direction.NORTH));
		row6.add(new FastConveyorbelt(Direction.NORTH));
		row6.add(new FastConveyorbelt(Direction.NORTH));
		row6.add(new FastConveyorbelt(Direction.NORTH));
		row6.add(new EmptySquare());
		row6.add(new FastConveyorbelt(Direction.NORTH));
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new FastConveyorbelt(Direction.NORTH));

		ArrayList<Square> row7 = new ArrayList<>();
		row7.add(new FastConveyorbelt(Direction.NORTH));
		row7.add(new FastConveyorbelt(Direction.NORTH));
		row7.add(new FastConveyorbelt(Direction.NORTH));
		row7.add(new FastConveyorbelt(Direction.NORTH));
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new FastConveyorbelt(Direction.NORTH));

		ArrayList<Square> row8 = new ArrayList<>();
		row8.add(new FastConveyorbelt(Direction.NORTH));
		row8.add(new FastConveyorbelt(Direction.NORTH));
		row8.add(new FastConveyorbelt(Direction.NORTH));
		row8.add(new FastConveyorbelt(Direction.NORTH));
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new FastConveyorbelt(Direction.NORTH));

		ArrayList<Square> row9 = new ArrayList<>();
		row9.add(new FastConveyorbelt(Direction.NORTH));
		row9.add(new FastConveyorbelt(Direction.NORTH));
		row9.add(new FastConveyorbelt(Direction.NORTH));
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new FastConveyorbelt(Direction.NORTH));

		bigFastConveyorBeltTestBoard.addRow(row0);
		bigFastConveyorBeltTestBoard.addRow(row1);
		bigFastConveyorBeltTestBoard.addRow(row2);
		bigFastConveyorBeltTestBoard.addRow(row3);
		bigFastConveyorBeltTestBoard.addRow(row4);
		bigFastConveyorBeltTestBoard.addRow(row5);
		bigFastConveyorBeltTestBoard.addRow(row6);
		bigFastConveyorBeltTestBoard.addRow(row7);
		bigFastConveyorBeltTestBoard.addRow(row8);
		bigFastConveyorBeltTestBoard.addRow(row9);

		return bigFastConveyorBeltTestBoard;
	}

	public static Board createFastConveyorBeltFrontEndGameBoard(){
		Board fastConveyorBeltFrontEndGameBoard = new Board();

		ArrayList<Square> row0 = new ArrayList<>();
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new Pit());

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new FastConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare("E"));
		row1.add(new EmptySquare("W"));
		row1.add(new FastConveyorbelt(Direction.NORTH));
		row1.add(new EmptySquare());
		row1.add(new GearRight());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.SOUTH));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.NORTH));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new EmptySquare("E"));
		row3.add(new EmptySquare("W"));
		row3.add(new FastConveyorbelt(Direction.NORTH));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new GearLeft());
		row3.add(new EmptySquare());
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new EmptySquare());
		row4.add(new SlowConveyorbelt(Direction.EAST));
		row4.add(new SlowConveyorbelt(Direction.SOUTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());

		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare());
		row5.add(new FastConveyorbelt(Direction.SOUTH));
		row5.add(new EmptySquare("E"));
		row5.add(new EmptySquare("W"));
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new EmptySquare());
		row5.add(new SlowConveyorbelt(Direction.NORTH));
		row5.add(new SlowConveyorbelt(Direction.WEST));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());

		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());

		ArrayList<Square> row7 = new ArrayList<>();
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new FastConveyorbelt(Direction.EAST));
		row7.add(new FastConveyorbelt(Direction.EAST, "S"));
		row7.add(new FastConveyorbelt(Direction.EAST, "S"));
		row7.add(new FastConveyorbelt(Direction.EAST, "S"));
		row7.add(new FastConveyorbelt(Direction.EAST));
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());

		ArrayList<Square> row8 = new ArrayList<>();
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new FastConveyorbelt(Direction.WEST));
		row8.add(new FastConveyorbelt(Direction.WEST, "N"));
		row8.add(new FastConveyorbelt(Direction.WEST, "N"));
		row8.add(new FastConveyorbelt(Direction.WEST, "N"));
		row8.add(new FastConveyorbelt(Direction.WEST));
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());

		ArrayList<Square> row9 = new ArrayList<>();
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());

		fastConveyorBeltFrontEndGameBoard.addRow(row0);
		fastConveyorBeltFrontEndGameBoard.addRow(row1);
		fastConveyorBeltFrontEndGameBoard.addRow(row2);
		fastConveyorBeltFrontEndGameBoard.addRow(row3);
		fastConveyorBeltFrontEndGameBoard.addRow(row4);
		fastConveyorBeltFrontEndGameBoard.addRow(row5);
		fastConveyorBeltFrontEndGameBoard.addRow(row6);
		fastConveyorBeltFrontEndGameBoard.addRow(row7);
		fastConveyorBeltFrontEndGameBoard.addRow(row8);
		fastConveyorBeltFrontEndGameBoard.addRow(row9);

		return fastConveyorBeltFrontEndGameBoard;
	}

	public static Board createFastSlowConveyorsInteractionTestBoard(){
		Board fastSlowConveyorsInteractionTestBoard = new Board();

		ArrayList<Square> row0 = new ArrayList<>();
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new FastConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());
		row1.add(new FastConveyorbelt(Direction.NORTH));
		row1.add(new SlowConveyorbelt(Direction.EAST));
		row1.add(new SlowConveyorbelt(Direction.EAST));
		row1.add(new SlowConveyorbelt(Direction.EAST));
		row1.add(new FastConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.SOUTH));
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.NORTH));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.SOUTH));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.NORTH));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());

		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare());
		row5.add(new SlowConveyorbelt(Direction.SOUTH));
		row5.add(new EmptySquare());
		row5.add(new SlowConveyorbelt(Direction.NORTH));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());

		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare());
		row6.add(new SlowConveyorbelt(Direction.SOUTH));
		row6.add(new EmptySquare());
		row6.add(new SlowConveyorbelt(Direction.NORTH));
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new SlowConveyorbelt(Direction.NORTH));
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());

		ArrayList<Square> row7 = new ArrayList<>();
		row7.add(new EmptySquare());
		row7.add(new SlowConveyorbelt(Direction.SOUTH));
		row7.add(new EmptySquare());
		row7.add(new SlowConveyorbelt(Direction.NORTH));
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new SlowConveyorbelt(Direction.NORTH));
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());

		ArrayList<Square> row8 = new ArrayList<>();
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new SlowConveyorbelt(Direction.NORTH));
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());

		ArrayList<Square> row9 = new ArrayList<>();
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new FastConveyorbelt(Direction.EAST));
		row9.add(new FastConveyorbelt(Direction.EAST));
		row9.add(new FastConveyorbelt(Direction.EAST));
		row9.add(new FastConveyorbelt(Direction.EAST));
		row9.add(new SlowConveyorbelt(Direction.NORTH));
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());

		fastSlowConveyorsInteractionTestBoard.addRow(row0);
		fastSlowConveyorsInteractionTestBoard.addRow(row1);
		fastSlowConveyorsInteractionTestBoard.addRow(row2);
		fastSlowConveyorsInteractionTestBoard.addRow(row3);
		fastSlowConveyorsInteractionTestBoard.addRow(row4);
		fastSlowConveyorsInteractionTestBoard.addRow(row5);
		fastSlowConveyorsInteractionTestBoard.addRow(row6);
		fastSlowConveyorsInteractionTestBoard.addRow(row7);
		fastSlowConveyorsInteractionTestBoard.addRow(row8);
		fastSlowConveyorsInteractionTestBoard.addRow(row9);

		return fastSlowConveyorsInteractionTestBoard;
	}

	public static Board createWinningFromBeltTestBoard(){
		Board winningFromBeltTestBoard = new Board();

		ArrayList<Square> row0 = new ArrayList<>();
		row0.add(new EmptySquare());
		row0.add(new SlowConveyorbelt(Direction.SOUTH));
		row0.add(new EmptySquare());

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new SlowConveyorbelt(Direction.EAST));
		row1.add(new FinalCheckPoint());
		row1.add(new SlowConveyorbelt(Direction.WEST));

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare());
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		row2.add(new EmptySquare());

		winningFromBeltTestBoard.addRow(row0);
		winningFromBeltTestBoard.addRow(row1);
		winningFromBeltTestBoard.addRow(row2);

		return winningFromBeltTestBoard;
	}

	// ------------------ GAME BOARDS ------------------------

	public static Board createBeginner12x12GameboardCorridorBlitz(){
		Board board = new Board();

		ArrayList<Square> row0 = new ArrayList<>();
		row0.add(new EmptySquare());
		row0.add(new FastConveyorbelt(Direction.SOUTH));
		row0.add(new EmptySquare("S"));
		row0.add(new EmptySquare());
		row0.add(new Pit());
		row0.add(new RepairSquare(3));
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new SlowConveyorbelt(Direction.NORTH));
		row0.add(new RepairSquare(2));

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new SlowConveyorbelt(Direction.WEST));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare("NE"));
		row1.add(new EmptySquare("W"));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare("SE"));
		row1.add(new EmptySquare("W"));
		row1.add(new EmptySquare());
		row1.add(new FastConveyorbelt(Direction.WEST));
	
		Laser laser1 = new Laser(3,1, Direction.EAST, 2);

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.SOUTH));
		row2.add(new EmptySquare("N"));
		row2.add(new FinalCheckPoint());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		
		Laser laser2 = new Laser(8,2, Direction.SOUTH, 2);
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new Checkpoint());
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.NORTH));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new RepairSquare(3));
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new EmptySquare());
		row3.add(new Pit());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new Pit());
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new GearRight());
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());

		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new EmptySquare("S"));
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new FastConveyorbelt(Direction.WEST));
		row5.add(new FastConveyorbelt(Direction.WEST));
		row5.add(new FastConveyorbelt(Direction.WEST));
		row5.add(new FastConveyorbelt(Direction.WEST));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());

		Laser laser3 = new Laser(2,5, Direction.NORTH, 2);

		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare());
		row6.add(new EmptySquare("E"));
		row6.add(new EmptySquare("NW"));
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare("E"));
		row6.add(new EmptySquare("SW"));
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());

		Laser laser4 = new Laser(7,6, Direction.WEST, 2);

		ArrayList<Square> row7 = new ArrayList<>();
		row7.add(new FastConveyorbelt(Direction.EAST));
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new RepairSquare(1));
		row7.add(new Pit());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare("N"));
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new SlowConveyorbelt(Direction.WEST));

		ArrayList<Square> row8 = new ArrayList<>();
		row8.add(new RepairSquare(2));
		row8.add(new SlowConveyorbelt(Direction.SOUTH));
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare("S"));
		row8.add(new Gear180("S"));
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new FastConveyorbelt(Direction.NORTH));
		row8.add(new EmptySquare());

		ArrayList<Square> row9 = new ArrayList<>();
		row9.add(new SlowConveyorbelt(Direction.NORTH));
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare("N"));
		row9.add(new EmptySquare("N"));
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row10 = new ArrayList<>();
		row10.add(new EmptySquare());
		row10.add(new EmptySquare("RnE"));
		row10.add(new EmptySquare("W"));
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare("Rn"));
		row10.add(new EmptySquare("Rn"));
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare("E"));
		row10.add(new EmptySquare("RnW"));

		ArrayList<Square> row11 = new ArrayList<>();
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare("Rn"));
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare("Rn"));
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());

		board.addRow(row0);
		board.addRow(row1);
		board.addRow(row2);
		board.addRow(row3);
		board.addRow(row4);
		board.addRow(row5);
		board.addRow(row6);
		board.addRow(row7);
		board.addRow(row8);
		board.addRow(row9);
		board.addRow(row10);
		board.addRow(row11);

		board.addLaser(laser1);
		board.addLaser(laser2);
		board.addLaser(laser3);
		board.addLaser(laser4);

		return board;
	}

	public static Board createIntermediate12x12GameboardPassingLane(){
		Board board = new Board();

		ArrayList<Square> row0 = new ArrayList<>();
		row0.add(new EmptySquare());
		row0.add(new EmptySquare("E"));
		row0.add(new EmptySquare("W"));
		row0.add(new FastConveyorbelt(Direction.SOUTH));
		row0.add(new FastConveyorbelt(Direction.SOUTH));
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		
		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare("ReE"));
		row1.add(new RepairSquare(1, "W"));
		row1.add(new FastConveyorbelt(Direction.SOUTH));
		row1.add(new FastConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new Gear180());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));

		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare("Re"));
		row3.add(new EmptySquare("SE"));
		row3.add(new EmptySquare("W"));
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare("S"));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new RepairSquare(2));
		row3.add(new FastConveyorbelt(Direction.NORTH));
		row3.add(new FastConveyorbelt(Direction.WEST));
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new EmptySquare("N"));
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare("N"));
		row4.add(new FinalCheckPoint("E"));
		row4.add(new EmptySquare("W"));
		row4.add(new EmptySquare("E"));
		row4.add(new EmptySquare("W"));
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new EmptySquare());

		Laser laser1 = new Laser(7,4, Direction.EAST, 3);

		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare());
		row5.add(new EmptySquare("ReE"));
		row5.add(new EmptySquare("W"));
		row5.add(new FastConveyorbelt(Direction.SOUTH));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare("S"));
		row5.add(new RepairSquare(3));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare("S"));
		row5.add(new EmptySquare());
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new EmptySquare());

		Laser laser2 = new Laser(5,5, Direction.NORTH, 2);

		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare());
		row6.add(new EmptySquare("ReE"));
		row6.add(new EmptySquare("W"));
		row6.add(new FastConveyorbelt(Direction.SOUTH));
		row6.add(new EmptySquare());
		row6.add(new EmptySquare("N"));
		row6.add(new EmptySquare());
		row6.add(new RepairSquare(3));
		row6.add(new EmptySquare("N"));
		row6.add(new EmptySquare());
		row6.add(new FastConveyorbelt(Direction.NORTH));
		row6.add(new EmptySquare());

		Laser laser3 = new Laser(8,6, Direction.SOUTH, 2);

		ArrayList<Square> row7 = new ArrayList<>();
		row7.add(new EmptySquare());
		row7.add(new EmptySquare("S"));
		row7.add(new EmptySquare());
		row7.add(new FastConveyorbelt(Direction.SOUTH));
		row7.add(new EmptySquare("E"));
		row7.add(new EmptySquare("W"));
		row7.add(new EmptySquare("E"));
		row7.add(new EmptySquare("W"));
		row7.add(new EmptySquare("S"));
		row7.add(new EmptySquare());
		row7.add(new FastConveyorbelt(Direction.NORTH));
		row7.add(new EmptySquare());

		Laser laser4 = new Laser(6,7, Direction.WEST, 3);

		ArrayList<Square> row8 = new ArrayList<>();
		row8.add(new EmptySquare("Re"));
		row8.add(new EmptySquare("NE"));
		row8.add(new EmptySquare("W"));
		row8.add(new FastConveyorbelt(Direction.SOUTH));
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare("N"));
		row8.add(new EmptySquare());
		row8.add(new FastConveyorbelt(Direction.NORTH));
		row8.add(new Checkpoint());

		ArrayList<Square> row9 = new ArrayList<>();
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new FastConveyorbelt(Direction.SOUTH));
		row9.add(new RepairSquare(2));
		row9.add(new EmptySquare());
		row9.add(new Pit());
		row9.add(new Pit());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new FastConveyorbelt(Direction.NORTH));
		row9.add(new EmptySquare());

		ArrayList<Square> row10 = new ArrayList<>();
		row10.add(new EmptySquare());
		row10.add(new EmptySquare("ReE"));
		row10.add(new EmptySquare("W"));
		row10.add(new FastConveyorbelt(Direction.EAST));
		row10.add(new FastConveyorbelt(Direction.EAST));
		row10.add(new FastConveyorbelt(Direction.EAST));
		row10.add(new FastConveyorbelt(Direction.EAST));
		row10.add(new FastConveyorbelt(Direction.EAST));
		row10.add(new FastConveyorbelt(Direction.EAST));
		row10.add(new FastConveyorbelt(Direction.EAST));
		row10.add(new FastConveyorbelt(Direction.NORTH));
		row10.add(new RepairSquare(1));

		ArrayList<Square> row11 = new ArrayList<>();
		row11.add(new EmptySquare());
		row11.add(new EmptySquare("E"));
		row11.add(new Pit("W"));
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new FastConveyorbelt(Direction.NORTH));
		row11.add(new FastConveyorbelt(Direction.NORTH));
		row11.add(new EmptySquare());

		board.addRow(row0);
		board.addRow(row1);
		board.addRow(row2);
		board.addRow(row3);
		board.addRow(row4);
		board.addRow(row5);
		board.addRow(row6);
		board.addRow(row7);
		board.addRow(row8);
		board.addRow(row9);
		board.addRow(row10);
		board.addRow(row11);

		board.addLaser(laser1);
		board.addLaser(laser2);
		board.addLaser(laser3);
		board.addLaser(laser4);

		return board;
	}

	public static Board createIntermediate12x12GameboardBurnout(){
		Board board = new Board();

		ArrayList<Square> row0 = new ArrayList<>();
		row0.add(new EmptySquare());
		row0.add(new EmptySquare("E"));
		row0.add(new EmptySquare("W"));
		row0.add(new FastConveyorbelt(Direction.SOUTH));
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new FastConveyorbelt(Direction.NORTH));
		row0.add(new EmptySquare());

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare("ReS"));
		row1.add(new EmptySquare("E"));
		row1.add(new FinalCheckPoint("W"));
		row1.add(new FastConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new FastConveyorbelt(Direction.NORTH));
		row1.add(new RepairSquare(3));
		
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare("N"));
		row2.add(new EmptySquare());
		row2.add(new SlowConveyorbelt(Direction.EAST));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare("S"));
		row2.add(new EmptySquare());
		row2.add(new Pit());
		row2.add(new RepairSquare(1));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new SlowConveyorbelt(Direction.WEST));

		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare("Re"));
		row3.add(new EmptySquare("ES"));
		row3.add(new EmptySquare("W"));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare("NE"));
		row3.add(new EmptySquare("W"));
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare("ES"));
		row3.add(new EmptySquare("W"));
		row3.add(new EmptySquare());

		Laser laserRow3 = new Laser(5, 3, Direction.EAST, 3);

		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new EmptySquare("N"));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.EAST));
		row4.add(new FastConveyorbelt(Direction.EAST));
		row4.add(new FastConveyorbelt(Direction.EAST));
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new EmptySquare("N"));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());

		Laser laserRow4 = new Laser(9, 4, Direction.SOUTH, 3);
		
		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare("Re"));
		row5.add(new EmptySquare("E"));
		row5.add(new EmptySquare("W"));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new GearRight());
		row5.add(new EmptySquare());
		row5.add(new FastConveyorbelt(Direction.SOUTH));
		row5.add(new EmptySquare());
		row5.add(new Pit());
		row5.add(new EmptySquare());

		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare("Re"));
		row6.add(new EmptySquare("E"));
		row6.add(new EmptySquare("W"));
		row6.add(new Pit());
		row6.add(new EmptySquare());
		row6.add(new FastConveyorbelt(Direction.NORTH));
		row6.add(new RepairSquare(3));
		row6.add(new EmptySquare());
		row6.add(new FastConveyorbelt(Direction.SOUTH));
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());

		ArrayList<Square> row7 = new ArrayList<>();
		row7.add(new Gear180());
		row7.add(new EmptySquare("S"));
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare("S"));
		row7.add(new FastConveyorbelt(Direction.NORTH));
		row7.add(new FastConveyorbelt(Direction.WEST));
		row7.add(new FastConveyorbelt(Direction.WEST));
		row7.add(new FastConveyorbelt(Direction.WEST));
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());

		Laser laserRow7 = new Laser(4, 7, Direction.NORTH, 3);

		ArrayList<Square> row8 = new ArrayList<>();
		row8.add(new EmptySquare("Re"));
		row8.add(new EmptySquare("NE"));
		row8.add(new EmptySquare("W"));
		row8.add(new EmptySquare("E"));
		row8.add(new EmptySquare("NW"));
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare("E"));
		row8.add(new EmptySquare("WS"));
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());

		Laser laserRow8 = new Laser(8, 8, Direction.WEST, 3);

		ArrayList<Square> row9 = new ArrayList<>();
		row9.add(new EmptySquare("S"));
		row9.add(new EmptySquare());
		row9.add(new SlowConveyorbelt(Direction.EAST));
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new RepairSquare(1));
		row9.add(new Pit());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare("N"));
		row9.add(new EmptySquare());
		row9.add(new SlowConveyorbelt(Direction.EAST));

		ArrayList<Square> row10 = new ArrayList<>();
		row10.add(new EmptySquare("ReN"));
		row10.add(new EmptySquare("E"));
		row10.add(new RepairSquare(2, "W"));
		row10.add(new FastConveyorbelt(Direction.SOUTH));
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new FastConveyorbelt(Direction.NORTH));
		row10.add(new Checkpoint());

		ArrayList<Square> row11 = new ArrayList<>();
		row11.add(new EmptySquare());
		row11.add(new EmptySquare("E"));
		row11.add(new EmptySquare("W"));
		row11.add(new FastConveyorbelt(Direction.SOUTH));
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new FastConveyorbelt(Direction.NORTH));
		row11.add(new EmptySquare());

		board.addRow(row0);
		board.addRow(row1);
		board.addRow(row2);
		board.addRow(row3);
		board.addRow(row4);
		board.addRow(row5);
		board.addRow(row6);
		board.addRow(row7);
		board.addRow(row8);
		board.addRow(row9);
		board.addRow(row10);
		board.addRow(row11);

		board.addLaser(laserRow3);
		board.addLaser(laserRow4);
		board.addLaser(laserRow7);
		board.addLaser(laserRow8);

		return board;
	}

	public static Board createAdvanced12x12GameboardHeavyMergeArea(){
		Board board = new Board();

		ArrayList<Square> row0 = new ArrayList<>();
		row0.add(new Checkpoint());
		row0.add(new SlowConveyorbelt(Direction.SOUTH));
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new Pit());
		row0.add(new EmptySquare());
		row0.add(new EmptySquare());
		row0.add(new SlowConveyorbelt(Direction.WEST));
		row0.add(new SlowConveyorbelt(Direction.WEST));
		row0.add(new EmptySquare());

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new Pit());
		row1.add(new SlowConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new RepairSquare(2));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new Pit());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new SlowConveyorbelt(Direction.WEST));
		row1.add(new EmptySquare());
		
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.WEST));
		row2.add(new SlowConveyorbelt(Direction.WEST, "E"));
		row2.add(new EmptySquare("W"));
		row2.add(new EmptySquare("E"));
		row2.add(new FastConveyorbelt(Direction.EAST, "W"));
		row2.add(new FastConveyorbelt(Direction.SOUTH));
		row2.add(new FastConveyorbelt(Direction.EAST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new FastConveyorbelt(Direction.WEST));
		row2.add(new EmptySquare("RwS"));
		row2.add(new EmptySquare());

		Laser laserRow2 = new Laser(3, 2, Direction.WEST, 3);

		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new FastConveyorbelt(Direction.EAST));
		row3.add(new FastConveyorbelt(Direction.EAST));
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new FastConveyorbelt(Direction.NORTH));
		row3.add(new FastConveyorbelt(Direction.SOUTH));
		row3.add(new RepairSquare(3));
		row3.add(new EmptySquare("E"));
		row3.add(new EmptySquare("W"));
		row3.add(new EmptySquare("N"));
		row3.add(new EmptySquare());

		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare("S"));
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new EmptySquare());
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new FastConveyorbelt(Direction.NORTH));
		row4.add(new FastConveyorbelt(Direction.EAST));
		row4.add(new FastConveyorbelt(Direction.EAST, "S"));
		row4.add(new FastConveyorbelt(Direction.SOUTH));
		row4.add(new EmptySquare());
		row4.add(new EmptySquare());
		row4.add(new EmptySquare("Rw"));
		
		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare("N"));
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new SlowConveyorbelt(Direction.NORTH));
		row5.add(new FastConveyorbelt(Direction.SOUTH));
		row5.add(new FastConveyorbelt(Direction.SOUTH));
		row5.add(new FastConveyorbelt(Direction.NORTH));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare("N"));
		row5.add(new FastConveyorbelt(Direction.SOUTH));
		row5.add(new EmptySquare("E"));
		row5.add(new EmptySquare("RwW"));
		row5.add(new EmptySquare());

		Laser laserRow5 = new Laser(0, 5, Direction.SOUTH, 3);

		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare("E"));
		row6.add(new FastConveyorbelt(Direction.NORTH, "W"));
		row6.add(new RepairSquare(3));
		row6.add(new FastConveyorbelt(Direction.SOUTH));
		row6.add(new FastConveyorbelt(Direction.SOUTH));
		row6.add(new FastConveyorbelt(Direction.NORTH));
		row6.add(new FastConveyorbelt(Direction.WEST));
		row6.add(new FastConveyorbelt(Direction.WEST));
		row6.add(new FastConveyorbelt(Direction.WEST));
		row6.add(new RepairSquare(1, "E"));
		row6.add(new EmptySquare("RwW"));
		row6.add(new EmptySquare());

		ArrayList<Square> row7 = new ArrayList<>();
		row7.add(new EmptySquare("S"));
		row7.add(new FastConveyorbelt(Direction.NORTH));
		row7.add(new SlowConveyorbelt(Direction.SOUTH));
		row7.add(new FastConveyorbelt(Direction.SOUTH));
		row7.add(new FastConveyorbelt(Direction.SOUTH));
		row7.add(new RepairSquare(3));
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new GearLeft());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare());
		row7.add(new EmptySquare("Rw"));

		ArrayList<Square> row8 = new ArrayList<>();
		row8.add(new EmptySquare("N"));
		row8.add(new FastConveyorbelt(Direction.NORTH));
		row8.add(new EmptySquare());
		row8.add(new Pit());
		row8.add(new FastConveyorbelt(Direction.NORTH));
		row8.add(new FastConveyorbelt(Direction.EAST));
		row8.add(new FastConveyorbelt(Direction.EAST));
		row8.add(new FastConveyorbelt(Direction.SOUTH));
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new EmptySquare());
		row8.add(new Gear180());

		ArrayList<Square> row9 = new ArrayList<>();
		row9.add(new EmptySquare());
		row9.add(new FastConveyorbelt(Direction.NORTH));
		row9.add(new FastConveyorbelt(Direction.WEST));
		row9.add(new FastConveyorbelt(Direction.WEST));
		row9.add(new FastConveyorbelt(Direction.WEST));
		row9.add(new FastConveyorbelt(Direction.WEST));
		row9.add(new FastConveyorbelt(Direction.WEST));
		row9.add(new FastConveyorbelt(Direction.WEST));
		row9.add(new EmptySquare());
		row9.add(new EmptySquare());
		row9.add(new EmptySquare("S"));
		row9.add(new EmptySquare());

		ArrayList<Square> row10 = new ArrayList<>();
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare("E"));
		row10.add(new RepairSquare(2, "W"));
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new EmptySquare());
		row10.add(new SlowConveyorbelt(Direction.EAST));
		row10.add(new SlowConveyorbelt(Direction.EAST));
		row10.add(new EmptySquare("RwN"));
		row10.add(new EmptySquare());

		ArrayList<Square> row11 = new ArrayList<>();
		row11.add(new EmptySquare());
		row11.add(new Pit());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare());
		row11.add(new Pit());
		row11.add(new EmptySquare());
		row11.add(new EmptySquare("S"));
		row11.add(new SlowConveyorbelt(Direction.NORTH));
		row11.add(new FinalCheckPoint());
		row11.add(new SlowConveyorbelt(Direction.WEST));
		row11.add(new EmptySquare());

		Laser laserRow11 = new Laser(7, 11, Direction.NORTH, 2);

		board.addRow(row0);
		board.addRow(row1);
		board.addRow(row2);
		board.addRow(row3);
		board.addRow(row4);
		board.addRow(row5);
		board.addRow(row6);
		board.addRow(row7);
		board.addRow(row8);
		board.addRow(row9);
		board.addRow(row10);
		board.addRow(row11);

		board.addLaser(laserRow2);
		board.addLaser(laserRow5);
		board.addLaser(laserRow11);

		return board;
	}
}