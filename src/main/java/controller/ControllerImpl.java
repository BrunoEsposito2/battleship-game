package controller;

import controller.ui.ProfileController;
import controller.users.ManagerInstance;
import model.Model;
import view.View;

public class ControllerImpl implements Controller {

    private final Model model;
    private final View view;

    private final ProfileController profileCtrl;

    public ControllerImpl(final Model model, final View view) {
        this.model = model;
        this.view = view;
        ManagerInstance.getInstance();
        this.profileCtrl = new ProfileController();
    }
}
