package ca.mcmaster.se2aa4.island.team210.DecisionMaker;

import ca.mcmaster.se2aa4.island.team210.Decision;

public interface DecisionGenerator {

    Decision decidingAlgorithm();

    String getState();
}
