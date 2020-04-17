package model;

public class PlayerHuman implements Player {
    
    private final Boolean isHuman;
    private String name;
    
    public PlayerHuman(String name) {
        this.name = name;
        isHuman = true;
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
