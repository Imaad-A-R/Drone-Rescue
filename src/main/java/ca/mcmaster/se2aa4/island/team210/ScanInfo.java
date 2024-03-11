package ca.mcmaster.se2aa4.island.team210;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScanInfo {

    private static List<POI> creekList = new ArrayList<>();
    private static POI emergencySite;

    public void interpretResults(Integer[] currentCoords, JSONObject extraInfo) {
        if(extraInfo.has("biomes")){
            checkCreeks(extraInfo.getJSONArray("creeks").toString(), currentCoords);
            setSite(extraInfo.getJSONArray("sites").toString(), currentCoords);
        }
    }

    private void setSite(String sites, Integer[] currentCoords) {
        if (!sites.isEmpty()){
            emergencySite = new POI(currentCoords);
        }
    }

    private void checkCreeks(String creeks, Integer[] currentCoords) {
        if (!creeks.isEmpty()){
            POI newCreek = new POI(currentCoords);
            creekList.add(newCreek);
        }
    }
}
