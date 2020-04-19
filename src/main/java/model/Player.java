package model;

/**
 * This interface models a player (human or ai indifferently) during a match.
 */
public interface Player {
    /**
     * @return the player's name
     */
    String getName();
    
    /**
     * @return true if the player is human
     */
    Boolean isHuman();
    
    /**
     * Starts the player's turn.
     * The turn is managed by this method from start to finish
     */
    void startTurn();
}
