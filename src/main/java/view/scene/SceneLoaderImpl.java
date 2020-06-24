package view.scene;

import java.io.File;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is used to switch Scenes on the active Stage.
 */
public final class SceneLoaderImpl implements SceneLoader {

    private final LayoutLoader layoutLoader = new LayoutLoaderFXML();

    /**
     * This method switches the active Scene to the one passed as parameter.
     * @param nextScene - the name of the Scene you want to load.
     * @param stage - the Stage of the application.
     */
    public void switchScene(final Stage stage, final SceneName nextScene) {
        final String directory = "layouts";
        final String separator = File.separator;
        final String fileName = nextScene.getLayoutName();
        final String fileExtension = ".fxml";
        stage.setScene(new Scene(layoutLoader.load(directory + separator + fileName + fileExtension)));
    }

}