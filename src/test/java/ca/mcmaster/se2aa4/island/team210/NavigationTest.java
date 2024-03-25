package ca.mcmaster.se2aa4.island.team210;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigationTest {
    private Map m = new Map();
    private Drone d = new Drone(7000, "E");
    private Navigation navigator = new Navigation(m, d);
    @Test
    public void testMakeADecisionFindIsland(){
        Decision test_decision = navigator.makeADecision(m, d);
        assertEquals("echo", test_decision.getAction());
    }
}