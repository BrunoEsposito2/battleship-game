package model;

import java.util.NoSuchElementException;

/**
 * This enum models the win conditions for a match, selectable by the player in the matchSettings menu.
 */
public enum WinCondition {
    // New win conditions can be added here
    // NAMES PASSED TO THE CONSTRUCTOR MUST BE DISTINCT!!
    ALL_ENEMY_SHIPS_SUNK("Classic", "Sink all enemy ships to win."),
    HIGHEST_SCORE_AFTER_N_TURNS("Highest score", "Have the highest score at the end of the match.");

    private final String description;
    private final String name;

    private WinCondition(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return the WinCondition's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the WinCondition's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method is used to get the enum value from its name field.
     * @param name - returned WinCondition will match this name
     * @return the WinCondition whose name matches the one passed as parameter
     */
    public static WinCondition getWinConditionFromName(final String name) {
        for (WinCondition wc : WinCondition.values()) {
            if (wc.name.equals(name)) {
                return wc;
            }
        }
        throw new NoSuchElementException("No WinCondition has this name");
    }
}
