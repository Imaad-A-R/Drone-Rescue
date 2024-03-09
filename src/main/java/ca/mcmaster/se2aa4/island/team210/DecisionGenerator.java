package ca.mcmaster.se2aa4.island.team210;

public interface DecisionGenerator {

    Decision decidingAlgorithm(Map givenMap);

    String getState();

    enum state{}
}
