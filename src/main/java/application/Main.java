package application;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelImpl;
import view.View;
import view.ViewImpl;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    private static Stage stage; 

    @Override
    public void start(final Stage stage) throws Exception {
        final View view = new ViewImpl(stage);
        final Model model = new ModelImpl();
        final Controller controller = new ControllerImpl(model, view);
        // Stage configuration
        Main.stage = stage;
        view.launch(controller);
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
