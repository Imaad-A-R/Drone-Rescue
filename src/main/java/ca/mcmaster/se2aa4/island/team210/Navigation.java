package ca.mcmaster.se2aa4.island.team210;

public class Navigation {

    enum State{
        FIND_ISLAND,
        FIND_EMERGENCY,
        FIND_CREEKS
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
                break;
            case FIND_EMERGENCY:
                decision = findEmergency.decidingAlgorithm(decision, givenMap);
                break;
            case FIND_CREEKS:
                decision = findCreeks.decidingAlgorithm(decision, givenMap);
                break;
            default:
                break;
        }
        return decision;
    }
}
