package ca.mcmaster.se2aa4.island.team210;

public class Navigation {

    enum State{
        FIND_ISLAND,
        FIND_EMERGENCY,
        FIND_CREEKS
    }

    State currentState;
    int count;

    public String[] makeADecision(Map givenMap) {
        String[] decisionArray = new String[2];
        currentState = State.FIND_ISLAND;
        switch (currentState){
            case FIND_ISLAND:
                decisionArray = makeADecisionFindIsland(decisionArray, givenMap);
                break;
            case FIND_EMERGENCY:
                decisionArray = makeADecisionFindEmergency(decisionArray, givenMap);
                break;
            case FIND_CREEKS:
                decisionArray = makeADecisionFindCreeks(decisionArray, givenMap);
                break;
            default:
                break;
        }
        return decisionArray;
    }

    private String[] makeADecisionFindCreeks(String[] decisionArray, Map givenMap) {
        return decisionArray;
    }

    private String[] makeADecisionFindEmergency(String[] decisionArray, Map givenMap) {
        return decisionArray;
    }

    private String[] makeADecisionFindIsland(String[] decisionArray, Map givenMap) {
        if (count == 0){
            decisionArray[0] = "echo";
            decisionArray[1] = "S";
        }
        else if (count == 1){
            decisionArray[0] = "echo";
            decisionArray[1] = "N";
        }
        else if (count == 2){
            decisionArray[0] = "echo";
            decisionArray[1] = "E";
        }
        else if (count < 3 + givenMap.get_east()){
            decisionArray[0] = "fly";
            decisionArray[1] = "";
        }
        else{
            decisionArray[0] = "stop";
            decisionArray[1] = "";
        }
        count++;
        return decisionArray;
    }
}
