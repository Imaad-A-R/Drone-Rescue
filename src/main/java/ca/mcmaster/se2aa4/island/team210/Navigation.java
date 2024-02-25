package ca.mcmaster.se2aa4.island.team210;

public class Navigation {

    enum State{
        FIND_ISLAND,
        FIND_EMERGENCY,
        FIND_CREEKS,
        DONE
    }

    State currentState;
    DecisionGenerator findIsland = new DecisionGeneratorIsland();
    DecisionGenerator findEmergency = new DecisionGeneratorEmergency();
    DecisionGenerator findCreeks = new DecisionGeneratorCreek();

    public Decision makeADecision(Map givenMap) {
        Decision decision = new Decision();
        currentState = State.FIND_ISLAND;
        switch (currentState){
            case FIND_ISLAND:
                decision = findIsland.decidingAlgorithm(decision, givenMap);
                if (findIsland.getState().equals("DONE")){
                    moveToNextState();
                }
                break;
            case FIND_EMERGENCY:
                decision = findEmergency.decidingAlgorithm(decision, givenMap);
                if (findEmergency.getState().equals("DONE")){
                    moveToNextState();
                }
                break;
            case FIND_CREEKS:
                decision = findCreeks.decidingAlgorithm(decision, givenMap);
                if (findCreeks.getState().equals("DONE")){
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
                currentState = State.FIND_CREEKS;
                break;
            case FIND_CREEKS:
                currentState = State.DONE;
                break;
            case DONE:
                break;
        }
    }
}
