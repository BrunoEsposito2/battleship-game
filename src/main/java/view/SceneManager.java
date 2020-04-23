package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.enums.SceneName;

/**
 * This enum is used to switch Scenes on the active Stage.
 * It is used as a singleton by calling SceneManager.INSTANCE.methodName().
 * Before its first use, this enum must be initialized by calling the init() method.
 * The init method must be called only once.
 */
// NOTE: IN THIS PROJECT THE INIT() METHOD IS CALLED BY THE LAUNCHER AT STARTUP. NO NEED TO EVER CALL IT AGAIN
public enum SceneManager {
    
    INSTANCE; // The usable instance of this class.
    private Stage currentStage;

    /**
     * This method switches the active Scene to the one passed as nextScene.
     * @param nextScene the Scene to switch to.
     */
    public void switchScene(final SceneName nextScene) {
        if (currentStage == null) {
            throw new IllegalStateException();
        }
        try {
            currentStage.setScene(new Scene(FXMLLoader.load(ClassLoader.getSystemResource("layouts/" + nextScene.getLayoutName() + ".fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Critical error reading layouts from disk.\nApp closing. . .\n");
            System.exit(1);
        }
    }

    /**
     * This method must be called once before using ViewManager for the first time.
     * @param stage the main stage, containing the active scene.
     */
    public void init(final Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("param \"stage\" is not a valid Stage");
        }
        if (currentStage != null) {
            throw new IllegalStateException("SceneManager is already initialized");
        }
        currentStage = stage;
    }
}
