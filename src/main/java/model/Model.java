package model;

import java.util.List;
import java.util.Optional;

import model.enums.GameMode;
import model.enums.PlayerNumber;
import model.players.Player;
import model.players.PlayerInfo;
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
    Optional<model.enums.PlayerNumber> getCurrentPlayer();

    /**
     * @param playerNumber - the new current player
     */
    void setCurrentPlayer(model.enums.PlayerNumber playerNumber);

    /**
     * @return the current gameMode
     */
    Optional<GameMode> getGameMode();

    /**
     * @param gameMode - the new current gameMode
     */
    void setGameMode(GameMode gameMode);

    /**
     * 
     * @param number - player's number
     * @return the player's info, if any
     */
    Optional<PlayerInfo> getPlayerInfo(PlayerNumber number);

    /**
     * @param number - player's number
     * @param info - player's info
     */
    void setPlayerInfo(PlayerNumber number, PlayerInfo info);

}
