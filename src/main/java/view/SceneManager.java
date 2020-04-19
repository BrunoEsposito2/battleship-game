package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.SceneName;

public enum SceneManager {
    
    INSTANCE;
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
            System.out.println("Error reading layouts. Exiting\n");
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
            throw new IllegalStateException("init method has already been called. Only one call is allowed");
        }
        currentStage = stage;
    }
}
