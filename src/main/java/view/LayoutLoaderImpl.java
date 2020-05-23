package view;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.enums.DialogType;
import view.dialog.DialogBuilder;
import view.dialog.DialogBuilderimpl;

/**
 * Concrete implementation of ResourceLoader.
 */
public final class LayoutLoaderImpl implements LayoutLoader {

    private final DialogBuilder dialog = new DialogBuilderimpl();

    /**
     * {@inheritDoc}
     */
    public Parent loadFXML(final String resourcePath) {
        Parent res = null;
        try {
            res = FXMLLoader.load(ClassLoader.getSystemResource(resourcePath));
        } catch (Exception e) {
            dialog.launch(DialogType.ERROR, "An Exception has occurred",
                    "Application encountered a critical error while reading files from disk", getStringFromStackTrace(e));
        }
        if (res == null) {
            throw new IllegalStateException("Failed to laod resource");
        }
        return res; 
    }

    private String getStringFromStackTrace(final Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
