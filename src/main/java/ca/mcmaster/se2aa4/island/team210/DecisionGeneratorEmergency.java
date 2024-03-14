package ca.mcmaster.se2aa4.island.team210;

import java.util.*;

public class DecisionGeneratorEmergency implements DecisionGenerator {


    enum state{
        SEARCHING,
        TURNING,
        DONE
    }

    private state current_state;
    private String turnDirection;
    private Queue<Decision> decQueue;

    public DecisionGeneratorEmergency(){
        decQueue = new LinkedList<>();
        current_state = state.SEARCHING;
    }
    public Decision decidingAlgorithm(Map givenMap){
        if (!decQueue.isEmpty()){
            return decQueue.remove();
        }
        switch (current_state){
            case SEARCHING:
                decQueue.add(new Decision("stop"));
                decQueue.add(new Decision("fly"));
                decQueue.add(new Decision("scan"));
                canSwitchStates(givenMap);
                break;
        }
        return decQueue.remove();
    }

    private void canSwitchStates(Map givenMap) {
        switch (current_state){
            case SEARCHING:
                //if (mapper.overOcean==true){
                if (true){
                    decQueue.add(new Decision("echo", givenMap.getDirection()));
                    switchStates(state.TURNING);
                }
                break;
            case TURNING:
                if (givenMap.getEchoType("current").equals("OUT_OF_RANGE")){
                    //turn in correct direction;
                    decQueue.add(new Decision("heading", turnDirection));
                    decQueue.add(new Decision("heading", givenMap.getBehind()));
                    //turnDirection is other possible direction (N and S, E and W)
                    turnDirection = "E";
                    switchStates(state.SEARCHING);
                }
                else{
                    //fly until next piece of land - 1 (because it flies first and then scans);
                    for (int i = 0; i < givenMap.getRange("current") - 1; i++){
                        decQueue.add(new Decision("fly"));
                    }
                    switchStates(state.SEARCHING);
                }
                break;
            case DONE:
                break;
            default:
                break;
        }
    }

    private void switchStates(state s){
        current_state = s;
    }

    public String getState(){
        return "";
    }
}
