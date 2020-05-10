package view.dialog;

import java.util.Optional;

import model.enums.DialogType;

/**
 * This interface provides a launch method to create and launch different types of dialogs.
 */
public interface DialogBuilder {

    /**
     * This method creates and launches a dialog using the passed parameters.
     * Parameters you don't care about can be passed as null.
     * Dialogs of type Login will not use the parameter description.
     * Dialogs of type Login return the user's password, or an Optional.empty if none was typed in.
     * Other dialog types currently return no value (Optional.empty).
     * @param type - the type of dialog to launch.
     * @param title - dialog's title
     * @param header - dialog's header
     * @param description - dialog's description
     * @return the result of the dialog's operations, if any.
     */
     Optional<String> launch(DialogType type, String title, String header, String description);

}
