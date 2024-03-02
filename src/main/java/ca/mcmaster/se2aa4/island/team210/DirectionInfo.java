package ca.mcmaster.se2aa4.island.team210;

import org.json.JSONObject;



public class DirectionInfo {
    private Integer range;
    private String echoType;
    DirectionInfo(){
        range = 0;
        echoType = "";
    }

    public void setInfo(JSONObject info){
        range = info.getInt("range");
        echoType = info.getString("found");
    }
    public Integer getRange(){
        return range;
    }
    public String getEchoType(){
        return echoType;
    }
}
