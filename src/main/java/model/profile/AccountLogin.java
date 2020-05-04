package model.profile;

import java.util.Optional;

import javafx.util.Pair;
import model.player.Player;
import view.LoginDialogBuilder;

public final class AccountLogin {
    
    private final LoginDialogBuilder dialog = new LoginDialogBuilder();

    public boolean login(final Player p) {
        Optional<Pair<String, String>> credentials = dialog.buildAndLaunch();
        if (credentials.isPresent()) {
          //TODO check if credentials are correct
            return true; 
        }
        return false;
    }
}
