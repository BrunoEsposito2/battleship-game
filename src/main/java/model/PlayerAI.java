package model;

public class PlayerAI implements Player {
    
    private final Boolean isHuman;
    private String name;
    
    public PlayerAI(String name) {
        this.name = name;
        isHuman = false;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Boolean isHuman() {
        return isHuman;
    }
    
}
