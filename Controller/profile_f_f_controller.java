package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Loader;
import model.Person;

import java.io.IOException;

public class profile_f_f_controller {
    public Label username;
    public Label country;
    public Person person;
    public Label followers;
    public Label followings;
    public Button unfollowbutton;
    public Button followbutton;
    public Button menubutton;

    @FXML
    public void initialize(){
        person=personitemcontroller.getPerson();
        username.setText(person.username);
        country.setText(person.country);
        //
    }
    public void unfollowing(ActionEvent actionEvent) {
    }

    public void following(ActionEvent actionEvent) {
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }
}
