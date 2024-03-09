package ca.mcmaster.se2aa4.island.team210;

import java.util.*;

public class DecisionGeneratorIsland implements DecisionGenerator {

    enum state{
        START,
        SEARCH,
        NAVIGATE,
        DONE,
    }
    private state current_state;

    Queue<Decision> decQueue;

    public DecisionGeneratorIsland(){

        current_state = state.START;
        decQueue = new LinkedList<>();
    }


    public Decision decidingAlgorithm(Map givenMap){
        if (!decQueue.isEmpty()){
            return decQueue.remove();
        }
        switch (current_state){
            case START:
                decQueue.add(new Decision("echo", givenMap.getDirection()));
                decQueue.add(new Decision("echo", givenMap.getRight()));
                decQueue.add(new Decision("echo", givenMap.getLeft()));
                canSwitchStates(givenMap);
                break;
            case SEARCH:
                canSwitchStates(givenMap);
                for (int i = 0; i < 3; i++){
                    decQueue.add(new Decision("fly"));
                }
                decQueue.add(new Decision("echo", givenMap.getLeft()));
                decQueue.add(new Decision("echo", givenMap.getRight()));
                break;
            case NAVIGATE:

                if (givenMap.getEchoType("left").equals("GROUND")) {
                    decQueue.add(new Decision("heading", givenMap.getLeft()));

                    for (int i = 0; i < givenMap.getRange("left") - 1; i++){
                        decQueue.add(new Decision("fly"));
                    }
                }
                else if (givenMap.getEchoType("right").equals("GROUND")){
                    decQueue.add(new Decision("heading", givenMap.getRight()));

                    for (int i = 0; i < givenMap.getRange("right") - 1; i++){
                        decQueue.add(new Decision("fly"));
                    }
                }
                else{
                    for (int i = 0; i < givenMap.getRange("current"); i++){
                        decQueue.add(new Decision("fly"));
                    }
                }

                decQueue.add(new Decision("scan"));
                canSwitchStates(givenMap);
                break;
            case DONE:
                decQueue.add(new Decision("stop"));
                break;
        }
        return decQueue.remove();

    }

    private void canSwitchStates(Map givenMap) {
        switch (current_state){
            case START:
                if (givenMap.getEchoType("current").equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                }
                else if (givenMap.getEchoType("right").equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                }
                else if (givenMap.getEchoType("left").equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                }
                else{
                    switchStates(state.SEARCH);
                }
                break;
            case SEARCH:

                if (!givenMap.getEchoType("right").equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                    decQueue.clear();
                }
                else if (!givenMap.getEchoType("left").equals("OUT_OF_RANGE")) {
                    switchStates(state.NAVIGATE);
                    decQueue.clear();
                 }
                break;
            case NAVIGATE:
                switchStates(state.DONE);
                break;
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
