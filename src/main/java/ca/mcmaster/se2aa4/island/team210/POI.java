package ca.mcmaster.se2aa4.island.team210;

import java.util.List;

public class POI {
    public Integer xCoord;
    public Integer yCoord;
    public String id;

    POI(Integer[] currentCoords, String id){
        this.xCoord = currentCoords[0];
        this.yCoord = currentCoords[1];
        this.id = id;
    }
}
