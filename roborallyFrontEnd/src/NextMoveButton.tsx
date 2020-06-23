import React from "react";

interface NextMoveButtonProps{
    onClick(): void
}
export function NextMoveButton({onClick }: NextMoveButtonProps){
    let backgroundcolour = "paleyellow";
    return <button style={{backgroundColor: backgroundcolour, fontSize: "40px"}} onClick={onClick}>display next move</button>
}