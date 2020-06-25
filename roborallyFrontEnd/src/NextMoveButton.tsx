import React from "react";

interface NextMoveButtonProps{
    onClick(): void,
    movenr: number

}
export function NextMoveButton({onClick, movenr}: NextMoveButtonProps){
    let backgroundcolour = "paleyellow";
return <button style={{backgroundColor: backgroundcolour, fontSize: "40px"}} onClick={onClick}
        disabled={movenr == 0}> Play Move {movenr}</button>
}