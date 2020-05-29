package view.dialog;

import java.util.Optional;

//package-private
abstract class AbstractDialog {

    protected abstract Optional<String> launch(DialogType type, String title, String header, String description);

}
