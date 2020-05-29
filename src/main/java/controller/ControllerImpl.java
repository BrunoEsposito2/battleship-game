package controller;

import controller.ui.ProfileManager;
import controller.users.ManagerInstance;

public class ControllerImpl implements Controller {

    private final ProfileManager profileMng;

    public ControllerImpl() {
        ManagerInstance.getInstance();
        this.profileMng = new ProfileManager();
    }
}
