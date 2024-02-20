package ca.mcmaster.se2aa4.island.team210;

public class Navigation {

    enum State{
        FIND_ISLAND,
        FIND_EMERGENCY,
        FIND_CREEKS
    }

    State currentState;
    int count;

    public Decision makeADecision(Map givenMap) {
        Decision decision = new Decision();
        currentState = State.FIND_ISLAND;
        switch (currentState){
            case FIND_ISLAND:
                decision = makeADecisionFindIsland(decision, givenMap);
                break;
            case FIND_EMERGENCY:
                decision = makeADecisionFindEmergency(decision, givenMap);
                break;
            case FIND_CREEKS:
                decision = makeADecisionFindCreeks(decision, givenMap);
                break;
            default:
                break;
        }
        return decision;
    }

    private Decision makeADecisionFindCreeks(Decision decision, Map givenMap) {
        return decision;
    }

    private Decision makeADecisionFindEmergency(Decision decision, Map givenMap) {
        return decision;
    }

    private Decision makeADecisionFindIsland(Decision decision, Map givenMap) {
        if (count == 0){
            decision.setAction("echo");
            decision.setExtra("S");
        }
        else if (count == 1){
            decision.setAction("echo");
            decision.setExtra("N");
        }
        else if (count == 2){
            decision.setAction("echo");
            decision.setExtra("E");
        }
        else if (count < 3 + givenMap.get_east()){
            decision.setAction("fly");
            decision.setExtra("");
        }
        else{
            decision.setAction("stop");
            decision.setExtra("");
        }
        count++;
        return decision;
    }
}
