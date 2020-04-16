package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ViewManager;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 400;

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/mainMenu.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        // Stage configuration
        stage.setTitle("Battleships");
        stage.setScene(scene);
        stage.show();
        ViewManager.getInstance().init(stage);
    }
    
    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();  
    }
    
}
