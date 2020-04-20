package model;

/**
 * 
 * This enum models the viewable Scenes of the app.
 *
 */
public enum SceneName {
    MAIN ("mainMenu"),
    MATCH_SETTINGS ("matchSettings");
    //SETTINGS ("settings"),
    //PROFILE ("profile"),
    
    private final String layoutName;
    
    private SceneName(String layoutName) {
        this.layoutName = layoutName;
    }
    
    /**
     * @return The layout's name
     */
    public String getLayoutName() {
        return layoutName;
    }
}
