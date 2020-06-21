package controller.ui;

import application.Battleships;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.scene.SceneName;

/**
 * The Controller related to the mainMenu.fxml GUI.
 *
 */
public final class MainMenu {

    @FXML
    private Button buttonNewGame, buttonSettings, buttonProfile, buttonExit;

    /**
     * The handler for the click events generated by the button.
     */
    @FXML
    public void buttonNewGame() {
        Battleships.getController().changeScene(SceneName.MATCH_SETTINGS);
    }

    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void buttonProfile() {
        Battleships.getController().changeScene(SceneName.PROFILE);
    }

    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void buttonExit() {
        System.exit(0);
    }

}
