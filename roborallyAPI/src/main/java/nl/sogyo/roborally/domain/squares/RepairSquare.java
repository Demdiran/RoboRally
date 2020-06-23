package nl.sogyo.roborally.domain.squares;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;

public class RepairSquare extends Square {
    int healingPower;


    public RepairSquare(int healingPower){
        this.healingPower = healingPower;
    }

    public RepairSquare(int healingPower, String walls){
        super(walls);
        this.healingPower = healingPower;
    }

    @Override
    public String getType(){
        return "RepairSquare";
    }

    @Override
    public void doSquareAction(Robot robot, Board board, List<Robot> robots){
        robot.receiveHealth(this.healingPower);
    }

}