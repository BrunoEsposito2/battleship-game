package model.players;

import java.util.Optional;

/**
 *  This class keeps track of the current player.
 */
public class CurrentPlayer {

    private Optional<model.enums.Player> current = Optional.empty();

    /**
     * @return the current player
     */
    public Optional<model.enums.Player> getCurrentPlayer() {
        return current;
    }

    /**
     * @param player - the new current player
     */
    public void setCurrentPlayer(final model.enums.Player player) {
        current = Optional.ofNullable(player);
    }

}
