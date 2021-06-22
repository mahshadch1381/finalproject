package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import model.Loader;

import java.io.IOException;

public class menuController {

    public Button logout;
    public Button profilepage;
    public Button posting;
    public Button exit;

    public void mainpage(ActionEvent actionEvent) throws IOException {
        new Loader().load("sample");
    }

    public void profile(ActionEvent actionEvent) throws IOException {
        new Loader().load("ownprofile");
    }

    public void postpage(ActionEvent actionEvent) throws IOException {
        new Loader().load("posts");
    }

    public void exit(ActionEvent actionEvent) {

    }
}
