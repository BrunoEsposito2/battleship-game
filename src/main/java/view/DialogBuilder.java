package view;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

/**
 *  This class is used to create and launch new alerts.
 */
public final class DialogBuilder {

    public enum DialogType {

        CONFIRMATION(AlertType.CONFIRMATION),
        INFORMATION(AlertType.INFORMATION),
        WARNING(AlertType.WARNING),
        ERROR(AlertType.ERROR);

        private final AlertType concreteType;

        DialogType(final AlertType concreteType) {
            this.concreteType = concreteType;
        }
    }

    private DialogBuilder() { };

    /**
     * This method returns a new alert from passed parameters.
     * @param type - type of the alert
     * @param title - title of the alert
     * @param header - header of the alert
     * @param description - description of the alert
     * @return the created alert
     */
    public static Alert build(final DialogType type, final String title, final String header, final String description) {
        Alert alert = new Alert(type.concreteType);
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

    /**
     * This method launches a new alert from passed parameters.
     * @param type - type of the alert
     * @param title - title of the alert
     * @param header - header of the alert
     * @param description - description of the alert
     */
    public static void buildAndLaunch(final DialogType type, final String title, final String header, final String description) {
        build(type, title, header, description).showAndWait();
    }

}
