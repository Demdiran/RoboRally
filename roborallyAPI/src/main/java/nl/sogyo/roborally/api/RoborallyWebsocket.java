package nl.sogyo.roborally.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import nl.sogyo.roborally.domain.Roborally;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.BoardFactory;

@ServerEndpoint(value = "/websocket")
public class RoborallyWebsocket{
    private static final Roborally roborally = new Roborally(BoardFactory.createSmallCompleteBoard());
    private static final List<Session> players = new ArrayList<>();
    private static final Map<Session, Robot> robots = new HashMap<>();
    
    @OnOpen
    public void onOpen(Session session)throws IOException{
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session)throws IOException{
        System.out.println("Message received: " + message);
        if(message.contains("initialize")){
            String name = message.split(" ")[1];
            Robot robot = new Robot(name, roborally.getBoard(), robots.size());
            roborally.resetWinner();
            roborally.resetNextRegisterToBePlayed();
            robots.put(session, robot);
            roborally.addRobot(robot);
            players.add(session);
            sendBoardInformation(session);
            updateAllPlayers();
        }
        else if(message.equals("switchpower")){
            Robot robot = robots.get(session);
            robot.turnOnOrOff();
            updatePlayerPowerStatus(session);
            for(Session player : players){
                updatePlayerPowerStatus(player);
            }
        }
        else if(message.equals("display next move")){
            Robot robot = robots.get(session);
            robot.wantsToExecuteNextMove();
            String readyformove= new JSONResultProcessor().createReadyForMoveState(false);
            session.getBasicRemote().sendText(readyformove);
            roborally.playNextRegisterIfAllRobotsReadyAndWantToExecuteNextMove();
            if(roborally.getWinner() != null){
                String winner = new JSONResultProcessor().createWinnerResponse(roborally);
                session.getBasicRemote().sendText(winner);
            }else if(!robot.isAlive()){
                String gameover = new JSONResultProcessor().createGameOverResponse();
                session.getBasicRemote().sendText(gameover);
            }else{
                updateAllRobots();
            }
        }
        else if(message.equals("end turn")){
            roborally.entersProgrammingPhase();
            roborally.dealtCards();
            roborally.prepareNextRound();
            updateAllPlayers();
            System.out.println("reached");
            for(Session player : players){
                String readytodeal = new JSONResultProcessor().readyToDealCards(false);
                player.getBasicRemote().sendText(readytodeal);
                String readytoprogram = new JSONResultProcessor().readyToProgram(true);
                player.getBasicRemote().sendText(readytoprogram);
                String readyformove= new JSONResultProcessor().createReadyForMoveState(false);
                player.getBasicRemote().sendText(readyformove);
            }
            
        }
        else {
            int[] cardnrs = MessageParser.parseMessage(message);
            Robot robot = robots.get(session);
            robot.programFromHand(cardnrs);
            roborally.leavesProgrammingPhase();
            robot.wantsToExecuteNextMove();
            updateAllRobots();
            String readyToProgram = new JSONResultProcessor().readyToProgram(false);
            session.getBasicRemote().sendText(readyToProgram);
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session)throws IOException{
        Robot robotToRemove = robots.get(session);
        roborally.removeRobot(robotToRemove);
        robots.remove(session);
        players.remove(session);
        System.out.println("Closing a websocket due to " + reason.getReasonPhrase());
        updateAllPlayers();
    }

    private void updateAllPlayers()throws IOException{
        updateAllRobots();
        for(Session player : players){
            updatePlayerPowerStatus(player);
            updatePlayerHand(player);
            if(roborally.getWinner() != null){
                String winner = new JSONResultProcessor().createWinnerResponse(roborally);
                player.getBasicRemote().sendText(winner);
            }
        }
    }

    private void updatePlayerPowerStatus(Session session)throws IOException{
        Robot robot = robots.get(session);
        String powerstatusresponse = new JSONResultProcessor().createPowerstatusResponse(robot);
        session.getBasicRemote().sendText(powerstatusresponse);
    }

    private void updatePlayerHand(Session session)throws IOException{
        String cards = new JSONResultProcessor().createCardsResponse(roborally, robots.get(session));
        session.getBasicRemote().sendText(cards);
    }

    private void updateAllRobots()throws IOException{
        // System.out.print("All robots ready: "+ roborally.allRobotsReadyForNextMove());
        // System.out.print("Next register: "+ roborally.getNextRegisterToBePlayed());
        // System.out.print("Roborally in programming phase: "+ roborally.inProgrammingPhase());

        for(Session session : players){
            String robotresponse = new JSONResultProcessor().createRobotsResponse(roborally);
            session.getBasicRemote().sendText(robotresponse);
            if(roborally.allRobotsReadyForNextMove()&& (roborally.getNextRegisterToBePlayed()<6)
                && !roborally.inProgrammingPhase()){               
                    String readyformove= new JSONResultProcessor().createReadyForMoveState(true);
                    session.getBasicRemote().sendText(readyformove);
            }
            boolean readyToDeal = roborally.isReadyToDealCards();         
            String readytodealresponse = new JSONResultProcessor().readyToDealCards(readyToDeal);
            session.getBasicRemote().sendText(readytodealresponse);
            String movenr = new JSONResultProcessor().createMoveNrResponse(roborally);
            session.getBasicRemote().sendText(movenr);
        }
       
    }

    private void sendBoardInformation(Session session)throws IOException{
        String board = new JSONResultProcessor().createBoardResponse(roborally);
        session.getBasicRemote().sendText(board);
        String lasers = new JSONResultProcessor().createLasersResponse(roborally);
        session.getBasicRemote().sendText(lasers);
        updatePlayerHand(session);
    }

}