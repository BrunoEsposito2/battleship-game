package model.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import controller.players.Player;
import model.enums.GameMode;


/**
 * Implementation of MatchManager interface.
 */
public final class MatchManagerImpl implements MatchManager {

    private final MatchStatus matchStatus = new MatchStatusImpl();
    private final List<Player> playerList;
    private final GameMode gameMode;
    private boolean hasStartedMatch;

    /**
     * This is the class' constructor.
     * @param players - a Set containing the players of the match
     * @param wc - the WinCondition which determines when the match will end
     */
    public MatchManagerImpl(final Set<Player> players, final GameMode wc) {
        this.hasStartedMatch = false;
        this.playerList = new ArrayList<>(players);
        this.gameMode = wc;
    }

    @Override
    public Player startNewMatch() {
        if (hasStartedMatch) {
            throw new IllegalStateException("Cannot start more than one match from the same MatchManager instance");
        }
        hasStartedMatch = true;
        return gameLoop();
    }

    private Player gameLoop() {
        while (true) {
            for (int i = 0; i < playerList.size(); i++) {
                //TODO load grid & prepare stuff for player(i)'s turn
                //playerList.get(i).startTurn();
                if (matchStatus.isMatchOver(gameMode)) {
                    return playerList.get(i); // returns the winner
                }
            }
        }
    }

    /*
    private void postMatchOperations(final Player winner) {
        DialogBuilder.buildAndLaunch(DialogBuilder.DialogType.INFORMATION, "Match over!", "Player " + winner.getName() + " won the match!\nPress ok to go back to menu.", null);
        SceneManager.INSTANCE.switchScene(SceneName.MAIN);
    }
    */
}
