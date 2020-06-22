package controller;

import java.util.Optional;

import controller.users.AccountManager;
import controller.users.AccountOperation;
import javafx.stage.Stage;
import model.Model;
import model.ModelImpl;
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

    /**
     * @return the current player
     */
    @Override
    public Optional<model.enums.Player> getCurrentPlayer() {
        return model.getCurrentPlayer();
    }

    /**
     * @param player - the new current player
     */
    @Override
    public void setCurrentPlayer(final model.enums.Player player) {
        model.setCurrentPlayer(player);
    }

}
