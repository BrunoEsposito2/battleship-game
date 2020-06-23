package controller.match;

import java.util.Optional;
import application.Battleships;
import controller.Controller;
import model.enums.PlayerNumber;
import model.enums.PlayerType;
import model.gamemode.GameMode;
import model.match.players.PlayerInfo;
import view.scene.SceneName;

/**
 * Implementation of MatchInitializer interface.
 */
public final class MatchInitializerImpl implements MatchInitializer {

    private final Controller controller = Battleships.getController();
    private PlayerInfo player1;
    private PlayerInfo player2;

    private void createPlayers(final String username1, final Optional<String> username2, final PlayerType playerType) {
        player1 = new PlayerInfo(username1, PlayerType.HUMAN, PlayerNumber.PLAYER_ONE);
        player2 = new PlayerInfo(username2.orElse("AI"), playerType, PlayerNumber.PLAYER_TWO);
    }

    private void updateMatchData(final GameMode gameMode, final PlayerInfo player1, final PlayerInfo player2) {
        controller.setGameMode(gameMode);
        controller.setCurrentPlayer(PlayerNumber.PLAYER_ONE);
        controller.setPlayerInfo(PlayerNumber.PLAYER_ONE, player1);
        controller.setPlayerInfo(PlayerNumber.PLAYER_TWO, player2);
    }

    @Override
    public void startNewMatch(final String username1, final Optional<String> username2, final PlayerType playerType, final GameMode gameMode) {
      createPlayers(username1, username2, playerType);
      updateMatchData(gameMode, player1, player2);
      controller.changeScene(SceneName.SHIP_DEPLOYMENT);
    }

}
