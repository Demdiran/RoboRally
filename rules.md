RoboRally rules: game for 1 - 6 players

1) How to win the game:
Program your robot to navigate it over the board and be the first to reach the intermediate checkpoint at the flag and then the finish at the house.

2) How to play:
When you log in to the game, you start at your unique starting position and nine cards will be dealt to you.
From the nine cards in your hand, choose five with which to program your robot’s movements by clicking on them. The cards you programmed will appear underneath the remaining cards in your hand.
If you change your mind about a card, simply click on it again and it will be returned to your hand. You can then choose another card from your hand by clicking on it.
When you have programmed five cards, the 'ready' button will activate and you can click on it to program your robot with your chosen cards. Your status will then change from 'programming' to 'ready'. Once you have clicked 'ready', it is not possible to change cards!
When every player has clicked 'ready' you can start executing the first of the five registers of the turn by clicking on 'play move xxx' (where xxx stands for a number between 1 and 5). Once one player has clicked on 'play move xxx', the first register will be executed and the board will update to display your new position.
Repeat this procedure until all five registers of the turn are played. Once all five registers have been played, the 'deal new cards' button will activate. Once a player clicks on 'deal new cards', all players will be dealt a new hand.
Turns continue until a winner emerges. It is not possible to play for runners-up (i.e. you cannot keep playing to determine second place, third place, etc.).
If you refresh the page, you will be taken out of the game.

3) Robot movement:
Your robot can move one, two or three steps forwards (indicated by the number in the top right corner of the card), one step backwards or turn left, right or 180 degrees.
Robot movement is executed based on priority. Priority is determined by the priority number on your card. The robot with the highest priority number moves first.
If your robot is in the way of another robot, it will be pushed along according to the other robot's movement card.
Your robot cannot move if the square has a wall in your robot's movement direction. Therefore, if your robot needs to push another robot along, but that other robot is blocked by a wall, your robot also cannot move (i.e. robots cannot crush each other against a wall).
If your robot moves off the board or falls into a pit, it will be taken out of the game for the remainder of the turn. At the start of the next turn, your robot will reenter the game at its last respawn position, which is your starting position or the intermediate checkpoint, if you have already reached that before respawning (for more on respawning see (4) below on robot health).

4) Robot health:
Your robot has three lives and nine health points.
Health points are lost when a robot is shot by another robot (minus 1 hp, for more on robot lasers see (5e) below) or ends a register in the beam of a board laser (minus 1, 2 or 3 hp as indicated by the number next to the laser, see (5d) below on board lasers). You lose all health points if your robot falls into a pit or walks off the board.
Health points can be earned back by ending a register on a repair square (plus 1, 2 or 3 hp as indicated by the number on the square, see (5f) below on board elements) or by powering down (restores all hp, unless you are shot while powered down).
If you want to power down, you have to click the ‘power’ button. The button will turn red and you will power down during the next turn. You are dealt no cards when you are powered down, but you can still be moved or shot by other robots or board elements (see (5) below). Your status will say “powered down”
When you have lost all your hp, you will be taken out of the game for the remainder of the turn and reenter next turn at your last respawn location. Your status will then say “lost all hp and will respawn next turn”. Your last respawn location is either your starting position or the intermediate checkpoint at the flag if you have reached that already.
Each time you lose a health point, you will be dealt one card less in your next turn. If you have dropped down to less than 5 hp, the last card(s) you programmed will stay locked. That is, these cards will not reenter the deck, you will not be able to programme other cards in their stead and your robot will have to execute those cards again in your next turn. If you have 4 hp remaining, the last card stays locked, if you have 3 hp remaining, the last two cards stay locked and so on.
Each time you drop down to 0 hp, you lose one of your three lives. When you have lost all of your lives, you cannot respawn and will permanently be taken out of the game.

5) Board elements:
Board elements will execute in the following order:

a) Fast conveyor belts:
The fast conveyor belt takes your robot two steps forward in the direction indicated by the double arrows. If the belt takes you round a bent, your robot will turn in the same direction (even if your robot is not facing in the direction of the arrows).
If a wall is in the way or the belt ends after one step, it will take you only one step forward.
If your robot is on a t-section and another robot will be moved into the same position as yours, neither robot will be moved.
If the belt ends but another robot which is NOT on the belt is in the way of your robot, your robot will not move. Robots on a belt can never push robots who are not on the belt.
If your robot cannot move on the belt for whichever reason (wall, t-section, other robot in the way off the belt), no robot which is positioned behind your robot will be able to move either.

b) Slow conveyor belts:
The slow conveyor belt takes your robot one step forwards in the direction of the single arrows. Otherwise it works exactly as the fast conveyor belt.
If you are pushed from a fast conveyor belt to a slow conveyor belt or the other way around and the new belt takes you in a different direction, your robot will not turn. Robots only turn on conveyor belts if they both belts are of the same type.

c) Gears:
The gears will turn your robot in the direction indicated by the arrow. If the gear displays a diamond on the left side (similar to the diamond on the 180 degree turn card), the gear will turn you 180 degrees.

d) Board lasers:
If your robots is in the way of the beam of a board laser at the end of a register, it will lose one, two or three health points as indicated by the small number next to the laser. Lasers are only activated after the robots have stopped moving, therefore, if your robot walks in the path of a laser and then executes at least one more step (e.g. with a move-2 or move-3 card or on a fast conveyor belt), it will not take damage.
Laser beams shine across the board until they hit an obstacle. Therefore, if your robot is in the way of a laser at the end of a register, but another robot ends up between your robot and the laser’s source, your robot will not take damage.

e) Robot lasers:
After all movement of robots either by their own programmed cards or by the board elements has been executed, robots will fire their own lasers.
The lasers fire in the direction the robot is facing until they hit an obstacle. If your robot is hit by another robot’s laser, it will lose one health point.

f) Repair square:
If your robot stands on a repair square at the end of a register, it will gain one, two or three health points as indicated by the number on the square.

If your robot should fall into a pit or move off the board as the result of a board element moving it, it will lose all health points and be taken out of the game for the remainder of the turn. If you have lives left, your robot will then reenter the game at the next turn at its respawn location.
Board elements operate on your robot regardless of whether it is powered down or not.

6) Terminology:
A turn consists of five registers. Each register consists of the playing of one of the programmed cards of each robot and the activation of all board elements.


Chose a gameboard:
Go to the class RoborallyWebsocket.java and change which board you want to use in line 20.

1) BoardFactory.createBeginner12x12GameboardCorridorBlitz() - based on corridor blitz
2) BoardFactory.createIntermediate12x12GameboardPassingLane() - based on passing lane
3) BoardFactory.createIntermediate12x12GameboardBurnout() - based on burnout
4) BoardFactory.createAdvanced12x12GameboardHeavyMergeArea() - based on heavy merge area
