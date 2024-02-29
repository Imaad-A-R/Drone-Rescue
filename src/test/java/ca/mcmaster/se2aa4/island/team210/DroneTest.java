package ca.mcmaster.se2aa4.island.team210;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;


public class DroneTest {

    private Drone aDrone = new Drone(7000, "E");

    @Test
    public void removeBattery(){
        aDrone.removeCost(54);
        assertEquals(6946, aDrone.getBattery());
        aDrone.removeCost(87);
        assertEquals(6859, aDrone.getBattery());
    }
    @Test
    public void handleDirectionTest(){
        aDrone.handleDirection("N");
        assertEquals("N", aDrone.getDirection());
    }
}
