package ca.mcmaster.se2aa4.island.team210;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class DirectionInfoTest {
    @Test
    public void testSetInfo(){
        DirectionInfo directionInfo = new DirectionInfo();
        String s = "{\"cost\": 5, \"extras\": {\"found\": \"GROUND\", \"range\": 2}, \"status\": \"OK\"}";
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        JSONObject extraInfo = response.getJSONObject("extras");
        directionInfo.setInfo(extraInfo);

        assertEquals(2, directionInfo.getRange());
        assertEquals("GROUND", directionInfo.getEchoType());
    }

}