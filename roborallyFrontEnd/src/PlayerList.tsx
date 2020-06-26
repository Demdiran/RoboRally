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
    let status = "Programming.";    
    if(robot.ready) status = "Ready to play next move.";
    if(robotOffBoard(robot, board)) status = "Lost all hp and will respawn next turn.";
    if(robot.status == "Inactive") status = "Powered down.";
    let flag = "No";
    if(robot.flagreached) flag = "Yes";
    return (
        <p style={style} key={robot.name}>
            {robot.name} Lives: {robot.lives} Health: {robot.hitpoints} Status: {status} Checkpoint Reached: {flag}
        </p>
    );
}

function robotOffBoard(robot: Robot, board: Square[][]){
    if(robot.xCoordinate < 0 || robot.xCoordinate > board[0].length || robot.yCoordinate < 0 || robot.yCoordinate > board.length){
        return true;
    } else return false;
}