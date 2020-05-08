package view.dialog;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import model.enums.DialogType;

/**
 *  This class is used to create and launch new alerts.
 */
final class SimpleDialog {

    private Alert build(final DialogType type, final String title, final String header, final String description) {
        Alert alert = new Alert(type.getConcreteType());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setResizable(true);

        if (description != null) {
            TextArea area = new TextArea(description);
            area.setWrapText(true);
            area.setEditable(false);
            alert.getDialogPane().setContent(area);
        }

        return alert;
    }

    protected Optional<String> launch(final DialogType type, final String title, final String header, final String description) {
        build(type, title, header, description).showAndWait();
        return Optional.empty();
    }

}
