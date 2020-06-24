package model;

import java.util.List;
import java.util.Optional;

import model.enums.PlayerNumber;
<<<<<<< HEAD
import model.match.PlaygroundBattle;
=======
import model.gamemode.GameMode;
>>>>>>> 95320364063f8a0752f48aae1265902e42b70738
import model.match.players.PlayerInfo;
import model.players.Player;
import model.players.PlayerManager;
import model.util.Pair;

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
    /**
     * 
     * @return the new PlaygroungBattle containing all the ships 
     *          positionated by AI basic implementation 
     */
    PlaygroundBattle startBasicAI();

    /**
     * 
     * @return the point in which set the new attack, 
     *          calculated by AI basic implementation
     */
    Pair<Integer, Integer> getNextHitPointAI();

=======
    void startBasicAI();
>>>>>>> 95320364063f8a0752f48aae1265902e42b70738
    /**
     * @return the current player
     */
    Optional<PlayerNumber> getCurrentPlayer();

    /**
     * @param playerNumber - the new current player
     */
    void setCurrentPlayer(PlayerNumber playerNumber);

    /**
     * this method checks whether the player has won the match according to selected win conditions.
     * @param hits - how many times the player has hit the opponent's ships.
     * @param opponentRemainingShips - how many (not sunk) ships the opponent still has.
     * @return true - if the player has won the match.
     */
    Boolean isMatchOver(int hits, int opponentRemainingShips);

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
