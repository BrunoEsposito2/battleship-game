package controller.ui;

import controller.users.ManagerInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import view.SceneManager;
import view.dialog.DialogBuilder;
import view.dialog.DialogBuilderimpl;
import model.enums.DialogType;
import model.enums.SceneName;

public class ProfileManager {

    private final DialogBuilder dialog;

    @FXML
    private Button signInButton, cancelAccountButton, profileBackButton;

    @FXML
    private TextField signInUsername, signInPassword, removeUsername, removePassword;

    public ProfileManager() {
        this.dialog = new DialogBuilderimpl();
    }

    @FXML
    public final void turnBack() {
        SceneManager.INSTANCE.switchScene(SceneName.MAIN);
    }

    @FXML
    public final void accountSignIn() {
        if (!signInUsername.getText().equals("") && !signInPassword.getText().equals("")) {
            ManagerInstance.getInstance().get().createAccount(String.valueOf(signInUsername.getText()).trim(), 
                    String.valueOf(signInPassword.getText()).trim());
            this.dialog.launch(DialogType.INFORMATION, "Account Created", "Your account has been created.", null);
        } else if (signInUsername.getText().equals("") || signInPassword.getText().equals("")) {
            this.dialog.launch(DialogType.WARNING, "Account Creation", "Please, insert a valid username and password!",
                    "Username and/or password may be absent.");
        } else {
            this.dialog.launch(DialogType.INFORMATION, "Account Creation", "Please, insert username and password", null);
        }
        signInUsername.clear();
        signInPassword.clear();
    }

    @FXML
    public final void accountRemove() {
        if (!removeUsername.getText().equals("") && !removePassword.getText().equals("")) {
            ManagerInstance.getInstance().get().removeAccount(String.valueOf(removeUsername.getText()).trim(), 
                    String.valueOf(removePassword.getText()).trim());
            this.dialog.launch(DialogType.INFORMATION, "Account Removed", "Your account has been deleted.", null);
        } else if (removeUsername.getText().equals("") || removePassword.getText().equals("")) {
            this.dialog.launch(DialogType.WARNING, "Account Creation", "Please, an existing username and/or a valid password", null);
        } else {
            this.dialog.launch(DialogType.INFORMATION, "Account Creation", "Please, insert existing username and password", null);
        }
        removeUsername.clear();
        removePassword.clear();
    }

}
