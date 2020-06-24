package nl.sogyo.roborally.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import nl.sogyo.roborally.domain.*;
import nl.sogyo.roborally.domain.cards.Card;
import nl.sogyo.roborally.domain.elements.Laser;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

@SuppressWarnings("unchecked")
public class JSONResultProcessor {
 
    public String createBoardResponse(Roborally roborally){
        Board board = roborally.getBoard();
        JSONArray squares = createJSONBoard(board);
        JSONObject response = new JSONObject();
        response.put("messagetype", "boardstate");
        response.put("body", squares);
        return response.toJSONString();
    }

    public String createCardsResponse(Roborally rRally, Robot robot){
        List<Card> hand = robot.getHand();
        List<Card> lockedCards = robot.getLockedCards();
        JSONArray cards = createJSONCards(hand);
        JSONArray lockedCardsJSON = createJSONCards(lockedCards);
        JSONObject response = new JSONObject();
        response.put("messagetype", "drawncards");
        response.put("body", cards);
        response.put("lockedcards", lockedCardsJSON);
        return response.toJSONString();
    }

    public String createRobotsResponse(Roborally roborally){
        List<Robot> robotList= roborally.getRobots();
        JSONArray robots = new JSONArray();
        for(Robot robot : robotList){
            robots.add(this.createJSONRobot(robot));
        }

        JSONObject response = new JSONObject();
        response.put("messagetype", "robots");
        response.put("body", robots);
        return response.toJSONString();
    }

    public String createWinnerResponse(Roborally r){
        JSONObject response = new JSONObject();
        response.put("messagetype", "winner");
        response.put("body", r.getWinner().getName());
        return response.toJSONString();
    }

    public String createLasersResponse(Roborally roborally){
        ArrayList<Laser> lasers = roborally.getBoard().getLasers();
        JSONArray jsonLasers = createJSONLasers(lasers);

        JSONObject response = new JSONObject();
        response.put("messagetype", "lasers");
        response.put("body", jsonLasers);
        return response.toJSONString();
    }

    public String createPowerstatusResponse(Robot robot){
        JSONObject response = new JSONObject();
        response.put("messagetype", "powerstatus");
        response.put("body", robot.getActivitylevel().toString());
        return response.toJSONString();
    }
 
    private JSONArray createJSONCards(List<Card> cards){
        JSONArray jsonCards = new JSONArray();
        for(int i = 0; i < cards.size(); i++){
            jsonCards.add(createJSONCard(cards.get(i), i));
        }
        return jsonCards;
    }

    private JSONObject createJSONCard(Card card, int index){
        JSONObject jsonCard = new JSONObject();
        jsonCard.put("name", card.getName());
        jsonCard.put("speed", card.getSpeed());
        jsonCard.put("cardid", index);
        return jsonCard;
    }

    private JSONArray createJSONBoard(Board board){
        JSONArray jsonSquares = new JSONArray();
        ArrayList<ArrayList<Square>> boardSquares = board.getBoard();

        for(ArrayList<Square> row : boardSquares){
            JSONArray jsonRow = new JSONArray();
            for(Square square : row){
                jsonRow.add(this.createJSONSquare(square));
            }
            jsonSquares.add(jsonRow);
        }
        return jsonSquares;
    }

    private JSONObject createJSONSquare(Square square){
        JSONObject result = new JSONObject();
        result.put("type", square.getType());
        result.put("northwall", square.hasWallAt(Direction.NORTH));
        result.put("eastwall", square.hasWallAt(Direction.EAST));
        result.put("southwall", square.hasWallAt(Direction.SOUTH));
        result.put("westwall", square.hasWallAt(Direction.WEST));
        if(square.getType() != "RepairSquare") result.put("hp", 0);
        else result.put("hp", ((RepairSquare) square).healingPower);
        return result;
    }

    private JSONObject createJSONRobot(Robot robot){
        JSONObject result = new JSONObject();
        result.put("name", robot.getName());
        result.put("orientation", robot.getOrientation().toString());
        result.put("colour", robot.getColour());
        result.put("xCoordinate", robot.getXCoordinate());
        result.put("yCoordinate", robot.getYCoordinate());
        result.put("ready", robot.isReady());
        result.put("hitpoints", robot.getHealth());
        result.put("status", robot.getActivitylevel().toString());
        result.put("lives", robot.getLives());
        result.put("flagReached", robot.hasReachedCheckpoint());
        return result;
    }

    private JSONArray createJSONLasers(ArrayList<Laser> lasers){
        JSONArray jsonLasers = new JSONArray();
        for(Laser laser : lasers){
            jsonLasers.add(createJSONLaser(laser));
        }
        return jsonLasers;
    }

    private JSONObject createJSONLaser(Laser laser){
        JSONObject result = new JSONObject();
        result.put("orientation", laser.getOrientation().toString());
        result.put("xCoordinate", laser.getxCoordinate());
        result.put("yCoordinate", laser.getyCoordinate());
        result.put("firepower", laser.getFirepower());
        return result;
    }

    public String createGameOverResponse(){
        JSONObject result = new JSONObject();
        result.put("messagetype", "gameover");
        result.put("body", " ");
        return result.toJSONString();
    }
}