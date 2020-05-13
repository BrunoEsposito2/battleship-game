package view.dialog;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;

// package-private
final class LoginDialog {

    private Dialog<String> build(final String title, final String header) {

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        PasswordField password = new PasswordField();
        password.setPromptText("your password here...");

        dialog.getDialogPane().setContent(password);
        dialog.getDialogPane().setPadding(new Insets(30, 30, 30, 30));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return password.getText();
            }
            return null;
        });

        return dialog;
    }

    Optional<String> launch(final String title, final String header) {
        return build(title, header).showAndWait();
    }

}
