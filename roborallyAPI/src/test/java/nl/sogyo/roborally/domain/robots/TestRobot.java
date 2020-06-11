package nl.sogyo.roborally.domain.robots;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.Card;
import nl.sogyo.roborally.domain.cards.Deck;
import nl.sogyo.roborally.domain.cards.DoNothingCard;
import nl.sogyo.roborally.domain.cards.MoveBackCard;
import nl.sogyo.roborally.domain.cards.MoveOneCard;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRobot{

    @Test
    public void testGetStartPosition(){
        Robot robot = new Robot(0,1);
        assertEquals(0, robot.getXCoordinate());
        assertEquals(1, robot.getYCoordinate());
    }

    @Test
    public void testIsAtSquare(){
        Robot robot = new Robot(2,3);
        assert(robot.isAt(2,3));
    }

    @Test
    public void testTakeDamage(){
        Robot robot = new Robot(2,2);
        robot.takeDamage(1);
        assert(robot.getHealth() == 8);
    }

    @Test
    public void testMoveOneNorth() {
        Robot robot = new Robot(2,3);
        robot.setOrientation(Direction.NORTH);
        robot.moveForward();
        assert(robot.isAt(2,2));
    }

    @Test
    public void testMoveOneEast() {
        Robot robot = new Robot(2,3);
        robot.setOrientation(Direction.EAST);
        robot.moveForward();
        assert(robot.isAt(3,3));
    }

    @Test
    public void testMoveOneSouth() {
        Robot robot = new Robot(2,3);
        robot.setOrientation(Direction.SOUTH);
        robot.moveForward();
        assert(robot.isAt(2,4));
    }

    @Test
    public void testMoveOneWest() {
        Robot robot = new Robot(2,3);
        robot.setOrientation(Direction.WEST);
        robot.moveForward();
        assert(robot.isAt(1,3));
    }

    @Test
    public void testProgramRobotMoveForward(){
        Robot robot = new Robot();
        Card[] cards = {new MoveOneCard(), new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards) ;
        Card card2 = robot.getCard(0);
        assertEquals(cards[0], card2);
    }

    @Test
    public void testProgramRobotMoveBackward(){
        Robot robot = new Robot();
        int[] cardnrs = {6,7,7,7,7};
        robot.program(cardnrs);
        assertTrue(robot.getCard(0) instanceof MoveBackCard);
    }

    @Test
    public void robotHasLockedCardsWithLess4health(){
        Deck deck = new Deck();
        Robot robot = new Robot();
        robot.drawCards(deck);
        robot.takeDamage(5);
        robot.clearHand(deck);;
        assertTrue(robot.getLockedCards().size() == 1);
        robot.takeDamage(1);
        robot.clearHand(deck);;
        assertTrue(robot.getLockedCards().size() == 2);
        robot.takeDamage(3);
        robot.clearHand(deck);;
        assertTrue(robot.getLockedCards().size() == 5);
    }

    @Test
    public void deckHasLessCardsWhenRobotHasLockedCards1(){
        Deck deck = new Deck();
        Robot robot = new Robot();
        robot.drawCards(deck);
        robot.takeDamage(5);
        int[] cardnrs = {0,1,2,3,4};
        robot.programFromHand(cardnrs);
        robot.clearHand(deck);
        assertEquals(deck.getSize(), 83);
    }

    @Test
    public void deckHasLessCardsWhenRobotHasLockedCards2(){
        Deck deck = new Deck();
        Robot robot = new Robot();
        robot.drawCards(deck);
        robot.takeDamage(6);
        int[] cardnrs = {0,1,2,3,4};
        robot.programFromHand(cardnrs);
        robot.clearHand(deck);
        assertTrue(deck.getSize() == 82);
    }

    @Test
    public void deckHasLessCardsWhenRobotHasLockedCards3(){
        Deck deck = new Deck();
        Robot robot = new Robot();
        robot.drawCards(deck);
        robot.takeDamage(9);
        int[] cardnrs = {0,1,2,3,4};
        robot.programFromHand(cardnrs);
        robot.clearHand(deck);
        assertTrue(deck.getSize() == 79);
    }

    @Test
    public void deckRefillsAfterPowerCycle(){
        Deck deck = new Deck();
        Robot robot = new Robot();
        robot.drawCards(deck);
        robot.takeDamage(9);
        int[] cardnrs = {0,1,2,3,4};
        robot.programFromHand(cardnrs);
        robot.shutDown(deck);
        robot.clearHand(deck);
        assertTrue(deck.getSize() == 84);
    }





}