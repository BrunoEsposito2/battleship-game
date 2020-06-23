package view.scene;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import application.Battleships;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import view.dialog.DialogType;

//package private
final class LayoutLoaderFXML implements LayoutLoader {

    @Override
    public Parent load(final String resourcePath) {
        Optional<Parent> res = Optional.empty();
        try {
            res = Optional.ofNullable(FXMLLoader.load(ClassLoader.getSystemResource(resourcePath)));
        } catch (Exception e) {
            Battleships.getController().launchDialog(DialogType.ERROR, "An Exception has occurred",
                    "Application encountered a critical error while reading files from disk", getStringFromStackTrace(e));
        }
        return res.orElseThrow(() -> new IllegalStateException("could not load resource"));
    }

    private String getStringFromStackTrace(final Exception e) {
        final StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
