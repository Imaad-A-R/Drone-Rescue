package ca.mcmaster.se2aa4.island.team210;

import java.util.List;

public class POI {
    private final Integer xCoord;
    private final Integer yCoord;
    private final String id;

    POI(Integer[] currentCoords, String id){
        this.xCoord = currentCoords[0];
        this.yCoord = currentCoords[1];
        this.id = id;
    }
}
