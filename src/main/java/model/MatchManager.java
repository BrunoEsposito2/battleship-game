package model;

/**
 * This interface is used to start a new match by calling the start() method.
 * The new match will be managed from start to finish by this interface, using the settings
 * passed as parameters in the start() method.
 */
public interface MatchManager {
    /**
     * This method is used to start a new match.
     * Once a match is started, the startNewMatch() method cannot be called again from the same MatchManager instance.
     */
    void startNewMatch();
}
