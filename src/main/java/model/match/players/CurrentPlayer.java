package model.match.players;

import java.util.Optional;

/**
 *  This class keeps track of the current player.
 */
public class CurrentPlayer {

    private Optional<model.enums.PlayerNumber> current = Optional.empty();

    /**
     * @return the current player
     */
    public Optional<model.enums.PlayerNumber> getCurrentPlayer() {
        return current;
    }

    /**
     * @param playerNumber - the new current player
     */
    public void setCurrentPlayer(final model.enums.PlayerNumber playerNumber) {
        current = Optional.ofNullable(playerNumber);
    }

}
