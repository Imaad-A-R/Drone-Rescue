package ca.mcmaster.se2aa4.island.team210;

public class DecisionGeneratorIsland implements DecisionGenerator {
    Integer count=0;
    public Decision decidingAlgorithm(Decision decision, Map givenMap){
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
        else if (count < 3 + givenMap.east.getRange()){
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
