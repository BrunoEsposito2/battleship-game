package model;

import java.util.List;
import java.util.Optional;

import model.enums.GameMode;
import model.enums.PlayerNumber;
import model.match.players.PlayerInfo;
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

<<<<<<< HEAD

    void startBasicAI();
=======
    /**
     * @return the current player
     */
    Optional<model.enums.PlayerNumber> getCurrentPlayer();

    /**
     * @param playerNumber - the new current player
     */
    void setCurrentPlayer(model.enums.PlayerNumber playerNumber);

    /**
     * this method checks whether the player has won the match according to selected win conditions.
     * @param playerHits - how many times the player has hit the opponent's ships.
     * @param opponentHits - how many times the opponent has hit the player's ships.
     * @param opponentRemainingShips - how many (not sunk) ships the opponent still has.
     * @return true - if the player has won the match.
     */
    Boolean isMatchOver(int playerHits, int opponentHits, int opponentRemainingShips);

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
>>>>>>> 5d3ade73d23c2b4d07dabbab4b781952af1fa203

}
