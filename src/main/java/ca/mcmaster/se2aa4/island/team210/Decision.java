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

    public void setAction(String givenAction) {
        action = givenAction;
    }

    public void setExtra(String givenExtra){
        extra = givenExtra;
    }

    public String getAction(){
        return action;
    }

    public String getExtra(){
        return extra;
    }
}
