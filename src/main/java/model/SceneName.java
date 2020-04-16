package model;

/**
 * 
 * This enum models the viewable Scenes of the app.
 *
 */
public enum SceneName {
    MAIN ("mainMenu"),
    GAMEMODE_SELECTION ("matchSettings");
    //SETTINGS ("settings"),
    //PROFILE ("profile"),
    
    private final String layoutName;
    
    private SceneName(String layoutName) {
        this.layoutName = layoutName;
    }
    
    public String getLayoutName() {
        return layoutName;
    }
}
