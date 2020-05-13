package view;

import java.io.File;
import application.Main;
import javafx.scene.Scene;
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
    private final ResourceLoaderImpl loader = new ResourceLoaderImpl();

    /**
     * This method switches the active Scene to the one passed as parameter.
     * @param nextScene - the name of the Scene you want to load.
     */
    public void switchScene(final SceneName nextScene) {
        final String directory = "layouts";
        final String separator = File.separator;
        final String fileName = nextScene.getLayoutName();
        final String fileExtension = ".fxml";
        currentStage.setScene(new Scene(loader.loadFXML(directory + separator + fileName + fileExtension)));
    }

}
