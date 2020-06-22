package model;

import java.util.List;
import java.util.Optional;

import model.enums.GameMode;
import model.enums.PlayerNumber;
import model.match.players.CurrentPlayer;
import model.match.players.PlayerInfo;
import model.players.Player;
import model.players.PlayerManager;
import model.players.PlayerOperation;

/**
 * Implementation of Model interface.
 */
public final class ModelImpl implements Model {

    private final CurrentPlayer currentPlayer = new CurrentPlayer();
    private Optional<PlayerInfo> player1 = Optional.empty(); 
    private Optional<PlayerInfo> player2 = Optional.empty(); 
    private Optional<GameMode> gameMode = Optional.empty();

    @Override
    public PlayerManager setPlayerManager(final Optional<List<Player>> players) {
        return new PlayerOperation(players);
    }

    /**
     * @return the current player
     */
    @Override
    public Optional<model.enums.PlayerNumber> getCurrentPlayer() {
        return currentPlayer.getCurrentPlayer();
    }

    /**
     * @param playerNumber - the new current player
     */
    @Override
    public void setCurrentPlayer(final model.enums.PlayerNumber playerNumber) {
        currentPlayer.setCurrentPlayer(playerNumber);
    }

    @Override
    public void setGameMode(final GameMode gameMode) {
        this.gameMode = Optional.ofNullable(gameMode);
    }

    @Override
    public Optional<PlayerInfo> getPlayerInfo(final PlayerNumber number) {
        return number.equals(PlayerNumber.PLAYER_ONE) ? player1 : player2;
    }

    @Override
    public void setPlayerInfo(final PlayerNumber number, final PlayerInfo info) {
        if (number.equals(PlayerNumber.PLAYER_ONE)) {
            player1 = Optional.ofNullable(info);
        } else {
            player2 = Optional.ofNullable(info);
        }
    }

    @Override
    public Boolean isMatchOver(final int playerHits, final int opponentHits, final int opponentRemainingShips) {
        return gameMode.isPresent() ? gameMode.get().isMatchOver(playerHits, opponentHits, opponentRemainingShips) : false;
    }

}
