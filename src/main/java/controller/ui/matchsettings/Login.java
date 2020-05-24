package controller.ui.matchsettings;

import java.util.Optional;
import controller.users.AccountManager;
import model.enums.DialogType;
import view.dialog.DialogBuilder;

//package private
final class Login {

    private final AccountManager accountManager;
    private final DialogBuilder dialog;

    Login(final DialogBuilder dialog, final AccountManager accountManager) {
        this.dialog = dialog;
        this.accountManager = accountManager;
    }

    boolean arePlayersValid(final Optional<String> username1, final Optional<String> username2, final boolean aiPlayer) {
        if (!username1.isPresent() || !username2.isPresent() && !aiPlayer) {
            dialog.launch(DialogType.ERROR, "Error!", "Some players have no profile selected!\nChange your selection and try again.", null);
        } else if (username1.equals(username2)) {
            dialog.launch(DialogType.ERROR, "Error!", "Player1 and Player2 cannot be the same!\nChange your selection and try again.", null);
        } else {
            return true;
        }
        return false;
    }

    boolean checkCredentials(final String username) {
        final Optional<String> password = dialog.launch(DialogType.LOGIN, "Login", "Insert password for user \"" + username + "\"", null);
        final boolean isLoginValid = (password.isPresent()) ?  accountManager.logInAccount(username, password.get()) : false;
        if (isLoginValid) {
            dialog.launch(DialogType.INFORMATION, "Login", "Login successful!", null);
            return true;
        } else {
            dialog.launch(DialogType.ERROR, "Login", "Invalid account credentials!", null);
            return false;
        }
    }
}
