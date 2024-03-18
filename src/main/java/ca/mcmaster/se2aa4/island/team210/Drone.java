package ca.mcmaster.se2aa4.island.team210;

import java.util.ArrayList;
import java.util.List;

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
    direction behind;
    String starting_turn;
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
            default:
                break;
        }
    }
    public void handleDirection(String newDir){
        if(current_direction!=null) {
            move();
        }
        switch (newDir){
            case "E":
                current_direction = direction.E;
                right = direction.S;
                left = direction.N;
                behind = direction.W;
                break;
            case "N":
                current_direction = direction.N;
                right = direction.E;
                left = direction.W;
                behind = direction.S;
                break;
            case "S":
                current_direction = direction.S;
                right = direction.W;
                left = direction.E;
                behind = direction.N;
                break;
            case "W":
                current_direction = direction.W;
                right = direction.N;
                left = direction.S;
                behind = direction.E;
                break;
        }
        move();
    }
    public String getDirection() { return current_direction.toString(); }
    public Integer[] getCoordinates() {
        Integer [] Coords = new Integer[2];
        Coords[0]=x;
        Coords[1]=y;
        return Coords;
    }
    public int getBattery(){
        return battery;
    }
    public void setStartingTurn(String dir){
        starting_turn = dir;
    }
}
