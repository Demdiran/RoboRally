import React, { Component } from "react";
import { CardElement, Card } from "./Card";

interface ProgrammedCardsProps{
    cards: Card[],
    lockedcards: Card[],
    ready(cardids: number[], lockedcardids: number[]): void,
    removeCard(cardid: number): void,
    movenr:number,
    readyfornextround:boolean
}

export function ProgrammedCards({cards, lockedcards, ready, removeCard, movenr, readyfornextround}: ProgrammedCardsProps){
    let mycarddivs = cards.map((card: Card, index: number) => <CardElement card={card} key={card.speed} onClick={removeCard}/>);
    let lockedcarddivs = lockedcards.map((card: Card, index: number) => <CardElement card={card} key={card.speed} onClick={removeCard}/>);
    return (      
      <div className = "cardsinhandgrid">
        {mycarddivs}{lockedcarddivs}
        <button onClick={() => ready( cards.map((card: Card) => card.cardid),
         lockedcards.map((card: Card) => card.cardid) ) }
         disabled={(cards.length+lockedcards.length) != 5 || movenr != 0 ||
          readyfornextround == true}>Ready</button>
      </div>
    );
}