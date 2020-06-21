package view.scene;

/**
 * This enum models the viewable Scenes of the app.
 */
public enum SceneName {
    // New scenes can be added here
    // Scene layoutName should be the fxml layout file's name, excluding the extension (ex. mainMenu.fxml -> mainMenu)

    /**
     * Main menu.
     */
    MAIN("mainMenu"),

    /**
     * Choose settings before starting a new match.
     */
    MATCH_SETTINGS("matchSettings"),

    /**
     * Create or delete player profiles.
     */
    PROFILE("profile"),

    /**
     * Create or delete player profiles.
     */
    SHIP_DEPLOYMENT("shipDeployment"),

    /**
     * Create or delete player profiles.
     */
    MATCH_BATTLE("matchBattle");

    private final String layoutName;

    SceneName(final String layoutName) {
        this.layoutName = layoutName;
    }

    /**
     * @return The layout's name
     */
    public String getLayoutName() {
        return layoutName;
    }
}
