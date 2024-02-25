package ca.mcmaster.se2aa4.island.team210;

public class DecisionGeneratorIsland implements DecisionGenerator {

    enum state{
        START,
        SEARCH,
        NAVIGATE,
        DONE
    }
    private int counter;
    private state current_state;

    public DecisionGeneratorIsland(){

        current_state = state.START;
        counter = 0;
    }

    public Decision decidingAlgorithm(Decision decision, Map givenMap){
        switch (current_state){
            case START:
                decision.setAction("echo");
                decision.setExtra(givenMap.getDirection());
                if (canSwitchStates(givenMap)){
                    switchStates(state.SEARCH);
                }
                break;
            case SEARCH:
                if (givenMap.east.getRange() > 3){
                    decision.setAction("fly");
                    decision.setExtra("");
                }
                else{
                    decision.setAction("echo");
                    decision.setExtra("E");
                }
                if (canSwitchStates(givenMap)){
                    switchStates(state.NAVIGATE);
                }
                break;
            case NAVIGATE:
                decision.setAction("stop");
                decision.setExtra("");
                if (canSwitchStates(givenMap)){
                    switchStates(state.DONE);
                }
                break;
            case DONE:
                break;
        }
        counter ++;
        return decision;
    }

    private boolean canSwitchStates(Map givenMap) {
        boolean canSwitch = false;
        switch (current_state){
            case START:
                canSwitch = true;
                break;
            case SEARCH:
                if (counter >= givenMap.east.getRange()){
                    canSwitch = true;
                }
                break;
            case NAVIGATE:
                canSwitch = true;
                break;
            case DONE:
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
