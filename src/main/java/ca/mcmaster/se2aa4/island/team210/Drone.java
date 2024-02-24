package ca.mcmaster.se2aa4.island.team210;

public class Drone {
    int battery;


    enum direction {
        N,
        S,
        E,
        W
    }
    Integer x=0;
    Integer y=0;
    direction current_direction;
    public Drone(Integer batteryLevel, String initial_direction){
        this.battery = batteryLevel;
        switch (initial_direction){
            case "E":
                current_direction = direction.E;
                break;
            case "N":
                current_direction = direction.N;
                break;
            case "S":
                current_direction = direction.S;
                break;
            case "W":
                current_direction = direction.W;
                break;
        }
    }


    public void removeCost(Integer cost) {
        battery -= cost;
    }
    public void move() {
        switch (current_direction){
            case N:
                y++;
                break;
            case E:
                x++;
                break;
            case W:
                x--;
                break;
            case S:
                y--;
                break;
        }
    }
    public void handleDirection(String newDir){
        switch (newDir){
            case "N":
                current_direction = direction.N;
                break;
            case "S":
                current_direction = direction.S;
                break;
            case "E":
                current_direction = direction.E;
                break;
            case "W":
                current_direction = direction.W;
                break;
        }
    }
    public int getBattery(){
        return battery;
    }
}
