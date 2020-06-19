package model;

import java.util.List;
import java.util.Optional;

import model.players.Player;
import model.players.PlayerManager;
import model.players.PlayerOperation;

public final class ModelImpl implements Model {

    @Override
    public PlayerManager setPlayerManager(final Optional<List<Player>> players) {
        final PlayerManager manager = new PlayerOperation(players);
        return manager;
    }

}
