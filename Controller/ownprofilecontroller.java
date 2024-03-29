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
import model.Loader;
import model.Person;
import model.Post;
import sample.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ownprofilecontroller {
    public ImageView image;
    public Label username;
    public Label country;
    public Label followers;
    public Label followings;
    public Button editbutton;
    public Button menu;
    public Person person;
    public ListView postslist;
    public List<Post> clientsPosts=new Vector<>();
    public Button deletebutton;

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        //(new getpersoninfo_server()).start();
        getpersoninfo_client getpersoninfoClient=new getpersoninfo_client();
        person=getpersoninfoClient.findingClientsinformation(Controller.mainUser);
        username.setText(person.username);
        country.setText(person.country);
        Image image55=new Image(Controller.getmainprofile());
        image.setImage(image55);
        //(new count_of_followers_server()).start();
        countoffollowers_client countoffollowers_client=new countoffollowers_client(person.username);
        String CountOfFollowers=countoffollowers_client.count_of_followers();
        //(new count_of_following_server()).start();
        countoffollowers_client countoffollowers_client2=new countoffollowers_client(person.username);
        String CountOfFollowings=countoffollowers_client2.count_of_following();
        followers.setText(CountOfFollowers);
        followings.setText(CountOfFollowings);
        //(new personposts_server()).start();
        personspost personspost=new personspost(person.username);
        List<Post> posts=personspost.posts();
        if(posts.size()>0){
            for(Post p:posts){
                clientsPosts.add(p);
            }
            postslist.setItems(FXCollections.observableArrayList(clientsPosts));
            postslist.setCellFactory(postList -> new PostItem());}

    }
    public void editing(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
       // (new carryoverperson_give1_server()).start();
        carryoverperson_client carryoverperson_client=new carryoverperson_client(Controller.mainUser);
        if(carryoverperson_client.person_give_info().equals("ok")){
            new Loader().load("edit");
        }
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }

    public void deleting(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        //(new deletingacount_server()).start();
        deletingacount_client dac=new deletingacount_client(Controller.mainUser);
         if (dac.deleting().equals("ok")){
             new Loader().load("sample");
         }
    }
}
