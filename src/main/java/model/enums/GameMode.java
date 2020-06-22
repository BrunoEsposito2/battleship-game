package model.enums;

/**
 * This enum models the win conditions for a match, selectable by the player in the matchSettings menu.
 */
public enum GameMode {

    /**
     * Sink all enemy ships to win.
     */
    CLASSIC("Classic", "Sink all enemy ships to win.") {
        @Override
        public boolean isMatchOver(final int playerHits, final int opponentHits, final int opponentRemainingShips) {
            return opponentRemainingShips < 1;
        }
    },

    /**
     * Have the highest score at the end of the match.
     */
    HIGH_SCORE("High-score", "Have the highest score at the end of the match.") {
        @Override
        public boolean isMatchOver(final int playerHits, final int opponentHits, final int opponentRemainingShips) {
            return playerHits > opponentHits;
        }
    };

    private final String name;
    private final String description;

    GameMode(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * this method checks whether the player has won the match according to selected win conditions.
     * @param playerHits - how many times the player has hit the opponent's ships.
     * @param opponentHits - how many times the opponent has hit the player's ships.
     * @param opponentRemainingShips - how many (not sunk) ships the opponent still has.
     * @return true - if the player has won the match.
     */
    public abstract boolean isMatchOver(int playerHits, int opponentHits, int opponentRemainingShips);

    /**
     * @return the GameMode's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the GameMode's description.
     */
    public String getDescription() {
        return description;
    }

}
