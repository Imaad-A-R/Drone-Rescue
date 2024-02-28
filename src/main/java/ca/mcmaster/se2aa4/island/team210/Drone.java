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
    direction right;
    direction left;
    public Drone(Integer batteryLevel, String initial_direction){
        this.battery = batteryLevel;
        handleDirection(initial_direction);
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
            case "E":
                current_direction = direction.E;
                right = direction.S;
                left = direction.N;
                break;
            case "N":
                current_direction = direction.N;
                right = direction.E;
                left = direction.W;
                break;
            case "S":
                current_direction = direction.S;
                right = direction.W;
                left = direction.E;
                break;
            case "W":
                current_direction = direction.W;
                right = direction.N;
                left = direction.S;
                break;
        }
    }
    public boolean canDoAction(Decision d, String direction){
        return true;
    }
    public String getDirection() { return current_direction.toString(); }
    public int getBattery(){
        return battery;
    }
}
