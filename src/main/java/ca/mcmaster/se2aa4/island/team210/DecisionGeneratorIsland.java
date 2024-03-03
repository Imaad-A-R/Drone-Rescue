package ca.mcmaster.se2aa4.island.team210;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private final Logger logger = LogManager.getLogger();

    Queue<Decision> decQueue;

    public DecisionGeneratorIsland(){

        current_state = state.START;
        counter = 0;
        decQueue = new LinkedList<>();
    }


    public Decision decidingAlgorithm(Map givenMap){
        Decision decision = new Decision();
        if (!decQueue.isEmpty()){
            return decQueue.remove();
        }
        switch (current_state){
            case START:
                decision.setAction("echo");
                decision.setExtra(givenMap.getDirection());

                decQueue.add(decision);
                Decision decision2 = new Decision();
                decision2.setAction("echo");
                decision2.setExtra(givenMap.getLeft());
                decQueue.add(decision2);
                Decision decision3 = new Decision();
                decision3.setAction("echo");
                decision3.setExtra(givenMap.getRight());
                decQueue.add(decision3);
                canSwitchStates(givenMap);
                break;
            case SEARCH:
                canSwitchStates(givenMap);
                for (int i = 0; i < 3; i++){
                    Decision dec = new Decision();
                    dec.setAction("fly");
                    dec.setExtra("");
                    decQueue.add(dec);
                }
                Decision decision4 = new Decision();
                decision4.setAction("echo");
                decision4.setExtra(givenMap.getLeft());
                decQueue.add(decision4);
                Decision decision5 = new Decision();
                decision5.setAction("echo");
                decision5.setExtra(givenMap.getRight());
                decQueue.add(decision5);
                break;
            case NAVIGATE:

                if (givenMap.getEchoType("left").equals("GROUND")) {
                    Decision decision6 = new Decision();
                    decision6.setAction("heading");
                    decision6.setExtra(givenMap.getLeft());
                    decQueue.add(decision6);

                    for (int i = 0; i < givenMap.getRange("left") - 1; i++){
                        Decision decl = new Decision();
                        decl.setAction("fly");
                        decl.setExtra("");
                        decQueue.add(decl);
                    }
                }
                else if (givenMap.getEchoType("right").equals("GROUND")){
                    Decision decision7 = new Decision();
                    decision7.setAction("heading");
                    decision7.setExtra(givenMap.getRight());
                    decQueue.add(decision7);

                    for (int i = 0; i < givenMap.getRange("right") - 1; i++){
                        Decision decl = new Decision();
                        decl.setAction("fly");
                        decl.setExtra("");
                        decQueue.add(decl);
                    }
                }
                else{
                    for (int i = 0; i < givenMap.getRange("current"); i++){
                        Decision decf = new Decision();
                        decf.setAction("fly");
                        decf.setExtra("");
                        decQueue.add(decf);
                    }
                }

                Decision decs = new Decision();
                decs.setAction("scan");
                decs.setExtra("");
                decQueue.add(decs);
                canSwitchStates(givenMap);
                break;
            case DONE:
                Decision decstop = new Decision();
                decstop.setAction("stop");
                decstop.setExtra("");
                decQueue.add(decstop);
                break;
        }
        counter ++;
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
                canSwitch=true;
                break;
            case STOP:
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
