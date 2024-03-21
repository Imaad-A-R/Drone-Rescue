package ca.mcmaster.se2aa4.island.team210;

import java.util.*;

public class DecisionGeneratorEmergency implements DecisionGenerator {

    private int counter;
    enum state{
        SEARCHING,
        TURNING,
        CHECK,
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
                decQueue.add(new Decision("fly"));
                decQueue.add(new Decision("scan"));
                canSwitchStates(givenMap);
                break;
            case TURNING:
                canSwitchStates(givenMap);
                break;
            case CHECK:
                canSwitchStates(givenMap);
        }
        return decQueue.remove();
    }

    private void canSwitchStates(Map givenMap) {
        if(givenMap.ourDrone.battery<100){
            decQueue.add(new Decision("stop"));
        }
        switch (current_state){
            case SEARCHING:
                if (givenMap.overOcean){
                    decQueue.clear();
                    decQueue.add(new Decision("echo", givenMap.getDirection()));
                    if (givenMap.getStartingTurn().equals("left")){
                        decQueue.add(new Decision("echo", givenMap.getRight()));
                    }
                    else{
                        decQueue.add(new Decision("echo", givenMap.getLeft()));
                    }
                    switchStates(state.TURNING);
                }
                break;
            case TURNING:
                if (givenMap.getEchoType("current").equals("OUT_OF_RANGE")){
                    if(givenMap.getStartingTurn().equals("left")) {
                        if (givenMap.getRange("right")<2){
                            decQueue.add(new Decision("fly"));
                            decQueue.add(new Decision("echo", givenMap.getRight()));
                        }
                        else{
                            turn(givenMap);
                        }
                    }
                    else{
                        if (givenMap.getRange("left")<2){
                            decQueue.add(new Decision("fly"));
                            decQueue.add(new Decision("echo", givenMap.getLeft()));
                        }
                        else{
                            turn(givenMap);
                        }
                    }
                    //turn in correct direction;
                    /*
                    if (givenMap.getStartingTurn().equals("left")){
                        decQueue.add(new Decision("heading", givenMap.getRight()));
                        givenMap.setDroneStartingTurn("right");
                    }
                    else{
                        decQueue.add(new Decision("heading", givenMap.getLeft()));
                        givenMap.setDroneStartingTurn("left");
                    }
                    decQueue.add(new Decision("heading", givenMap.getBehind()));
                    decQueue.add(new Decision("scan"));
                    decQueue.add(new Decision("echo", givenMap.getBehind()));
                    //turnDirection is other possible direction (N and S, E and W)
                    switchStates(state.CHECK);
                    */
                }
                else{
                    //fly until next piece of land - 1 (because it flies first and then scans);
                    for (int i = 0; i < givenMap.getRange("current")+1; i++){
                        decQueue.add(new Decision("fly"));
                    }
                    switchStates(state.CHECK);
                }
                break;
            case CHECK:
                if(counter<2) {
                    if (givenMap.getEchoType("current").equals("OUT_OF_RANGE")) {
                        counter++;
                        if (givenMap.getStartingTurn().equals("left")) {
                            decQueue.add(new Decision("heading", givenMap.getLeft()));
                            decQueue.add(new Decision("fly"));
                            decQueue.add(new Decision("heading", givenMap.getBehind()));
                            decQueue.add(new Decision("heading", givenMap.getRight()));
                            decQueue.add(new Decision("heading", givenMap.getDirection()));
                            givenMap.setDroneStartingTurn("right");
                        } else {
                            decQueue.add(new Decision("heading", givenMap.getRight()));
                            decQueue.add(new Decision("fly"));
                            decQueue.add(new Decision("heading", givenMap.getBehind()));
                            decQueue.add(new Decision("heading", givenMap.getLeft()));
                            decQueue.add(new Decision("heading", givenMap.getDirection()));
                            givenMap.setDroneStartingTurn("left");
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

    private void switchStates(state s){
        current_state = s;
    }
    private void turn(Map givenMap){
        if (givenMap.getStartingTurn().equals("left")){
            decQueue.add(new Decision("heading", givenMap.getRight()));
            givenMap.setDroneStartingTurn("right");
        }
        else{
            decQueue.add(new Decision("heading", givenMap.getLeft()));
            givenMap.setDroneStartingTurn("left");
        }
        decQueue.add(new Decision("heading", givenMap.getBehind()));
        decQueue.add(new Decision("echo", givenMap.getBehind()));
        //turnDirection is other possible direction (N and S, E and W)
        switchStates(state.CHECK);
    }

    public String getState(){
        return "";
    }
}
