package view;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.enums.SceneName;

/**
 * This enum is used to switch Scenes on the active Stage.
 * It is used as a singleton by calling SceneManager.INSTANCE.methodName().
 */
public enum SceneManager {

    /**
     * The usable instance of this class.
     */
    INSTANCE;

    private final Stage currentStage = Main.getStage();

    /**
     * This method switches the active Scene to the one passed as parameter.
     * @param nextScene - the name of the Scene you want to load.
     */
    public void switchScene(final SceneName nextScene) {
        currentStage.setScene(new Scene((Parent) ResourceLoader.load("layouts" + File.separator + nextScene.getLayoutName() + ".fxml")));
    }

}
