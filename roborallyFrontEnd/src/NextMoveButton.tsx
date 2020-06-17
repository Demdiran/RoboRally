import React from "react";

interface NextMoveButtonProps{
    onClick(): void
}
export function NextMoveButton({onClick }: NextMoveButtonProps){
    return <button style={{fontSize: "40px"}} onClick={onClick}>display next move</button>
}