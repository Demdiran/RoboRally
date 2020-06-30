import React from "react";

interface NextMoveButtonProps{
    onClick(): void,
    movenr: number,
    allplayersreadyformove: boolean

}
export function NextMoveButton({onClick, movenr, allplayersreadyformove}: NextMoveButtonProps){
    let backgroundcolour = "paleyellow";
return <button style={{backgroundColor: backgroundcolour, fontSize: "40px"}} onClick={onClick}
        disabled={allplayersreadyformove == false}> Play Move {movenr}</button>
}