package model.match;

import java.util.Optional;
import application.Battleships;
import controller.Controller;
import model.enums.GameMode;
import model.enums.PlayerType;
import view.scene.SceneName;

/**
 * Implementation of MatchManager interface.
 */
public final class MatchInitializer {

    private final Controller controller = Battleships.getController();
    private final boolean aiPlayer;

    /**
     * the class' constructor.
     * @param username1 - player1's username.
     * @param username2 - player2's username.
     * @param playerType - player2's type.
     * @param gameMode - selected game mode.
     */
    public MatchInitializer(final String username1, final Optional<String> username2, final PlayerType playerType, final GameMode gameMode) {
        aiPlayer = playerType.equals(PlayerType.HUMAN) ? true : false;
        controller.setGameMode(gameMode);
    }

    /**
     * Start a new match.
     */
    public void startNewMatch() {
      controller.changeScene(SceneName.SHIP_DEPLOYMENT);
    }

}
