package view.dialog;

import java.util.Optional;

import model.enums.DialogType;

/**
 * This class is the only concrete implementation of DialogBuilder.
 * Concrete instances of DialogBuilder must be of this class type.
 */
public final class GenericDialog implements DialogBuilder {

    @Override
    public Optional<String> launch(final DialogType type, final String title, final String header, final String description) {
        return (type.equals(DialogType.LOGIN))
                ? new LoginDialog().launch(title, header) : new SimpleDialog().launch(type, title, header, description);
    }

}
