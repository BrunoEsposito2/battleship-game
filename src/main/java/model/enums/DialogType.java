package model.enums;

import javafx.scene.control.Alert.AlertType;

/**
 * This enum represents the Dialog types that DialogBuilder can use. 
 */
public enum DialogType {

    /**
     * Ask confirmation from user.
     */
    CONFIRMATION(AlertType.CONFIRMATION),
    /**
     * Give the user information. 
     */
    INFORMATION(AlertType.INFORMATION),
    /**
     * Warn the user about a non-critical issue.
     */
    WARNING(AlertType.WARNING),
    /**
     * Warn the user about a critical issue.
     */
    ERROR(AlertType.ERROR),
    /**
     * Ask the user for a password.
     */
    LOGIN(AlertType.NONE);

    private final AlertType concreteType;

    DialogType(final AlertType concreteType) {
        this.concreteType = concreteType;
    }

    /**
     * @return the concrete type of this DialogType
     */
    public AlertType getConcreteType() {
        return concreteType;
    }
}
