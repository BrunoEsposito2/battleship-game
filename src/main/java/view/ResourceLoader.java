package view;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import model.enums.DialogType;
import view.dialog.DialogBuilder;
import view.dialog.GenericDialog;

public final class ResourceLoader {

    private final DialogBuilder dialog = new GenericDialog();

    public Object load(final String resource) {
        Node res = null;
        try {
            res = FXMLLoader.load(ClassLoader.getSystemResource(resource));
        } catch (Exception e) {
            dialog.launch(DialogType.ERROR, "An Exception has occurred",
                    "Application encountered a critical error while reading files from disk", getStringFromStackTrace(e));
        }
        return res;
    }

    private String getStringFromStackTrace(final Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
