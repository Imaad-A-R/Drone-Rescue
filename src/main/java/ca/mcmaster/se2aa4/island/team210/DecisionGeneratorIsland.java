package ca.mcmaster.se2aa4.island.team210;

public class DecisionGeneratorIsland implements DecisionGenerator {

    enum state{
        START,
        SEARCH,
        NAVIGATE,
        DONE,
        STOP
    }
    private int counter;
    private state current_state;
    private int naviCounter = 0;
    public DecisionGeneratorIsland(){

        current_state = state.START;
        counter = 0;
    }

    boolean doLeft = false;
    public Decision decidingAlgorithm(Decision decision, Map givenMap){
        switch (current_state){
            case START:
                decision.setAction("echo");
                decision.setExtra(givenMap.getDirection());
                if (canSwitchStates(givenMap) && givenMap.getEchoType("current").equals("GROUND")){
                    switchStates(state.NAVIGATE);
                }
                else if(canSwitchStates(givenMap) && givenMap.getEchoType("current").equals("OUT_OF_RANGE")){
                    switchStates(state.SEARCH);
                }
                break;
            case SEARCH:
                if (counter%(givenMap.getRange("current")/10)!=0){
                    decision.setAction("fly");
                    decision.setExtra("");
                    counter++;
                }
                else{
                    if(!doLeft){
                        decision.setAction("echo");
                        decision.setExtra(givenMap.getRight());
                        doLeft=true;
                    }
                    else{
                        decision.setAction("echo");
                        decision.setExtra(givenMap.getLeft());
                        doLeft=false;
                        counter++;
                    }
                }
                if (canSwitchStates(givenMap)){
                    switchStates(state.NAVIGATE);
                }
                break;
            case NAVIGATE:
                if ((givenMap.getEchoType("right").equals("GROUND"))){
                    decision.setAction("heading");
                    decision.setExtra(givenMap.getRight());
                }
                else if ((givenMap.getEchoType("left").equals("GROUND"))){
                    decision.setAction("heading");
                    decision.setExtra(givenMap.getLeft());
                }
                if(naviCounter>=1){
                    decision.setAction("fly");
                    decision.setExtra("");
                }
                if (canSwitchStates(givenMap)){
                    switchStates(state.DONE);
                }
                naviCounter++;
                break;
            case DONE:
                decision.setAction("scan");
                decision.setExtra("");
                if (canSwitchStates(givenMap)){
                    switchStates(state.STOP);
                }
                break;
            case STOP:
                decision.setAction("stop");
                decision.setExtra("");
                break;
        }
        return decision;
    }

    private boolean canSwitchStates(Map givenMap) {
        boolean canSwitch = false;
        switch (current_state){
            case START:
                canSwitch = true;
                break;
            case SEARCH:
                if (givenMap.getEchoType("right").equals("GROUND")|| givenMap.getEchoType("left").equals("GROUND") ||counter >= givenMap.getRange("current")){
                    canSwitch = true;
                }
                break;
            case NAVIGATE:
                if (naviCounter-1 >= givenMap.getRange("current")){
                    canSwitch=true;
                }
                break;
            case DONE:
                canSwitch=true;
                break;
            case STOP:
                break;
        }
        return canSwitch;
    }

    private void switchStates(state s){
        current_state = s;
    }

    public String getState(){
        return current_state.toString();
    }
}
