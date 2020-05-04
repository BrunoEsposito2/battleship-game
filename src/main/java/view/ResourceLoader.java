package view;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import model.enums.DialogType;

public abstract class ResourceLoader {

    public static Object load(final String resource) {
        Node res = null;
        try {
            res = FXMLLoader.load(ClassLoader.getSystemResource(resource));
        } catch (Exception e) {
            new DialogBuilder().buildAndLaunch(DialogType.ERROR, "An Exception has occurred",
                    "Application encountered a critical error while reading files from disk", getStringFromStackTrace(e));
        }
        return res;
    }

    private static String getStringFromStackTrace(final Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
