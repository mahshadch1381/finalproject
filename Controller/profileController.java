package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Loader;
import model.Person;

import java.io.IOException;

public class profileController {
    public Label user;
    public Label numoffolloing;
    public Label numoffollowers;
    public Label country;
    public Button follow;
    public Button unfollow;
    public Button edit;
    public Person person;
    public Button menubutton;

    @FXML
    public void initialize(){
        person=postdetailcontroller.getPerson();
        user.setText(person.username);
        country.setText(person.country);
        //تعداد فالور ها و اینا با سوکت
    }

    public void following(ActionEvent actionEvent) {
    }

    public void unfollowing(ActionEvent actionEvent) {
    }

    public void editing(ActionEvent actionEvent) {
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }
}
