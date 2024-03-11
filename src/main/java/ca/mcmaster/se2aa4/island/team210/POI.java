package ca.mcmaster.se2aa4.island.team210;

import java.util.List;

public class POI {
    private final Integer xCoord;
    private final Integer yCoord;

    POI(Integer[] currentCoords){
        this.xCoord = currentCoords[0];
        this.yCoord = currentCoords[1];
    }
}
