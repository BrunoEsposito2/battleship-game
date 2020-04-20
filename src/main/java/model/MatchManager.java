package model;


import java.util.Set;

/**
 * This interface is used to start a new match by calling the start() method.
 * The new match will be managed from start to finish by this interface, using the settings
 * passed as parameters in the start() method.
 */
public interface MatchManager {
    /**
     * This method is used to start a new match.
     * Once a match is started, the start() method cannot be called again until the match is over.
     * @param players a Set containing the players
     * @param wc the win conditions of the match (decides when the match is considered over)
     */
    void start(Set<Player> players, WinCondition wc);
}
