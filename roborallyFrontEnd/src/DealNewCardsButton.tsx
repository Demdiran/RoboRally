import React from "react";

interface DealNewCardsButtonProps{
    onClick(): void,
    readystate:boolean
}
export function DealNewCardsButton({onClick, readystate}: DealNewCardsButtonProps){
    let backgroundcolour = "yellow";
    return <button style={{backgroundColor: backgroundcolour, fontSize: "40px"}}
     onClick={onClick} disabled = {readystate == false}>deal new cards</button>
}