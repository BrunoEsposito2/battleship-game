package controller.ui.matchsettings;

import javafx.scene.control.TextArea;
import java.util.Optional;
import application.Battleships;
import controller.users.AccountManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import model.enums.GameMode;
import model.enums.PlayerType;
import model.match.MatchInitializer;
import view.scene.SceneName;

/**
 *  The Controller related to the matchSettings.fxml GUI.
 */
public final class MatchSettings {

    private final AccountManager accountManager = Battleships.getController().getAccountManager(); //new AccountOperation();
    private final Login login = new Login(accountManager);

    @FXML
    private Button buttonBack, buttonStart;
    @FXML
    private ChoiceBox<String> choiceboxPlayer1, choiceboxPlayer2;
    @FXML
    private ChoiceBox<GameMode> choiceboxGameMode;
    @FXML
    private CheckBox checkboxAI;
    @FXML
    private TextArea textareaDescription;

    /**
     * this method is called automatically when loading the fxml layout. It sets the initial state of the UI.
     */
    public void initialize() {
        new Initializer(this, login, accountManager).initChoiceBoxes(choiceboxPlayer1, choiceboxPlayer2, choiceboxGameMode);
        if (choiceboxPlayer1.getItems().isEmpty()) {
            login.noProfilesAvailable();
        }
    }

    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void buttonStart() {
        final Optional<String> username1 = Optional.ofNullable(getSelectedItem(choiceboxPlayer1));
        final Optional<String> username2 = checkboxAI.isSelected() ? Optional.empty() : Optional.ofNullable(getSelectedItem(choiceboxPlayer2));
        if (login.isPlayerSelectionValid(username1, username2, checkboxAI.isSelected())) {
            new MatchInitializer(username1.get(), username2, checkboxAI.isSelected()
                    ? PlayerType.ARTIFICIAL : PlayerType.HUMAN, getSelectedItem(choiceboxGameMode)).startNewMatch();
        }
    }

    /**
     *  The handler for the click events generated by the checkbox.
     */
    @FXML
    public void checkboxAI() {
        choiceboxPlayer2.setDisable(!choiceboxPlayer2.isDisabled());
    }

    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void buttonBack() {
        Battleships.getController().changeScene(SceneName.MAIN);
    }

    // package-private
    void setGameModeDescription(final String text) {
        textareaDescription.setText(text);
    }

    // package-private
    <T> T getSelectedItem(final ChoiceBox<T> cb) {
        return cb.getSelectionModel().getSelectedItem();
    }

}
