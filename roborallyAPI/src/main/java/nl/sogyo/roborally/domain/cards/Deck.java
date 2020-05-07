package nl.sogyo.roborally.domain.cards;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Deck {
    List<ICard> cardsInDeck;

    public int getSize(){
        return cardsInDeck.size();
    }

    public List<ICard> getDeck(){
        return this.cardsInDeck;
    }

    public void createDeck(){
        cardsInDeck = new ArrayList<ICard>();
        ClassLoader classLoader = new Deck().getClass().getClassLoader();
        // Deck.getResourceAsStream("roborallyAPI\src\main\resources\TextDeck.txt")
        File file = new File(classLoader.getResource("TextDeck.txt").getFile());
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                addCardToDeck(sc.nextLine());
            };
            sc.close();
        }catch(IOException e){
            System.out.println("File not found.");
        }

    }

    private void addCardToDeck(String line){
        int speed = Integer.parseInt(line.split(" ")[0]);
        String type = line.split(" ", 2)[1];
        ICard card;
        switch(type){
            case "U Turn": card = new UTurnCard(speed); break;
            case "Rotate Left": card = new RotateLeftCard(speed); break;
            case "Rotate Right": card = new RotateRightCard(speed); break;
            case "Back Up": card = new MoveBackCard(speed); break;
            case "Move 1": card = new MoveOneCard(speed); break;
            case "Move 2": card = new MoveTwoCard(speed); break;
            case "Move 3": card = new MoveThreeCard(speed); break;
            default: card = null;
        }
        cardsInDeck.add(card);
    }
       
    public List<ICard> getHand(int damage){
        List<ICard> hand = new ArrayList<ICard>();
        for(int i = 0; i < 9;i++){       
            if(i<9-damage) hand.add(getRandomCard());
            else  hand.add(new DoNothingCard());
        }
        return hand;
    }

    private ICard getRandomCard(){
        Random rand = new Random();
        int cardIndex = rand.nextInt(cardsInDeck.size());
        ICard randCard = this.cardsInDeck.get(cardIndex);
        this.cardsInDeck.remove(cardIndex);
        return randCard;
    }
}