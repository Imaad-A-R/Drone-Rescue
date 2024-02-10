package ca.mcmaster.se2aa4.island.team210;

public class Drone {
    int battery;
    public Drone(Integer batteryLevel){
        this.battery = batteryLevel;
    }

    public void removeCost(Integer cost) {
        battery -= cost;
    }

    public int getBattery(){
        return battery;
    }
}
