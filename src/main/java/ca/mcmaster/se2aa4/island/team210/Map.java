package ca.mcmaster.se2aa4.island.team210;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;




public class Map {

    DirectionInfo east = new DirectionInfo();
    DirectionInfo west = new DirectionInfo();
    DirectionInfo north = new DirectionInfo();
    DirectionInfo south = new DirectionInfo();

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
        }
    }

    public void applyCost(Integer cost) {
        ourDrone.removeCost(cost);
    }
}
