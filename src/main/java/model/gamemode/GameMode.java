package model.gamemode;

/**
 * This enum models the win conditions for a match, selectable by the player in the matchSettings menu.
 */
public enum GameMode {

    /**
     * Sink all enemy ships to win.
     */
    CLASSIC("Classic", "Sink all enemy ships to win.") {
        @Override
        protected boolean isMatchOver(final int playerSunkShips, final int opponentRemainingShips) {
            return opponentRemainingShips == 0;
        }
    },

    /**
     * Be the first player to sink a ship.
     */
    FIRST_SHIP_SUNK("Sink a Ship", "Be the first player to sink a ship.") {
        @Override
        protected boolean isMatchOver(final int playerSunkShips, final int opponentRemainingShips) {
            return playerSunkShips > 0;
        }
    },

    /**
     * Be the first player to sink 3 ships.
     */
    THIRD_SHIP_SUNK("Sink Three Ships", "Be the first player to sink 3 ships.") {
        @Override
        protected boolean isMatchOver(final int playerSunkShips, final int opponentRemainingShips) {
            return playerSunkShips > 2;
        }
    };

    private final String name;
    private final String description;

    GameMode(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * this method checks whether the player has won the match.
     * @param playerSunkShips - how many ships the player has sunk.
     * @param opponentRemainingShips - how many (not sunk) ships the opponent still has.
     * @return true - if the player has won the match.
     */
    protected abstract boolean isMatchOver(int playerSunkShips, int opponentRemainingShips);

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
