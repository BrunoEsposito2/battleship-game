package match_settings;

import javafx.scene.control.TextArea;
import java.util.Set;
import controllers.MatchManager;
import controllers.MatchManagerImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import model.Player;
import model.PlayerAI;
import model.ProfileLoader;
import model.SceneName;
import model.WinCondition;
import view.SceneManager;


/**
 *  The Controller related to the gameModeSelection.fxml GUI.
 *
 */
public final class UIControllerMatchSettings {
    
    Set<Player> profiles = new ProfileLoader().load();
    
    @FXML
    private Button btBtmm;
    @FXML
    private Button btStrt;
    @FXML
    private ChoiceBox<String> choicebPlayer1;
    @FXML
    private ChoiceBox<String> choicebPlayer2;
    @FXML
    private ChoiceBox<String> choicebGamemode;
    @FXML
    private CheckBox checkbAI;
    @FXML
    private TextArea textareaGameModeDescription;
    
    public void initialize() {
        for(Player p : profiles) {
            choicebPlayer1.getItems().add(p.getName());
            choicebPlayer2.getItems().add(p.getName());
        }
        for(WinCondition wc : WinCondition.values()) {
            choicebGamemode.getItems().add(wc.getName());
        }
        choicebPlayer1.getSelectionModel().selectFirst();
        choicebPlayer2.getSelectionModel().selectFirst();
        choicebGamemode.getSelectionModel().selectFirst();
        choicebGamemode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateTextareaDescription(WinCondition.getWinConditionFromName(newValue).getDescription());
            }           
        });
        this.updateTextareaDescription(WinCondition.ALL_ENEMY_SHIPS_SUNK.getDescription());
    }

    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void btBtmmOnClickHandler() {
        System.out.println("\"Back To Main Menu\" button clicked\n ");
        SceneManager.INSTANCE.switchScene(SceneName.MAIN);
    }

    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void btStrtOnClickHandler() {
        System.out.println("\"Start Game\" button clicked\n ");
        // TODO have this handler call a function to deal with gamemanager rather than doing it itself
        Player p1,p2;
        p1 = profiles.stream()
            .filter(x -> x.getName().equals(choicebPlayer1.getSelectionModel().getSelectedItem()))
            .reduce((a, b) -> {
                throw new IllegalStateException("Multiple profiles with same name: " + a + ", " + b);
            })
            .get();
        if(checkbAI.isSelected()) {
            p2 = new PlayerAI("AI");
        } else {
            p2 = profiles.stream()
                .filter(x -> x.getName().equals(choicebPlayer2.getSelectionModel().getSelectedItem()))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple profiles with same name: " + a + ", " + b);
                })
                .get();
        }
        final MatchManager gm = new MatchManagerImpl(p1,p2,WinCondition.getWinConditionFromName(choicebGamemode.getSelectionModel().getSelectedItem()));
        //gm.start();
    }
    
    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void checkbAIonClickHandler() {
        System.out.println("checkbox clicked\n ");
        choicebPlayer2.setDisable(!choicebPlayer2.isDisabled());
    }
    
    private void updateTextareaDescription(String text) {
        this.textareaGameModeDescription.setText(text);
    }

}
