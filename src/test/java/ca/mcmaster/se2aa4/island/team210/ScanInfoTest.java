package ca.mcmaster.se2aa4.island.team210;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScanInfoTest {
    @Test
    public void testInterpretResults(){
        String s = "{\"cost\": 5, \"extras\": {\"creeks\": [\"abc123\"], \"biomes\": [\"SHRUBLAND\"], \"sites\": [\"ab12\"]}, \"status\": \"OK\"}";
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        JSONObject extraInfo = response.getJSONObject("extras");
        ScanInfo scan = new ScanInfo();
        Integer[] myArray = new Integer[]{1,2};
        scan.interpretResults(myArray, extraInfo);

        List<POI> creek = scan.getCreekList();
        assertEquals("abc123", creek.get(0).id);

        POI emergencySite = scan.getEmergencySite();
        assertEquals("ab12", emergencySite.id);
    }
}