import React from "react";

interface DealNewCardsButtonProps{
    onClick(): void
}
export function DealNewCardsButton({onClick }: DealNewCardsButtonProps){
    let backgroundcolour = "yellow";
    return <button style={{backgroundColor: backgroundcolour, fontSize: "40px"}} onClick={onClick}>deal new cards</button>
}