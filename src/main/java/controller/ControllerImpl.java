package controller;

import controller.ui.ProfileController;
import controller.users.ManagerInstance;

public class ControllerImpl implements Controller {

    private final ProfileController profileCtrl;

    public ControllerImpl() {
        ManagerInstance.getInstance();
        this.profileCtrl = new ProfileController();
    }
}
