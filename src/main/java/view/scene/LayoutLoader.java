package view.scene;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import view.dialog.DialogLauncher;
import view.dialog.DialogType;

//package private
final class LayoutLoader {

    Parent loadFXML(final String resourcePath) {
        Parent res = null;
        try {
            res = FXMLLoader.load(ClassLoader.getSystemResource(resourcePath));
        } catch (Exception e) {
            DialogLauncher.launch(DialogType.ERROR, "An Exception has occurred",
                    "Application encountered a critical error while reading files from disk", getStringFromStackTrace(e));
        }
        if (res == null) {
            throw new IllegalStateException("Failed to laod resource");
        }
        return res; 
    }

    private String getStringFromStackTrace(final Exception e) {
        final StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
