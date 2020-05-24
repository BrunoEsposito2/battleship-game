package controller.ui.matchsettings;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import controller.users.AccountManager;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ChoiceBox;
import model.enums.DialogType;
import model.enums.GameMode;
import view.dialog.DialogBuilder;

//package private
final class Initializer {

    private final DialogBuilder dialog;
    private final MatchSettings ms;
    private final Login login;
    private final Collection<String> usernames;
    //TODO switch to proper init
    //private final Collection<String> usernames = Arrays.asList("voglio andare", "al mare");

    private enum ChoiceBoxType {
        PLAYER, GAMEMODE;
    }

    protected Initializer(final MatchSettings ms, final Login login, final AccountManager accountManager, final DialogBuilder dialog) {
        this.dialog = dialog;
        this.ms = ms;
        this.login = login;
        usernames = accountManager.getAllUsername().orElse(noProfilesAvailable());
    }

  //package private
    void initChoiceBoxes(final ChoiceBox<String> p1, final ChoiceBox<String> p2, final ChoiceBox<GameMode> gm) {
        initChoiceBox(p1, usernames, getChoiceBoxListener(p1, ChoiceBoxType.PLAYER));
        initChoiceBox(p2, usernames, getChoiceBoxListener(p2, ChoiceBoxType.PLAYER));
        initChoiceBox(gm, Arrays.asList(GameMode.values()), getChoiceBoxListener(gm, ChoiceBoxType.GAMEMODE));
        gm.getSelectionModel().selectFirst();
        ms.setGameModeDescription(ms.getSelectedItem(gm).getDescription());
    }

    private <T> void initChoiceBox(final ChoiceBox<T> cb, final Collection<T> c, final ChangeListener<T> cl) {
        c.forEach(x -> cb.getItems().add(x));
        cb.setStyle("-fx-font: 18px \"Serif\";");
        cb.getSelectionModel().selectedItemProperty().addListener(cl);
    }

    private <T> ChangeListener<T> getChoiceBoxListener(final ChoiceBox<T> cb, final ChoiceBoxType type) {
        return type.equals(ChoiceBoxType.PLAYER)
                ? (x, y, z) -> {
                    if (ms.getSelectedItem(cb) != null && !login.checkCredentials((String) ms.getSelectedItem(cb))) {
                        cb.getSelectionModel().clearSelection();
                    }
                }
                : (x, y, z) -> {
                    ms.setGameModeDescription(((GameMode) ms.getSelectedItem(cb)).getDescription());
                };
    }

    private List<String> noProfilesAvailable() {
        dialog.launch(DialogType.WARNING, "Warning: No Profiles Available", "You must register at least a profile to start a match.\n"
                + "Go back to the main menu, then click Profile to manage your profiles.", null);
        return Collections.emptyList();
    }

}
