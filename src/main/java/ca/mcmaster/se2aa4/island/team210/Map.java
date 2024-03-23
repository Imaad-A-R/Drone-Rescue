package ca.mcmaster.se2aa4.island.team210;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


public class Map {

    private DirectionInfo east = new DirectionInfo();
    private DirectionInfo west = new DirectionInfo();
    private DirectionInfo north = new DirectionInfo();
    private DirectionInfo south = new DirectionInfo();
    public boolean overOcean;
    private String last_echo;

    private final Logger logger = LogManager.getLogger();

    public Map(){}

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

    public void storeDecisionInfo(Decision givenDecision, Drone givenDrone) {
        switch (givenDecision.getAction()){
            case "heading":
                givenDrone.handleDirection(givenDecision.getExtra());
                break;
            case "fly":
                givenDrone.move();
                break;
            case "echo":
                last_echo = givenDecision.getExtra();
                break;
            default:
                break;
        }
    }

    public Integer getRange(String orientation, Drone givenDrone){
        String direc = givenDrone.returnDirection(orientation);
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

    public String getEchoType(String orientation, Drone givenDrone){
        String direc = givenDrone.returnDirection(orientation);
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

    public void isOcean(JSONArray biomes) {
        if (biomes.length()==1 && biomes.getString(0).equals("OCEAN")){
            overOcean = true;
        }
        else{
            overOcean = false;
        }
    }
}
