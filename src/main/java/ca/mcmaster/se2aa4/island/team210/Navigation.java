package ca.mcmaster.se2aa4.island.team210;

public class Navigation {

    enum State{
        FIND_ISLAND,
        FIND_EMERGENCY,
        DONE
    }

    State currentState;
    DecisionGenerator findIsland = new DecisionGeneratorIsland();
    DecisionGenerator findEmergency = new DecisionGeneratorEmergency();

    public Decision makeADecision(Map givenMap) {
        Decision decision = new Decision();
        currentState = State.FIND_ISLAND;
        switch (currentState) {
            case FIND_ISLAND:
                decision = findIsland.decidingAlgorithm(givenMap);
                if (findIsland.getState().equals("DONE")) {
                    moveToNextState();
                }
                break;
            case FIND_EMERGENCY:
                decision = findEmergency.decidingAlgorithm(givenMap);
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
