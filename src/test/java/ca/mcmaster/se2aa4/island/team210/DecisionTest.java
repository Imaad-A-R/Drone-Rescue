package ca.mcmaster.se2aa4.island.team210;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecisionTest {

    @Test
    public void testDefaultDecision(){
        Decision aDecision = new Decision();
        assertEquals("", aDecision.getAction());
        assertEquals("", aDecision.getExtra());
    }
    
    @Test void testActionDecision(){
        Decision aDecision = new Decision("echo");
        assertEquals("echo", aDecision.getAction());
        assertEquals("", aDecision.getExtra());
    }

    @Test void testDecision(){
        Decision decision = new Decision("echo","N");
        assertEquals("echo",decision.getAction());
        assertEquals("N",decision.getExtra());
    }

}