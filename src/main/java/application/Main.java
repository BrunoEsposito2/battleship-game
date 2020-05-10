package application;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;
    private static Stage stage; 

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts" + File.separator + "mainMenu.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        // Stage configuration
        stage.setTitle("Battleships");
        stage.setScene(scene);
        stage.show();
        Main.stage = stage;
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

    /**
     * @return the application's active stage
     */
    public static Stage getStage() {
        return stage;
    }

}
