package model.player;

import java.util.Optional;

import model.enums.PlayerType;

/**
 * Consists on players registered in the game.
 * 
 */
public interface Player {

    /**
     * Returns a specific player informations.
     * 
     * @param type
     *          represents the player type
     *
     * @param username
     *          represents the name of the player
     *
     * @return the specific player 
     */
    Optional<PlayerPrototype> getPlayerInfo(PlayerType type, String username);

    /**
     * Creates a new player.
     * 
     * @param type
     *          represents the player type
     *
     * @param username
     *          represents the name of the player
     *
     * @param password
     *          represents the password of the player
     */
    void createPlayerProfile(PlayerType type, String username, String password);

}
