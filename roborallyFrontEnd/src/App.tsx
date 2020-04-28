import React, { useState } from "react";
import { Square } from "./board/Square";
import { Board } from "./board/Board";

export function App() {
    const [gamestate, setGamestate] = useState<Square[][] | undefined>(undefined);
    const [websocket, setWebsocket] = useState<WebSocket | undefined>(undefined);

    async function getGameState(){
        if (websocket !== undefined
                && websocket.readyState !== WebSocket.CLOSED) {
            return;
        }
        let tempwebsocket = new WebSocket("ws://localhost:3000/roborally/websocket");

        if(tempwebsocket !== undefined && tempwebsocket.readyState !== WebSocket.CLOSED){

            tempwebsocket.onopen = function(){
                console.log("connected");
                tempwebsocket.send("gib data pls");
            };

            tempwebsocket.onmessage = function(event: WebSocketMessageEvent){
                let gamestate = JSON.parse(event.data);
                console.log(gamestate);
                setGamestate(gamestate);
            };

            tempwebsocket.onclose = function(event: WebSocketCloseEvent){
                console.log("connection closed");
            }

        }
        setWebsocket(tempwebsocket);
        // console.log("Initializing gamestate.");
        // const response = await fetch("roborally/api/initialize", {
        //     method: 'GET',
        //     headers:{
        //         'Accept': 'application/json'
        //     }
        // });
        // const gamestate = await response.json();
        // setGamestate(gamestate);
    }

    async function moveForward(){
        programCard(0);
    }

    async function turnRight(){
        programCard(1);
    }

    async function turnLeft(){
        programCard(2);
    }

    async function uTurn(){
        programCard(3);
    }

    async function moveForward2(){
        programCard(4);
    }

    async function moveForward3(){
        programCard(5);
    }

    async function programCard(cardnr:number){
        console.log("Programming card: " + cardnr);
        const response = await fetch("roborally/api/program/" + cardnr, {
            method: 'PUT',
            headers:{
                'Accept': 'application/json'
            }
        });
        const gamestate = await response.json();
        setGamestate(gamestate);
    }


    if(gamestate != undefined){
        return (<div>
                    <Board squares = {gamestate}></Board>
                    <button onClick={moveForward}>Forward</button>
                    <button onClick={turnRight}>Right</button>
                    <button onClick={turnLeft}>Left</button>
                    <button onClick={uTurn}>Turn around</button>
                    <button onClick={moveForward2}>Forward x 2</button>
                    <button onClick={moveForward3}>Forward x 3</button>
                </div>);
    }
    else{
        getGameState();
        return <div>Loading...</div>;
    }
}