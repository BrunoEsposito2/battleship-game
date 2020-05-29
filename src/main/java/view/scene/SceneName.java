package view.scene;

/**
 * This enum models the viewable Scenes of the app.
 */
public enum SceneName {
    // New scenes can be added here
    // Scene layoutName should be the fxml layout file's name, excluding the extension (ex. mainMenu.fxml -> mainMenu)
    MAIN("mainMenu"),
    MATCH_SETTINGS("matchSettings"),
    //SETTINGS ("settings");
    PROFILE("profile");

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
