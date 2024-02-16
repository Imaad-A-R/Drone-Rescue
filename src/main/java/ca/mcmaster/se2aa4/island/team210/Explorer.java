package ca.mcmaster.se2aa4.island.team210;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    Map mapper = new Map();
    Navigation decisionMaker = new Navigation();

    Drone ourDrone;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
        ourDrone = new Drone(batteryLevel);
    }

    @Override
    public String takeDecision() {
        String[] givenDecision = decisionMaker.makeADecision(mapper);
        JSONObject decision = new JSONObject();
        switch(givenDecision[0]){
            case "fly":
                decision.put("action", "fly");
                break;
            case "heading":
                decision.put("action", "heading");
                decision.put("parameters", (new JSONObject()).put("direction", givenDecision[1]));
                break;
            case "echo":
                decision.put("action", "echo");
                decision.put("parameters", (new JSONObject()).put("direction", givenDecision[1]));
                break;
            case "scan":
                decision.put("action", "scan");
                break;
            case "stop":
                decision.put("action", "stop");
                break;
        }
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);

        ourDrone.removeCost(cost);
        logger.info(ourDrone.getBattery());

        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        mapper.interpretResults(extraInfo);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
