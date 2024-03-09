package ca.mcmaster.se2aa4.island.team210;

import org.json.JSONObject;

public class ScanInfo {

    private String current_space;

    public static void interpretResults(JSONObject extraInfo) {
        if(extraInfo.has("biomes")){
            setCurrentSpace(extraInfo.getString("biomes"));
            checkCreeks(extraInfo.getString("creeks"));
        }
    }

    private static void checkCreeks(String creeks) {
    }

    private static void setCurrentSpace(String biomes) {
    }
}
