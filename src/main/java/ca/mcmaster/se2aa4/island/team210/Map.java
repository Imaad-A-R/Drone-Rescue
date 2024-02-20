package ca.mcmaster.se2aa4.island.team210;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;




public class Map {

    Integer east_end;

    private final Logger logger = LogManager.getLogger();

    Drone ourDrone;
    public Map(Drone drone) {
        ourDrone = drone;
    }

    public void interpretResults(JSONObject info){
        if (info.has("range")){
            east_end = info.getInt("range");
        }
    }
    public Integer get_east(){
        return this.east_end;
    }

    public void storeDecisionInfo(Decision givenDecision) {
        switch (givenDecision.getAction()){
            case "heading":
                ourDrone.handleDirection(givenDecision.getExtra());
                break;
            case "fly":
                ourDrone.move();
                break;
        }
    }

    public void applyCost(Integer cost) {
        ourDrone.removeCost(cost);
    }
}
