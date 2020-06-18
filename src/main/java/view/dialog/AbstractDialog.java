package view.dialog;

import java.util.Optional;

//package-private
public abstract class AbstractDialog {

    public abstract Optional<String> launch(DialogType type, String title, String header, String description);

}
