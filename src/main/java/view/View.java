package view;

import java.io.IOException;

import controller.Controller;

public interface View {

    void launch(Controller controller) throws IOException;

}
