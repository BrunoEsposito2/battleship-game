package model.match;

import java.util.Optional;
import application.Battleships;
import controller.Controller;
import model.enums.GameMode;
import model.enums.PlayerType;
import view.dialog.DialogType;

/**
 * Implementation of MatchManager interface.
 */
public final class MatchInitializer {

    private final Controller controller = Battleships.getController();
    private final GameMode gameMode;
    private final boolean aiPlayer;

    /**
     * the class' constructor.
     * @param username1 - player1's username.
     * @param username2 - player2's username.
     * @param playerType - player2's type.
     * @param gameMode - selected game mode.
     */
    public MatchInitializer(final String username1, final Optional<String> username2, final PlayerType playerType, final GameMode gameMode) {
        this.aiPlayer = playerType.equals(PlayerType.HUMAN) ? true : false;
        this.gameMode = gameMode;
        //TODO remove debug function.
        this.debugConstructorData(username1, username2);
    }

    private void debugConstructorData(final String username1, final Optional<String> username2) {
        controller.launchDialog(DialogType.INFORMATION, "[DEBUG] Match Started", "[DEBUG] Match is successfully started with these settings:",
                "player1: " + username1 + "\n"
              + "player2: " + username2.orElse("Ai") + "\n"
              + "gamemode: " + gameMode.getName());
    }

    /**
     * Start a new match.
     */
    public void startNewMatch() {
      //TODO call ship deployment for player1
      //TODO call ship deployment for player2
      //TODO start match with selection data
    }

}
