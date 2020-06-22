package model;

import java.util.List;
import java.util.Optional;

import model.players.CurrentPlayer;
import model.players.Player;
import model.players.PlayerManager;
import model.players.PlayerOperation;

/**
 * Implementation of Model interface.
 */
public final class ModelImpl implements Model {

    private final CurrentPlayer currentPlayer = new CurrentPlayer();

    @Override
    public PlayerManager setPlayerManager(final Optional<List<Player>> players) {
        return new PlayerOperation(players);
    }

    /**
     * @return the current player
     */
    @Override
    public Optional<model.enums.Player> getCurrentPlayer() {
        return currentPlayer.getCurrentPlayer();
    }

    /**
     * @param player - the new current player
     */
    @Override
    public void setCurrentPlayer(final model.enums.Player player) {
        currentPlayer.setCurrentPlayer(player);
    }

}
