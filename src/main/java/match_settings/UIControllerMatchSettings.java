package match_settings;

//import controllers.GameManager;
//import controllers.GameManagerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import model.SceneName;
import view.ViewManager;


/**
 *  The Controller related to the gameModeSelection.fxml GUI.
 *
 */

public final class UIControllerMatchSettings {
    
    @FXML
    private Button btBtmm;
    @FXML
    private Button btStrt;
    @FXML
    private ChoiceBox<String> choicebPlayer1;
    @FXML
    private ChoiceBox<String> choicebPlayer2;
    @FXML
    private CheckBox checkbAI;

    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void btBtmmOnClickHandler() {
        System.out.println("\"Back To Main Menu\" button clicked\n ");
        ViewManager.getInstance().switchScene(SceneName.MAIN);
    }

    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void btStrtOnClickHandler() {
        System.out.println("\"Start Game\" button clicked\n ");
        // TODO have this handler call a function to deal with gamemanager rather than doing it itself
        //final GameManager gm = new GameManagerImpl(null);
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

}
