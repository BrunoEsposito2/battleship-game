package model.match.players;

import model.enums.PlayerNumber;
import model.enums.PlayerType;

/**
 * This class contains info on a player during a match.
 */
public final class PlayerInfo {

    private final String username;
    private final PlayerType type;
    private final PlayerNumber number;

    /**
     * Constructor of this class.
     * @param username - player's username
     * @param type - player type
     * @param number - player number
     */
    public PlayerInfo(final String username, final PlayerType type, final PlayerNumber number) {
        this.username = username;
        this.type = type;
        this.number = number;
    }

    /**
     * @return player's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return player's username
     */
    public PlayerType getType() {
        return type;
    }

    /**
     * @return player's username
     */
    public PlayerNumber getNumber() {
        return number;
    }
}
