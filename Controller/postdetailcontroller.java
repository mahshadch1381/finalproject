package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Loader;
import model.Person;
import model.Post;

import java.io.IOException;

public class postdetailcontroller {
    public Label title;
    public Label description;
    public Label username;
    public Button menubutton;
    public ImageView profile;
    public Post post;
    public Button likebutton;
    public Button repostbutton;
    public Button visitcommentsbutton;
    public Button addcommentbutton;
    public Button checkprofilebutton;
    public Label numoflike;
    public Label numofrepost;
    public Person person;
    @FXML
    public void initialize(){
        post=PostItemController.post;
         title.setText(post.getTitle());
        description.setText(post.getDescription());
        username.setText(post.getPublisher());
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }

    public void liking(ActionEvent actionEvent) {
    }

    public void reposting(ActionEvent actionEvent) {
    }

    public void visitingcomments(ActionEvent actionEvent) {
    }

    public void addingcomments(ActionEvent actionEvent) {
    }

    public void visitingprofile(ActionEvent actionEvent) throws IOException {
        new Loader().load("profile");
    }
}
