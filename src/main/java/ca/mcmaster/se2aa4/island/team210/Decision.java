package ca.mcmaster.se2aa4.island.team210;

public class Decision {

    private String action;
    private String extra;

    public Decision(){
        action = "";
        extra = "";
    }

    public Decision(String givenCommand){
        action = givenCommand;
        extra = "";
    }

    public Decision(String givenCommand, String extraInfo){
        action = givenCommand;
        extra = extraInfo;
    }

    public String getAction(){
        return action;
    }

    public String getExtra(){
        return extra;
    }
}
