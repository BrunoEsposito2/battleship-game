package view.dialog;

import java.util.Optional;

import model.enums.DialogType;

/**
 * The concrete implementation of DialogBuilder.
 */
public final class DialogBuilderimpl implements DialogBuilder {

    @Override
    public Optional<String> launch(final DialogType type, final String title, final String header, final String description) {
        return (type.equals(DialogType.LOGIN))
                ? new LoginDialog().launch(title, header) : new MessageDialog().launch(type, title, header, description);
    }

}
