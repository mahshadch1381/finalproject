package Controller;

import Client.*;
import Server.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Loader;
import model.Person;
import model.Post;
import sample.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public Label editwarning;
    public ListView postslist;
    public List<Post> clientsPosts=new ArrayList<>();
    public ImageView image;

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        person=postdetailcontroller.getPerson();
        user.setText(person.username);
        country.setText(person.country);
        Image image55=new Image(person.profilePath);
        image.setImage(image55);
        (new count_of_followers_server()).start();
        countoffollowers_client countoffollowers_client=new countoffollowers_client(person.username);
        String CountOfFollowers=countoffollowers_client.count_of_followers();
        (new count_of_following_server()).start();
        countoffollowers_client countoffollowers_client2=new countoffollowers_client(person.username);
        String CountOfFollowings=countoffollowers_client2.count_of_following();
        numoffolloing.setText(CountOfFollowings);
        numoffollowers.setText(CountOfFollowers);
        (new personposts_server()).start();
        personspost personspost=new personspost(person.username);
        List<Post> posts=personspost.posts();
        if(posts.size()>0){
        for(Post p:posts){
            clientsPosts.add(p);
        }
        postslist.setItems(FXCollections.observableArrayList(clientsPosts));
        postslist.setCellFactory(postList -> new PostItem());}
    }

    public void following(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new followingserver()).start();
        followingclient followingclient = new followingclient(person, Controller.mainUser);
        if (followingclient.follow().equals("ok")) {
            (new count_of_followers_server()).start();
            countoffollowers_client countoffollowers_client = new countoffollowers_client(person.username);
            String CountOfFollowers = countoffollowers_client.count_of_followers();
            numoffollowers.setText(CountOfFollowers);
        }
    }
    public void unfollowing(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
            (new unfollowingserver()).start();
            unfollowing_client unfollowingclient = new unfollowing_client(person, Controller.mainUser);
            if (unfollowingclient.unfollow().equals("ok")) {
                (new count_of_followers_server()).start();
                countoffollowers_client countoffollowers_client = new countoffollowers_client(person.username);
                String CountOfFollowers = countoffollowers_client.count_of_followers();
                numoffollowers.setText(CountOfFollowers);
            }

        }

    public void editing(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if(!Controller.mainUser.equals(person.username)){
            editwarning.setVisible(true);
        }else {
            (new carryoverperson_give1_server()).start();
            carryoverperson_client carryoverperson_client=new carryoverperson_client(Controller.mainUser);
            if(carryoverperson_client.person_give_info().equals("ok")){
                new Loader().load("edit");
            }
        }
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }

}
