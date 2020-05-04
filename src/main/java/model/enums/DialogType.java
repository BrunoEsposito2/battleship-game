package model.enums;

import javafx.scene.control.Alert.AlertType;

public enum DialogType {

    CONFIRMATION(AlertType.CONFIRMATION),
    INFORMATION(AlertType.INFORMATION),
    WARNING(AlertType.WARNING),
    ERROR(AlertType.ERROR);

    private final AlertType concreteType;

    DialogType(final AlertType concreteType) {
        this.concreteType = concreteType;
    }

    public AlertType getConcreteType() {
        return concreteType;
    }
}
