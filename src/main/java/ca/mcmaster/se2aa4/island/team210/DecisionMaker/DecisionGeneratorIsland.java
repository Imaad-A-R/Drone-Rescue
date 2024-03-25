package ca.mcmaster.se2aa4.island.team210.DecisionMaker;

import ca.mcmaster.se2aa4.island.team210.Decision;
import ca.mcmaster.se2aa4.island.team210.Drone;
import ca.mcmaster.se2aa4.island.team210.Map;

import java.util.*;

public class DecisionGeneratorIsland implements DecisionGenerator {

    enum state{
        START,
        SEARCH,
        NAVIGATE,
        LOOPING,
        DONE,
    }
    private Map givenMap;
    private Drone givenDrone;
    private state current_state;
    private final String right = "right";
    private Queue<Decision> decQueue;

    public DecisionGeneratorIsland(Map map, Drone drone){

        current_state = state.START;
        decQueue = new LinkedList<>();
        givenMap = map;
        givenDrone = drone;
    }


    public Decision decidingAlgorithm(){
        if (!decQueue.isEmpty()){
            return decQueue.remove();
        }
        switch (current_state){
            case START:
                decQueue.add(new Decision("echo", givenDrone.returnDirection("current")));
                decQueue.add(new Decision("echo", givenDrone.returnDirection(right)));
                decQueue.add(new Decision("echo", givenDrone.returnDirection("left")));
                canSwitchStates();
                break;
            case SEARCH:
                canSwitchStates();
                if (current_state.equals(state.NAVIGATE)){
                    decQueue.add(new Decision("scan"));
                }
                else {
                    decQueue.add(new Decision("fly"));
                    decQueue.add(new Decision("echo", givenDrone.returnDirection("left")));
                    decQueue.add(new Decision("echo", givenDrone.returnDirection(right)));
                }
                break;
            case NAVIGATE:
                if (givenMap.getEchoType("left", givenDrone).equals("GROUND")) {
                    decQueue.add(new Decision("heading", givenDrone.returnDirection("left")));
                    givenDrone.setStartingTurn("left");

                    for (int i = 0; i < givenMap.getRange("left", givenDrone) - 1; i++){
                        decQueue.add(new Decision("fly"));
                    }
                }
                else if (givenMap.getEchoType(right, givenDrone).equals("GROUND")){
                    decQueue.add(new Decision("heading", givenDrone.returnDirection(right)));
                    givenDrone.setStartingTurn(right);

                    for (int i = 0; i < givenMap.getRange(right, givenDrone) - 1; i++){
                        decQueue.add(new Decision("fly"));
                    }
                }
                else{
                    for (int i = 0; i < givenMap.getRange("current", givenDrone); i++){
                        decQueue.add(new Decision("fly"));
                    }
                }

                decQueue.add(new Decision("scan"));
                canSwitchStates();
                break;
            case LOOPING:
                canSwitchStates();
                decQueue.add(new Decision("scan"));
                break;
            case DONE:
                break;
            default:
                break;
        }
        return decQueue.remove();

    }


    private void canSwitchStates() {
        switch (current_state){
            case START:
                if (givenMap.getEchoType("current", givenDrone).equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                }
                else if (givenMap.getEchoType(right, givenDrone).equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                }
                else if (givenMap.getEchoType("left", givenDrone).equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                }
                else{
                    switchStates(state.SEARCH);
                }
                break;
            case SEARCH:
                if (!givenMap.getEchoType(right, givenDrone).equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                    decQueue.clear();
                }
                else if (!givenMap.getEchoType("left", givenDrone).equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                    decQueue.clear();
                }
                break;
            case NAVIGATE:
                switchStates(state.LOOPING);
                break;
            case LOOPING:
                if (decQueue.isEmpty()) {
                    switchStates(state.DONE);
                }
            case DONE:
                break;
        }
    }

    private void switchStates(state s){
        current_state = s;
    }

    public String getState(){
        return current_state.toString();
    }
}
