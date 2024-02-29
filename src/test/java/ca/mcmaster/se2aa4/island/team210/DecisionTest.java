package ca.mcmaster.se2aa4.island.team210;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecisionTest {

    @Test
    public void defaultDecisionTest(){
        Decision aDecision = new Decision();
        assertEquals("", aDecision.getAction());
        assertEquals("", aDecision.getExtra());
    }
    
    @Test void actionDecisionTest(){
        Decision aDecision = new Decision("echo");
        assertEquals("echo", aDecision.getAction());
        assertEquals("", aDecision.getExtra());
    }

    @Test void extraDecisionTest(){

    }

}