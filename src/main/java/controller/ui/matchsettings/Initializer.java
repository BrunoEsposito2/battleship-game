package controller.ui.matchsettings;

import java.util.Arrays;
import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.ChoiceBox;
import model.enums.GameMode;

//package private
final class Initializer {

    private final MatchSettings ms;
    private final Login login;
    //private final Collection<String> usernames = accountManager.getAllUsername().orElse(new ArrayList<String>());
    //TODO switch to proper init
    private final Collection<String> usernames = Arrays.asList("voglio andare", "al mare");

    Initializer(final MatchSettings ms, final Login login) {
        this.ms = ms;
        this.login = login;
    }

    void initChoiceBoxes() {
        initChoiceBox(ms.getChoiceBoxPlayer1(), usernames, getChoiceBoxPlayerListener(ms.getChoiceBoxPlayer1()));
        initChoiceBox(ms.getChoiceBoxPlayer2(), usernames, getChoiceBoxPlayerListener(ms.getChoiceBoxPlayer2()));
        initChoiceBox(ms.getChoiceBoxGameMode(), Arrays.asList(GameMode.values()), getChoiceBoxGameModeListener());
        ms.getChoiceBoxGameMode().getSelectionModel().selectFirst();
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
            if (ms.getSelectedItem(cb) != null) {
                if (!login.checkCredentials((String) ms.getSelectedItem(cb))) {
                    cb.getSelectionModel().clearSelection();
                }
            }
        };
    }

    private <T> ChangeListener<T> getChoiceBoxGameModeListener() {
        return (x, y, z) -> {
            ms.setSelectedGameMode(ms.getSelectedItem(ms.getChoiceBoxGameMode()));
            ms.updateGameModeText(ms.getSelectedGameMode().getDescription());
        };
    }

}
