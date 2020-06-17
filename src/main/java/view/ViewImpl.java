package view;

import java.io.File;
import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewImpl implements View {

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

    private Controller control;
    private final Stage stage;

    public ViewImpl(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public final void launch(final Controller controller) throws IOException {
        this.control = controller;
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts" + File.separator + "mainMenu.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Battleships");
        stage.setScene(scene);
        stage.show();
    }

}
