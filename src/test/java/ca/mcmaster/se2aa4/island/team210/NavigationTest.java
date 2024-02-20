package ca.mcmaster.se2aa4.island.team210;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigationTest {
    private Navigation navigator = new Navigation();
    private Map m = new Map(new Drone(7000));
    @Test
    public void makeADecisionFindIslandTest(){
        Decision test_decision = navigator.makeADecision(m);
        assertEquals("fly", test_decision.getAction());
    }
}