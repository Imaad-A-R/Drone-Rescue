package ca.mcmaster.se2aa4.island.team210;

import ca.mcmaster.se2aa4.island.team210.DecisionMaker.DecisionGenerator;
import ca.mcmaster.se2aa4.island.team210.DecisionMaker.DecisionGeneratorEmergency;
import ca.mcmaster.se2aa4.island.team210.DecisionMaker.DecisionGeneratorIsland;

public class Navigation {

    enum State{
        FIND_ISLAND,
        FIND_EMERGENCY,
        DONE
    }


    State currentState;
    DecisionGenerator findIsland;
    DecisionGenerator findEmergency;

    Navigation(Map map, Drone drone){
        currentState = State.FIND_ISLAND;
        findIsland = new DecisionGeneratorIsland(map, drone);
        findEmergency = new DecisionGeneratorEmergency(map, drone);
    }

    public Decision makeADecision() {
        Decision decision = new Decision();
        switch (currentState) {
            case FIND_ISLAND:
                decision = findIsland.decidingAlgorithm();
                if (findIsland.getState().equals("DONE")) {
                    moveToNextState();
                }
                break;
            case FIND_EMERGENCY:
                decision = findEmergency.decidingAlgorithm();
                if (findEmergency.getState().equals("DONE")) {
                    moveToNextState();
                }
                break;
            default:
                break;
        }
        return decision;
    }

    private void moveToNextState(){
        switch (currentState){
            case FIND_ISLAND:
                currentState = State.FIND_EMERGENCY;
                break;
            case FIND_EMERGENCY:
                currentState = State.DONE;
                break;
            case DONE:
                break;
        }
    }
}
