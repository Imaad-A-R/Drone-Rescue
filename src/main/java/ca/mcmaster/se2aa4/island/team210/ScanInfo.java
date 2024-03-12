package ca.mcmaster.se2aa4.island.team210;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScanInfo {

    private static List<POI> creekList = new ArrayList<>();
    private static POI emergencySite;

    public void interpretResults(Integer[] currentCoords, JSONObject extraInfo) {
        if(extraInfo.has("biomes")){
            checkCreeks(extraInfo.getJSONArray("creeks"), currentCoords);
            setSite(extraInfo.getJSONArray("sites"), currentCoords);
        }
    }

    private void setSite(JSONArray sites, Integer[] currentCoords) {
        if (!sites.isEmpty()){
            emergencySite = new POI(currentCoords, sites.getString(0));
        }
    }

    private void checkCreeks(JSONArray creeks, Integer[] currentCoords) {
        if (!creeks.isEmpty()){
            POI newCreek = new POI(currentCoords, creeks.getString(0));
            creekList.add(newCreek);
        }
    }
}
