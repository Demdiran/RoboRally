import React from "react";
import { Robot } from "./Robot";
import { Board } from "./board/Board";
import { Square } from "./board/Square";

const board = Board;

interface playerlistprops{
    players: Robot[],
    board: Square[][],
}

export function PlayerList({ players, board }: playerlistprops){
    let playerlist = players.map((robot: Robot) => {
        return createPlayerEntry(robot, board);
    });
    let style: React.CSSProperties = {
        position: "absolute",
        top: "0px",
        right: "0px",
        minWidth: "350px",
    }
    return (
        <div style={style}>
            {playerlist}
        </div>
    )
}

export function gameWinner(){
    
}

function createPlayerEntry(robot: Robot, board: Square[][]){
    let style: React.CSSProperties = {
        color: robot.colour,
    };
    let readytext = "";
    if(!robot.ready) readytext = " is programming, and active";
    else if(robot.ready) readytext = " is ready and wants to display next move";
    
    if(robot.wantsToExecuteNextMove) readytext = " wants to display next move";

    if(robotOffBoard(robot, board)) readytext = " has lost all hp and will respawn next turn";

    if(robot.status == "Inactive") readytext = " has powered down.";
    return (
        <p style={style} key={robot.name}>
            {robot.name}({robot.hitpoints}){readytext}
        </p>
    );
}

function robotOffBoard(robot: Robot, board: Square[][]){
    if(robot.xCoordinate < 0 || robot.xCoordinate > board[0].length || robot.yCoordinate < 0 || robot.yCoordinate > board.length){
        return true;
    } else return false;
}