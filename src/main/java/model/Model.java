package model;

import java.util.List;
import java.util.Optional;

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


    void startBasicAI();

}
