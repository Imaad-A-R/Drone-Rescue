package ca.mcmaster.se2aa4.island.team210;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class MapAnalyzerTest {
    @Test
    public void testFindClosestCreek(){
        ScanInfo scan = new ScanInfo();
        String s = "{\"cost\": 5, \"extras\": {\"creeks\": [\"creek1\"], \"biomes\": [\"SHRUBLAND\"], \"sites\": []}, \"status\": \"OK\"}";
        String s2 = "{\"cost\": 5, \"extras\": {\"creeks\": [\"creek2\"], \"biomes\": [\"SHRUBLAND\"], \"sites\": []}, \"status\": \"OK\"}";
        String s3 = "{\"cost\": 5, \"extras\": {\"creeks\": [\"creek3\"], \"biomes\": [\"SHRUBLAND\"], \"sites\": []}, \"status\": \"OK\"}";
        String s4 = "{\"cost\": 5, \"extras\": {\"creeks\": [], \"biomes\": [\"SHRUBLAND\"], \"sites\": [\"emergency\"]}, \"status\": \"OK\"}";

        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        JSONObject extraInfo = response.getJSONObject("extras");
        Integer[] myArray = new Integer[]{1,2};
        scan.interpretResults(myArray, extraInfo);

        JSONObject response1 = new JSONObject(new JSONTokener(new StringReader(s2)));
        JSONObject extraInfo1 = response1.getJSONObject("extras");
        Integer[] myArray1 = new Integer[]{2,3};
        scan.interpretResults(myArray1, extraInfo1);

        JSONObject response2 = new JSONObject(new JSONTokener(new StringReader(s3)));
        JSONObject extraInfo2 = response2.getJSONObject("extras");
        Integer[] myArray2 = new Integer[]{3,4};
        scan.interpretResults(myArray2, extraInfo2);

        JSONObject response3 = new JSONObject(new JSONTokener(new StringReader(s4)));
        JSONObject extraInfo3 = response3.getJSONObject("extras");
        Integer[] myArray3 = new Integer[]{5,10};
        scan.interpretResults(myArray3, extraInfo3);

        MapAnalyzer mapAnalyzer = new MapAnalyzer(scan);
        String closestCreek = mapAnalyzer.findClosestCreek();

        assertEquals("creek3", closestCreek);
    }
}