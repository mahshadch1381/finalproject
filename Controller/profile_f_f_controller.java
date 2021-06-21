package Controller;

import Client.followingclient;
import Client.followingfiles_client;
import Server.followingfiles2_server2;
import Server.followingserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Loader;
import model.Person;
import sample.Controller;

import java.io.IOException;

public class profile_f_f_controller {
    public String user;
    public Label username;
    public Label country;
    public Label followers;
    public Label followings;
    public Button unfollowbutton;
    public Button followbutton;
    public Button menubutton;
    public Label successfullabel;
    public Label unsuccessfullable;
    public Person p;

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        (new followingfiles2_server2()).start();
        followingfiles_client followingfiles_client=new followingfiles_client();
        p=followingfiles_client.following_get_info();
        username.setText(p.username);
        country.setText(p.country);
    }
    public void unfollowing(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new followingserver()).start();
        followingclient followingclient=new followingclient(p, Controller.mainUser);
        if(followingclient.unfollow().equals("false")){
            unsuccessfullable.setVisible(true);
        }else {
            successfullabel.setVisible(true);
        }
    }

    public void following(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new followingserver()).start();
        followingclient followingclient=new followingclient(p, Controller.mainUser);
        if(followingclient.follow().equals("ok")){
            successfullabel.setVisible(true);
        }
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }
}
