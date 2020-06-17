import React from "react";

interface NextMoveButtonProps{
    onClick(): void
}
export function NextMoveButton({onClick }: NextMoveButtonProps){
    let backgroundcolour = "lightyellow";
    return <button style={{backgroundColor: backgroundcolour, fontSize: "40px"}} onClick={onClick}>display next move</button>
}