package model;

import java.util.NoSuchElementException;

public enum WinCondition {
    ALL_ENEMY_SHIPS_SUNK("Classic","Sink all enemy ships to win"),
    HIGHEST_SCORE_AFTER_N_TURNS("Highest score","Have the highest score at the end of the match");
    
    private final String description;
    private final String name;
    
    private WinCondition(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static WinCondition getWinConditionFromName(String name) {
        for(WinCondition wc : WinCondition.values()) {
            if(wc.name.equals(name)) {
                return wc;
            }
        }
        throw new NoSuchElementException();
    }
}
