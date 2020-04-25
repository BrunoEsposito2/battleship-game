package model.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.enums.SceneName;
import model.enums.GameMode;
import model.player.Player;
import view.AlertBuilder;
import view.SceneManager;

/**
 * Implementation of MatchManager interface.
 */
public final class MatchManagerImpl implements MatchManager {

    private final MatchStatus ms = new MatchStatusImpl();
    private final List<Player> players;
    private final GameMode wc;
    private boolean hasStartedMatch;

    /**
     * This is the class' constructor.
     * @param players - a Set containing the players of the match
     * @param wc - the WinCondition which determines when the match will end
     */
    public MatchManagerImpl(final Set<Player> players, final GameMode wc) {
        this.hasStartedMatch = false;
        this.players = new ArrayList<>(players);
        this.wc = wc;
    }

    @Override
    public void startNewMatch() {
        if (hasStartedMatch) {
            throw new IllegalStateException("Cannot start more than one match from the same MatchManager instance");
        }
        hasStartedMatch = true;
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
        AlertBuilder.buildAndLaunch(AlertType.INFORMATION,"Match over!","Player " + winner.getName() + " won the match!\nPress ok to go back to menu.",null);
        SceneManager.INSTANCE.switchScene(SceneName.MAIN);
    }
}
