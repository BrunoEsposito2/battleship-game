package controller;

import java.util.Optional;

import controller.users.AccountManager;
import controller.users.AccountOperation;
import javafx.stage.Stage;
import model.Model;
import model.ModelImpl;
import model.enums.GameMode;
import model.enums.PlayerNumber;
import model.match.players.PlayerInfo;
import view.View;
import view.ViewImpl;
import view.dialog.DialogType;
import view.scene.SceneName;

/**
 * Concrete implementation of the app's mvc controller.
 */
public final class ControllerImpl implements Controller {

    private final Model model;
    private final View view;

    private final AccountManager manager;

    /**
     * Constructor of this class.
     * @param stage - the app's stage
     */
    public ControllerImpl(final Stage stage) {
        view = new ViewImpl(stage);
        model = new ModelImpl();
        manager = new AccountOperation(model);
    }

    public AccountManager getAccountManager() {
        return this.manager;
    }

    @Override
    public void changeScene(final SceneName name) {
        view.loadScene(name);
    }

    @Override
    public Optional<String> launchDialog(final DialogType type, final String title, final String header, final String description) {
        return view.launchDialog(type, title, header, description);
    }

    @Override
    public Optional<model.enums.PlayerNumber> getCurrentPlayer() {
        return model.getCurrentPlayer();
    }

    @Override
    public void setCurrentPlayer(final model.enums.PlayerNumber playerNumber) {
        model.setCurrentPlayer(playerNumber);
    }

    @Override
    public Boolean isMatchOver(final int playerHits, final int opponentHits, final int opponentRemainingShips) {
        return model.isMatchOver(playerHits, opponentHits, opponentRemainingShips);
    }

    @Override
    public void setGameMode(final GameMode gameMode) {
        model.setGameMode(gameMode);
    }

    @Override
    public Optional<PlayerInfo> getPlayerInfo(final PlayerNumber number) {
        return model.getPlayerInfo(number);
    }

    @Override
    public void setPlayerInfo(final PlayerNumber number, final PlayerInfo info) {
        model.setPlayerInfo(number, info);
    }

}
