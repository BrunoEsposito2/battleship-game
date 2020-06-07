package controller.ui.matchsettings;

import java.util.Optional;
import controller.users.AccountManager;
import view.dialog.DialogLauncher;
import view.dialog.DialogType;

//package private
final class Login {

    private final AccountManager accountManager;

    protected Login(final AccountManager accountManager) {
        this.accountManager = accountManager;
    }

  //package private
    boolean isPlayerSelectionValid(final Optional<String> username1, final Optional<String> username2, final boolean aiPlayer) {
        if (!username1.isPresent() || (!username2.isPresent() && !aiPlayer)) {
            DialogLauncher.launch(DialogType.ERROR, "Error!", "Some players have no profile selected!\nChange your selection and try again.", null);
        } else if (username1.equals(username2) && !aiPlayer) {
            DialogLauncher.launch(DialogType.ERROR, "Error!", "Player1 and Player2 cannot be the same!\nChange your selection and try again.", null);
        } else {
            return true;
        }
        return false;
    }

  //package private
    boolean areCredentialsValid(final String username) {
        final Optional<String> password = DialogLauncher.launch(DialogType.LOGIN, "Login", "Insert password for user \"" + username + "\"", null);
        if (password.isPresent() && accountManager.logInAccount(username, password.get())) {
            DialogLauncher.launch(DialogType.INFORMATION, "Login", "Login successful!", null);
            return true;
        } else {
            DialogLauncher.launch(DialogType.ERROR, "Login", "Invalid account credentials!", null);
            return false;
        }
    }

  //package private
    void noProfilesAvailable() {
        DialogLauncher.launch(DialogType.WARNING, "Warning: No Profiles Available", "You need to create at least a profile in order to play.\n"
                + "From the Main Menu, click \"Profile\" to create a new account.", null);
    }
}
