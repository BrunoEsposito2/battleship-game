package view;

import java.util.Optional;

import javafx.stage.Stage;
import view.dialog.DialogType;
import view.scene.SceneLoader;
import view.scene.SceneName;

/**
 * Concrete implementation of tha application's view.
 */
public final class ViewImpl implements View {

    private final Stage stage;
    private final SceneLoader sceneLoader;

    /**
     * the constructor of this class.
     * @param stage - the application's stage.
     */
    public ViewImpl(final Stage stage) {
        this.stage = stage;
        sceneLoader = new SceneLoader();
        loadScene(SceneName.MAIN);
        stage.setTitle("Battleships");
        stage.show();
    }

    @Override
    public void loadScene(final SceneName name) {
        sceneLoader.switchScene(stage, name);
    }

    @Override
    public Optional<String> launchDialog(final DialogType type, final String title, final String header, final String description) {
        return type.getConcreteClass().launch(type, title, header, description);
    }

}
