package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.SceneManager;

/**
 * Implementation of MatchManager interface.
 */
public final class MatchManagerImpl implements MatchManager {

    private final MatchStatus ms = new MatchStatusImpl();
    private final List<Player> players;
    private final WinCondition wc;
    private boolean hasMatchStarted;

    /**
     * This is the class' constructor.
     * @param players - a Set containing the players of the match
     * @param wc - the winCondition which determines when the match will end
     */
    public MatchManagerImpl(final Set<Player> players, final WinCondition wc) {
        this.hasMatchStarted = false;
        this.players = new ArrayList<>(players);
        this.wc = wc;
    }

    @Override
    public void startNewMatch() {
        if (hasMatchStarted) {
            throw new IllegalStateException("Cannot start more than one match from the same MatchManager instance");
        }
        hasMatchStarted = true;
        postMatchOperations(gameLoop());
    }

    private Player gameLoop() {
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                //TODO load grid & prepare stuff for player(i)'s turn
                players.get(i).startTurn();
                if (ms.isMatchOver(wc)) {
                    return players.get(i);
                }
            }
        }
    }

    private void postMatchOperations(final Player winner) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Winner!");
        alert.setContentText("Player " + winner.getName() + " won the match!\nPress ok to go back to menu.");
        alert.showAndWait();
        SceneManager.INSTANCE.switchScene(SceneName.MAIN);
    }
}
