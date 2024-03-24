package ca.mcmaster.se2aa4.island.team210;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    public void testInterpretResults(){
        Decision decision = new Decision("echo","N");
        Drone drone = new Drone(7000,"N");
        Map map = new Map();
        map.storeDecisionInfo(decision,drone);
        String s = "{\"cost\": 5, \"extras\": {\"found\": \"GROUND\", \"range\": 2}, \"status\": \"OK\"}";
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        JSONObject extraInfo = response.getJSONObject("extras");
        map.interpretResults(extraInfo);

        assertEquals(2, map.getRange("current", drone));
        assertEquals("GROUND", map.getEchoType("current", drone));
    }
}