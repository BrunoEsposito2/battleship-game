package model.enums;

/**
 * This enum models the win conditions for a match, selectable by the player in the matchSettings menu.
 */
public enum GameMode {

    CLASSIC("Classic", "Sink all enemy ships to win.") {
        @Override
        public boolean checkWinCondition() {
            //TODO add implementation
            return true;
        }
    },
    HIGH_SCORE("High-score", "Have the highest score at the end of the match.") {
        @Override
        public boolean checkWinCondition() {
            //TODO add implementation
            return true;
        }
    };

    private final String name;
    private final String description;

    GameMode(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public boolean checkWinCondition() {
        // this always gets overridden
        return true;
    }

    /**
     * @return the GameMode's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the GameMode's description
     */
    public String getDescription() {
        return description;
    }

}
