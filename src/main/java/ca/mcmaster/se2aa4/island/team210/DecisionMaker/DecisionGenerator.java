package ca.mcmaster.se2aa4.island.team210.DecisionMaker;

import ca.mcmaster.se2aa4.island.team210.Decision;
import ca.mcmaster.se2aa4.island.team210.Drone;
import ca.mcmaster.se2aa4.island.team210.Map;

public interface DecisionGenerator {

    Decision decidingAlgorithm(Map givenMap, Drone givenDrone);

    String getState();

    enum state{}
}
