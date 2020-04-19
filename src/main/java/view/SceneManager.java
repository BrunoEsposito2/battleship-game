package view;

import javafx.stage.Stage;
import model.SceneName;

public interface SceneManager {
    public void switchScene(final SceneName nextScene);
    public void init(final Stage stage);
}
