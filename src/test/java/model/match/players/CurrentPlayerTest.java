package model.match.players;

<<<<<<< HEAD
=======
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;
>>>>>>> 4034867715dbc423ce2bd507ad3af22ebf49feab
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import model.enums.PlayerNumber;

class CurrentPlayerTest {

    private CurrentPlayer currentPlayer = new CurrentPlayerImpl();

    @Test
    void testgetCurrentPlayer() {

        currentPlayer.setCurrentPlayer(PlayerNumber.PLAYER_ONE);
        assertEquals(PlayerNumber.PLAYER_ONE, currentPlayer.getCurrentPlayer().get());

        currentPlayer.setCurrentPlayer(PlayerNumber.PLAYER_TWO);
        assertEquals(PlayerNumber.PLAYER_TWO, currentPlayer.getCurrentPlayer().get());

        currentPlayer.setCurrentPlayer(null);
        assertEquals(Optional.empty(), currentPlayer.getCurrentPlayer());

    }

    @Test
    void testNextPlayer() {

        currentPlayer.setCurrentPlayer(PlayerNumber.PLAYER_ONE);
        currentPlayer.nextPlayer();
        assertEquals(PlayerNumber.PLAYER_TWO, currentPlayer.getCurrentPlayer().get());

        currentPlayer.setCurrentPlayer(PlayerNumber.PLAYER_TWO);
        currentPlayer.nextPlayer();
        assertEquals(PlayerNumber.PLAYER_ONE, currentPlayer.getCurrentPlayer().get());

        currentPlayer.setCurrentPlayer(null);
        currentPlayer.nextPlayer();
        assertEquals(Optional.empty(), currentPlayer.getCurrentPlayer());

    }

}
