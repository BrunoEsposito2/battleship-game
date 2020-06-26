package controller.ui.matchsettings;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import application.Battleships;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ChoiceBox;
import model.gamemode.GameMode;

//package private
final class Initializer {

    private final MatchSettings ms;
    private final Login login;
    private final Collection<String> usernames = Battleships.getController().getAccountManager().getAllUsername().orElse(Collections.emptyList());

    private enum ChoiceBoxType {
        PLAYER, GAMEMODE;
    }

    protected Initializer(final MatchSettings ms, final Login login) {
        this.ms = ms;
        this.login = login;
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
                    if (ms.getSelectedItem(cb) != null && !login.areCredentialsValid((String) ms.getSelectedItem(cb))) {
                        cb.getSelectionModel().clearSelection();
                    }
                }
                : (x, y, z) -> {
                    ms.setGameModeDescription(((GameMode) ms.getSelectedItem(cb)).getDescription());
                };
    }

}
