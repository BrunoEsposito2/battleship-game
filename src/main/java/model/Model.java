package model;

import java.util.List;
import java.util.Optional;

import model.enums.GameMode;
import model.players.Player;
import model.players.PlayerManager;

/**
 * Main model interface containing all the main interactions available.
 * 
 */
public interface Model {

    /**
     * 
     * @param players - players saved on the system
     * @return the model's player manager object.
     */
    PlayerManager setPlayerManager(Optional<List<Player>> players);

    /**
     * @return the current player
     */
    Optional<model.enums.Player> getCurrentPlayer();

    /**
     * @param player - the new current player
     */
    void setCurrentPlayer(model.enums.Player player);
    
    /**
     * @return the current gameMode
     */
    Optional<GameMode> getGameMode();

    /**
     * @param gameMode - the new current gameMode
     */
    void setGameMode(GameMode gameMode);

}
