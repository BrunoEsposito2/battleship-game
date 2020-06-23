package model.gamemode;

import java.util.Optional;

/**
 * Concrete implementation of class WinCondition.
 */
public final class WinConditionImpl implements WinCondition {

    private Optional<GameMode> gameMode;

    @Override
    public Boolean isMatchOver(final int playerHits, final int opponentRemainingShips) {
        return gameMode.isPresent() ? gameMode.get().isMatchOver(playerHits, opponentRemainingShips) : false;
    }

    @Override
    public void setGameMode(final GameMode gameMode) {
        this.gameMode = Optional.ofNullable(gameMode);
    }

}
