package ca.mcmaster.se2aa4.island.team210;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


public class Map {

    private DirectionInfo east = new DirectionInfo();
    private DirectionInfo west = new DirectionInfo();
    private DirectionInfo north = new DirectionInfo();
    private DirectionInfo south = new DirectionInfo();
    public boolean overOcean;
    private String last_echo;

    private final Logger logger = LogManager.getLogger();

    Drone ourDrone;
    public Map(Drone drone) {
        ourDrone = drone;
    }

    public void interpretResults(JSONObject info){
        if (info.has("range")){
            switch (last_echo){
                case "N":
                    north.setInfo(info);
                    break;
                case "E":
                    east.setInfo(info);
                    break;
                case "S":
                    south.setInfo(info);
                    break;
                case "W":
                    west.setInfo(info);
                    break;
                default:
                    break;
            }
        }
    }

    public void storeDecisionInfo(Decision givenDecision) {
        switch (givenDecision.getAction()){
            case "heading":
                ourDrone.handleDirection(givenDecision.getExtra());
                break;
            case "fly":
                ourDrone.move();
                break;
            case "echo":
                last_echo = givenDecision.getExtra();
                break;
            default:
                break;
        }
    }

    public Integer getRange(String orientation){
        String direc = returnDirection(orientation);
        switch (direc){
            case "N":
                return north.getRange();
            case "E":
                return east.getRange();
            case "S":
                return south.getRange();
            case "W":
                return west.getRange();
            default:
                return 0;
        }
    }

    public String getEchoType(String orientation){
        String direc = returnDirection(orientation);
        switch (direc){
            case "N":
                return north.getEchoType();
            case "E":
                return east.getEchoType();
            case "S":
                return south.getEchoType();
            case "W":
                return west.getEchoType();
            default:
                return "";
        }
    }

    public String returnDirection(String orientation){
        switch (orientation){
            case "current":
                return ourDrone.current_direction.toString();
            case "right":
                return ourDrone.right.toString();
            case "left":
                return ourDrone.left.toString();
            default:
                return "";
        }
    }

    public void applyCost(Integer cost) {
        ourDrone.removeCost(cost);
    }

    public String getDirection() { return ourDrone.getDirection();
    }

    public String getRight(){
        return ourDrone.right.toString();
    }
    public String getLeft(){
        return ourDrone.left.toString();
    }
    public void setDroneStartingTurn(String dir){
        ourDrone.setStartingTurn(dir);
    }
    public String getStartingTurn(){
        return ourDrone.starting_turn;
    }

    public String getBehind() {
        return ourDrone.behind.toString();
    }

    public void isOcean(JSONArray biomes) {
        if (biomes.length()==1 && biomes.getString(0).equals("OCEAN")){
            overOcean = true;
        }
        else{
            overOcean = false;
        }
    }
}
