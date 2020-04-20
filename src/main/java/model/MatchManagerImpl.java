package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Implementation of MatchManager interface.
 */
public final class MatchManagerImpl implements MatchManager {

    //TODO Consider making this class a singleton 
    // VERY UNFINISHED CLASS !!
    // will handle the gameloop

    private boolean isMatchStarted;
    private  final List<Player> players;
    private  final WinCondition wc;

    /**
     * This is the class' constructor.
     * @param players - a Set containing the players of the match
     * @param wc - the winCondition which determines when the match will end
     */
    public MatchManagerImpl(final Set<Player> players, final WinCondition wc) {
        this.isMatchStarted = false;
        this.players = new ArrayList<>(players);
        this.wc = wc;
    }

    @Override
    public void start() {
        if (isMatchStarted) {
            throw new IllegalStateException("A match is already in progress");
        }
        isMatchStarted = true;
        gameLoop();
    }

    private void gameLoop() {
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                // load grid for player(i)
                players.get(i).startTurn();
                // if(isMatchOver(wc)) break;
            }
        }
        //isMatchStarted = false;
    }
}
