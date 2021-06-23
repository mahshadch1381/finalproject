package Controller;

import Client.followingfiles_client;
import Server.followingfiles_server;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Loader;
import model.Person;
import model.Post;

import java.io.IOException;

public class personitemcontroller {
    public AnchorPane root;
    public ImageView picture;
    public Label userlabel;
    public Button details;
    public  Person person;
    public static Person pe;

    public personitemcontroller(Person person) throws IOException {
        new Loader().load("personitem", this);
        this.person=person;
        pe=person;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        userlabel.setText(person.username);
        pe=person;
        Image image=new Image(person.profilePath);
        picture.setImage(image);
        //set another image dynamically
        // if (post.getPublisher().equals("ali alavi"))
        //  profileImage.setImage(new Image(Paths.get("images/ali_alavi.jpg").toUri().toString()));
        return root;
    }

    public static Person getPerson() {
        return pe;

    }

    //you can show post's detail in new page with this method
    public void detail(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new followingfiles_server()).start();
        followingfiles_client followingfiles_client=new followingfiles_client(person);
         if(followingfiles_client.following_give_info().equals("ok")){
        new Loader().load("profileforfollowing");}
    }
}

