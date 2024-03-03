package ca.mcmaster.se2aa4.island.team210;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    Map mapper;
    Navigation decisionMaker = new Navigation();


    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
        mapper = new Map(new Drone(batteryLevel, direction));
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        Decision givenDecision;
        givenDecision = decisionMaker.makeADecision(mapper);

        switch(givenDecision.getAction()){
            case "fly":
                decision.put("action", "fly");
                mapper.storeDecisionInfo(givenDecision);
                break;
            case "heading":
                decision.put("action", "heading");
                decision.put("parameters", (new JSONObject()).put("direction", givenDecision.getExtra()));
                mapper.storeDecisionInfo(givenDecision);
                break;
            case "echo":
                decision.put("action", "echo");
                decision.put("parameters", (new JSONObject()).put("direction", givenDecision.getExtra()));
                mapper.storeDecisionInfo(givenDecision);
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

        mapper.applyCost(cost);

        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        mapper.interpretResults(extraInfo);
        logger.info(mapper.ourDrone.getBattery());
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
