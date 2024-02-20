package ca.mcmaster.se2aa4.island.team210;

import org.json.JSONObject;



public class DirectionInfo {
    private Integer range;
    private String echo_type;
    DirectionInfo(){
        range = 0;
        echo_type = "";
    }

    public void setInfo(JSONObject info){
        range = info.getInt("range");
        echo_type = info.getString("found");
    }
    public Integer getRange(){
        return range;
    }
    public String getEcho_type(){
        return echo_type;
    }
}
