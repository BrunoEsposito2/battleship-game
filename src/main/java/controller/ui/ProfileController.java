package controller.ui;

import controller.users.ManagerInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import view.scene.SceneManager;
import view.dialog.DialogLauncher;
import view.dialog.DialogType;
import view.scene.SceneName;

public class ProfileController {

    //private final MessageDialog dialog;

    @FXML
    private Button signInButton, cancelAccountButton, profileBackButton;

    @FXML
    private TextField signInUsername, signInPassword, removeUsername, removePassword;

    public ProfileController() {
        //this.dialog = new DialogBuilderimpl();
    }

    @FXML
    public final void turnBack() {
        SceneManager.INSTANCE.switchScene(SceneName.MAIN);
    }

    @FXML
    public final void accountSignIn() {
        if (!signInUsername.getText().equals("") && !signInPassword.getText().equals("")) {
            try {
                ManagerInstance.getInstance().get().createAccount(String.valueOf(signInUsername.getText()).trim(), 
                        String.valueOf(signInPassword.getText()).trim());
                DialogLauncher.launch(DialogType.INFORMATION, "Account Created", "Your account has been created.", null);
            } catch (Exception e) {
                DialogLauncher.launch(DialogType.INFORMATION, "Account Create Exception", e.getMessage(), null);
            }
        } else if (signInUsername.getText().equals("") && signInPassword.getText().equals("")) {
            DialogLauncher.launch(DialogType.WARNING, "Account Creation", "Please, insert username and password!", null);
        } else {
            DialogLauncher.launch(DialogType.INFORMATION, "Account Creation", "Please, insert a valid username and password", 
                    "Username and/or password may be absent.");
        }
        signInUsername.clear();
        signInPassword.clear();
    }

    @FXML
    public final void accountRemove() {
        if (!removeUsername.getText().equals("") && !removePassword.getText().equals("")) {
            try {
                ManagerInstance.getInstance().get().removeAccount(String.valueOf(removeUsername.getText()).trim(), 
                        String.valueOf(removePassword.getText()).trim());
                DialogLauncher.launch(DialogType.INFORMATION, "Account Removed", "Your account has been deleted.", null);
            } catch (Exception e) {
                DialogLauncher.launch(DialogType.INFORMATION, "Account Remove Exception", e.getMessage(), null);
            }
        } else if (removeUsername.getText().equals("") && removePassword.getText().equals("")) {
            DialogLauncher.launch(DialogType.WARNING, "Account Creation", "Please, insert existing username and password", null);
        } else {
            DialogLauncher.launch(DialogType.INFORMATION, "Account Creation", "Please, insert an existing username and/or a valid password", null);
        }
        removeUsername.clear();
        removePassword.clear();
    }

}
