package ca.mcmaster.se2aa4.island.team210.DecisionMaker;

import ca.mcmaster.se2aa4.island.team210.Decision;
import ca.mcmaster.se2aa4.island.team210.Drone;
import ca.mcmaster.se2aa4.island.team210.Map;

import java.util.*;

public class DecisionGeneratorEmergency implements DecisionGenerator {

    private int counter;
    private Map givenMap;
    private Drone givenDrone;

    enum state{
        SEARCHING,
        TURNING,
        CHECK,
        DONE
    }

    private state current_state;
    private Queue<Decision> decQueue;
    private final String current = "current";
    private final String right = "right";
    private final String left = "left";
    private final String heading = "heading";

    public DecisionGeneratorEmergency(Map map, Drone drone){
        decQueue = new LinkedList<>();
        current_state = state.SEARCHING;
        givenMap = map;
        givenDrone = drone;
    }
    public Decision decidingAlgorithm(){
        if (!decQueue.isEmpty()){
            return decQueue.remove();
        }
        switch (current_state){
            case SEARCHING:
                decQueue.add(new Decision("fly"));
                decQueue.add(new Decision("scan"));
                canSwitchStates();
                break;
            case TURNING:
                canSwitchStates();
                break;
            case CHECK:
                canSwitchStates();
            case DONE:
                break;
            default:
                break;
        }
        return decQueue.remove();
    }

    private void canSwitchStates() {
        if(givenDrone.getBattery()<100){
            decQueue.add(new Decision("stop"));
        }
        switch (current_state){
            case SEARCHING:
                if (givenMap.overOcean){
                    decQueue.clear();
                    decQueue.add(new Decision("echo", givenDrone.returnDirection(current)));
                    if (givenDrone.getStartingTurn().equals(left)){
                        decQueue.add(new Decision("echo", givenDrone.returnDirection(right)));
                    }
                    else{
                        decQueue.add(new Decision("echo", givenDrone.returnDirection(left)));
                    }
                    switchStates(state.TURNING);
                }
                break;
            case TURNING:
                if (givenMap.getEchoType(current, givenDrone).equals("OUT_OF_RANGE")){
                    if(givenDrone.getStartingTurn().equals(left)) {
                        if (givenMap.getRange(right, givenDrone)<2){
                            decQueue.add(new Decision("fly"));
                            decQueue.add(new Decision("echo", givenDrone.returnDirection(right)));
                        }
                        else{
                            turn(givenDrone);
                        }
                    }
                    else{
                        if (givenMap.getRange(left, givenDrone)<2){
                            decQueue.add(new Decision("fly"));
                            decQueue.add(new Decision("echo", givenDrone.returnDirection(left)));
                        }
                        else{
                            turn(givenDrone);
                        }
                    }
                }
                else{
                    for (int i = 0; i < givenMap.getRange(current, givenDrone); i++){
                        decQueue.add(new Decision("fly"));
                    }
                    decQueue.add(new Decision("scan"));
                    decQueue.add(new Decision("fly"));
                    switchStates(state.CHECK);
                }
                break;
            case CHECK:
                if(counter<2) {
                    if (givenMap.getEchoType(current, givenDrone).equals("OUT_OF_RANGE")) {
                        counter++;
                        if (givenDrone.getStartingTurn().equals(left)) {
                            moveInterlace(givenDrone, left, right);
                        } else {
                            moveInterlace(givenDrone, right, left);
                        }
                    }
                    else{
                        decQueue.add(new Decision("scan"));
                    }
                }
                else{
                    decQueue.add(new Decision("stop"));
                }
                switchStates(state.SEARCHING);
                break;
            case DONE:
                break;
            default:
                break;
        }
    }

    private void moveInterlace(Drone givenDrone, String direcIn, String direcOut) {
        decQueue.add(new Decision(heading, givenDrone.returnDirection(direcIn)));
        decQueue.add(new Decision("fly"));
        decQueue.add(new Decision(heading, givenDrone.returnDirection("behind")));
        decQueue.add(new Decision(heading, givenDrone.returnDirection(direcOut)));
        decQueue.add(new Decision(heading, givenDrone.returnDirection(current)));
        givenDrone.setStartingTurn(direcOut);
    }

    private void switchStates(state s){
        current_state = s;
    }
    private void turn(Drone givenDrone){
        if (givenDrone.getStartingTurn().equals(left)){
            decQueue.add(new Decision(heading, givenDrone.returnDirection(right)));
            givenDrone.setStartingTurn(right);
        }
        else{
            decQueue.add(new Decision(heading, givenDrone.returnDirection(left)));
            givenDrone.setStartingTurn(left);
        }
        decQueue.add(new Decision(heading, givenDrone.returnDirection("behind")));
        decQueue.add(new Decision("echo", givenDrone.returnDirection("behind")));
        //turnDirection is other possible direction (N and S, E and W)
        switchStates(state.CHECK);
    }

    public String getState(){
        return "";
    }
}
