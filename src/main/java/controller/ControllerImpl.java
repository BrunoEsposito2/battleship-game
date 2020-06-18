package controller;

import controller.ui.ProfileController;
import model.Model;
import view.View;

public class ControllerImpl implements Controller {

    private final Model model;
    private final View view;

    private ProfileController profileCtrl;

    public ControllerImpl(final Model model, final View view) {
        this.model = model;
        this.view = view;
        //this.accountMng = new AccountOperation();
        //ManagerInstance.getInstance();
    }

    public final void setAccountObserver() {
        this.profileCtrl = new ProfileController();
    }

}
