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

    Initializer(final MatchSettings ms, final Login login, final AccountManager accountManager, final DialogBuilder dialog) {
        this.dialog = dialog;
        this.ms = ms;
        this.login = login;
        usernames = accountManager.getAllUsername().orElse(noProfilesAvailable());
    }

    void initChoiceBoxes(final ChoiceBox<String> p1, final ChoiceBox<String> p2, final ChoiceBox<GameMode> gm) {
        initChoiceBox(p1, usernames, getChoiceBoxPlayerListener(p1));
        initChoiceBox(p2, usernames, getChoiceBoxPlayerListener(p2));
        initChoiceBox(gm, Arrays.asList(GameMode.values()), getChoiceBoxGameModeListener(gm));
        gm.getSelectionModel().selectFirst();
        ms.updateGameModeText(ms.getSelectedGameMode().getDescription());
    }

    private <T> ChoiceBox<T> initChoiceBox(final ChoiceBox<T> cb, final Collection<T> c, final ChangeListener<T> cl) {
        c.forEach(x -> cb.getItems().add(x));
        cb.setStyle("-fx-font: 18px \"Serif\";");
        cb.getSelectionModel().selectedItemProperty().addListener(cl);
        return cb;
    }

    private <T> ChangeListener<T> getChoiceBoxPlayerListener(final ChoiceBox<T> cb) {
        return (x, y, z) -> {
            if (ms.getSelectedItem(cb) != null && !login.checkCredentials((String) ms.getSelectedItem(cb))) {
                cb.getSelectionModel().clearSelection();
            }
        };
    }

    private <T> ChangeListener<T> getChoiceBoxGameModeListener(final ChoiceBox<GameMode> cb) {
        return (x, y, z) -> {
            ms.setSelectedGameMode(ms.getSelectedItem(cb));
            ms.updateGameModeText(ms.getSelectedGameMode().getDescription());
        };
    }

    private List<String> noProfilesAvailable() {
        dialog.launch(DialogType.WARNING, "Warning: No Profiles Available", "You must register at least a profile to start a match.\nGo back to the main menu, then click Profile to do so", null);
        return Collections.emptyList();
    }

}
