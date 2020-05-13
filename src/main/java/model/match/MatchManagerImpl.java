package model.match;

import java.util.Arrays;
import java.util.List;
import model.enums.GameMode;
import model.enums.PlayerType;


/**
 * Implementation of MatchManager interface.
 */
public final class MatchManagerImpl implements MatchManager {

    private final MatchStatus matchStatus = new MatchStatusImpl();
    private final List<String> playerList;
    private final GameMode gameMode;
    private final boolean isOpponentAi;
    private boolean hasStartedMatch;

    /**
     * This is the class' constructor.
     * @param players - a Set containing the players of the match
     * @param wc - the WinCondition which determines when the match will end
     */
    public MatchManagerImpl(final String username1, final String username2, PlayerType playerType, final GameMode wc) {
        this.hasStartedMatch = false;
        this.playerList = Arrays.asList(username1, username2);
        this.isOpponentAi = playerType.equals(PlayerType.HUMAN) ? true : false;
        this.gameMode = wc;
    }

    @Override
    public String startNewMatch() {
        if (hasStartedMatch) {
            throw new IllegalStateException("Cannot start more than one match from the same MatchManager instance");
        }
        hasStartedMatch = true;
        return gameLoop();
    }

    private String gameLoop() {
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

}
