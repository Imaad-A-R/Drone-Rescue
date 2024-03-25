package ca.mcmaster.se2aa4.island.team210;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class MapAnalyzer {
    private List<POI> creekList;
    private final Logger logger = LogManager.getLogger();
    private POI emergency;
    double closest = 10000;
    public MapAnalyzer(ScanInfo scanInfo) {
        creekList = scanInfo.getCreekList();
        emergency = scanInfo.getEmergencySite();
    }

    public String findClosestCreek() {
        String closestID="";
        logger.info("emergency coords: "+emergency.xCoord+" "+emergency.yCoord);
        for (POI i: creekList){
            logger.info(i.xCoord+" "+i.yCoord);
            if (distanceForm(i.xCoord, i.yCoord, emergency.xCoord, emergency.yCoord) < closest){
                closestID = i.id;
                closest = distanceForm(i.xCoord, i.yCoord, emergency.xCoord, emergency.yCoord);
            }
        }
        return closestID;
    }

    private double distanceForm(Integer x, Integer y, Integer emergencyX, Integer emergencyY){
        return Math.sqrt(Math.abs((Math.pow((double)x-emergencyX, 2) + Math.pow((double)y-emergencyY, 2))));
    }
}
