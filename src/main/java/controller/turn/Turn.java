package controller.turn;

/**
 * This interface models a player's turn during a match.
 */
public interface Turn {
    /**
     * Starts a new turn for the player passed as param.
     * @param username - the player's username
     */
    void start(String username);
}
