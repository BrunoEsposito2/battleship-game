package model.match;

import controller.players.Player;

/**
 * This interface is used to start a new match by calling the start() method.
 * The new match will be managed from start to finish by this interface, using the settings
 * passed as parameters in the start() method.
 */
public interface MatchManager {
    /**
     * This method is used to start a new match.
     * Can be called once per MatchManager instance.
     * @return the winner of the match
     */
    Player startNewMatch();
}
