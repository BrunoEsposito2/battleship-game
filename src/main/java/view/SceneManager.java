package view;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.enums.SceneName;

/**
 * This enum is used to switch Scenes on the active Stage.
 * It is used as a singleton by calling SceneManager.INSTANCE.methodName().
 */
public enum SceneManager {

    INSTANCE; // The usable instance of this class.
    private final Stage currentStage = Main.getStage();;

    /**
     * This method switches the active Scene to the one passed as parameter.
     * @param nextScene - the Scene to switch to.
     */
    public void switchScene(final SceneName nextScene) {
        try {
            currentStage.setScene(new Scene(FXMLLoader.load(ClassLoader.getSystemResource("layouts/" + nextScene.getLayoutName() + ".fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Critical error reading layouts from disk.\nApp closing. . .\n");
            System.exit(1);
        }
    }
}
