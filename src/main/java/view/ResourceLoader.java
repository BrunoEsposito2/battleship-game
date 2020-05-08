package view;

import javafx.scene.Parent;

/**
 * This interface provides methods to load resources from disk.
 */
public interface ResourceLoader {

    /**
     * This method is used to load FXML resources (layouts).
     * @param resourcePath - the path to the resource (filename and extension included)
     * @return the loaded resource
     */
    Parent loadFXML(String resourcePath);

}
