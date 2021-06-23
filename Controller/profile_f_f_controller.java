package Controller;

import Client.countoffollowers_client;
import Client.followingclient;
import Client.followingfiles_client;
import Client.unfollowing_client;
import Server.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ImageView profile;

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        (new followingfiles2_server2()).start();
        followingfiles_client followingfiles_client=new followingfiles_client();
        p=followingfiles_client.following_get_info();
        username.setText(p.username);
        country.setText(p.country);
        Image image=new Image(p.profilePath);
        profile.setImage(image);
        (new count_of_followers_server()).start();
        countoffollowers_client countoffollowers_client=new countoffollowers_client(p.username);
        String CountOfFollowers=countoffollowers_client.count_of_followers();
        (new count_of_following_server()).start();
        countoffollowers_client countoffollowers_client2=new countoffollowers_client(p.username);
        String CountOfFollowings=countoffollowers_client2.count_of_following();
        followings.setText(CountOfFollowings);
        followers.setText(CountOfFollowers);
    }
    public void unfollowing(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new unfollowingserver()).start();
        unfollowing_client unfollowingclient=new unfollowing_client(p, Controller.mainUser);
        if(unfollowingclient.unfollow().equals("ok")){
            successfullabel.setVisible(false);
            unsuccessfullable.setVisible(true);
            (new count_of_followers_server()).start();
            countoffollowers_client countoffollowers_client=new countoffollowers_client(p.username);
            String CountOfFollowers=countoffollowers_client.count_of_followers();
            (new count_of_following_server()).start();
            countoffollowers_client countoffollowers_client2=new countoffollowers_client(p.username);
            String CountOfFollowings=countoffollowers_client2.count_of_following();
            followings.setText(CountOfFollowings);
            followers.setText(CountOfFollowers);
        }
    }

    public void following(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new followingserver()).start();
        followingclient followingclient=new followingclient(p, Controller.mainUser);
        if(followingclient.follow().equals("ok")){
            unsuccessfullable.setVisible(false);
            successfullabel.setVisible(true);
            (new count_of_followers_server()).start();
            countoffollowers_client countoffollowers_client=new countoffollowers_client(p.username);
            String CountOfFollowers=countoffollowers_client.count_of_followers();
            (new count_of_following_server()).start();
            countoffollowers_client countoffollowers_client2=new countoffollowers_client(p.username);
            String CountOfFollowings=countoffollowers_client2.count_of_following();
            followings.setText(CountOfFollowings);
            followers.setText(CountOfFollowers);
        }
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }
}
