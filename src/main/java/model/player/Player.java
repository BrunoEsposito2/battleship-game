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
     * @param username
     * @return player
     */
    Optional<PlayerPrototype> getPlayerInfo(PlayerType type, String username);

    /**
     * 
     * @param type
     * @param username
     * @param password
     */
    void createPlayerProfile(PlayerType type, String username, String password);

}
