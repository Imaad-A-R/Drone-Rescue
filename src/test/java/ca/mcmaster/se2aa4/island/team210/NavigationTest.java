package ca.mcmaster.se2aa4.island.team210;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigationTest {
    private Navigation navigator = new Navigation();
    private Map m = new Map();
    private Drone d = new Drone(7000, "E");
    @Test
    public void makeADecisionFindIslandTest(){
        Decision test_decision = navigator.makeADecision(m, d);
        assertEquals("echo", test_decision.getAction());
    }
}