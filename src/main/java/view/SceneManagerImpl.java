package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.SceneName;
/**
 * 
 * This class manages transitions between scenes.
 * Prior to first use it must be initialized by calling its init() method.
 * Used as a Singleton by calling the getInstance() method
 *
 */
public final class SceneManagerImpl implements SceneManager {

    private static final SceneManagerImpl vm = new SceneManagerImpl();
    private Stage currentStage;

    private SceneManagerImpl() { };

    /**
     * This method returns the usable instance of the singleton.
     * @return the ViewManager instance.
     */
    public static SceneManagerImpl getInstance() {
        return vm;
    }


    /**
     * This method switches the active Scene to the one passed as nextScene.
     * @param nextScene the Scene to switch to.
     * 
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
     * 
     */
    public void init(final Stage stage) {
        if (stage == null || currentStage != null) {
            throw new IllegalStateException();
        }
        currentStage = stage;
    }
}
