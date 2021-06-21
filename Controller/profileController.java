package Controller;

import Client.countoffollowers_client;
import Server.count_of_followers_server;
import Server.count_of_following_server;
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
    public void initialize() throws IOException, ClassNotFoundException {
        person=postdetailcontroller.getPerson();
        user.setText(person.username);
        country.setText(person.country);
        (new count_of_followers_server()).start();
        countoffollowers_client countoffollowers_client=new countoffollowers_client(person.username);
        String CountOfFollowers=countoffollowers_client.count_of_followers();
        (new count_of_following_server()).start();
        countoffollowers_client countoffollowers_client2=new countoffollowers_client(person.username);
        String CountOfFollowings=countoffollowers_client2.count_of_following();
        numoffolloing.setText(CountOfFollowings);
        numoffollowers.setText(CountOfFollowers);
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
