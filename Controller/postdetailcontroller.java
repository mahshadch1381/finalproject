package Controller;

import Client.getpersoninfo_client;
import Client.postdetails_client;
import Server.getpersoninfo_server;
import Server.postdetail_server;
import Server.postdetails2_server2;
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
    public static Person person;
    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        (new postdetails2_server2()).start();
        postdetails_client postdetails_client=new postdetails_client();
        post=postdetails_client.detail_get_info();
         title.setText(post.getTitle());
        description.setText(post.getDescription());
        username.setText(post.getPublisher());
        (new getpersoninfo_server()).start();
        getpersoninfo_client getpersoninfo_client=new getpersoninfo_client();
        Person p=getpersoninfo_client.findingClientsinformation(post.publisher);
        person=p;
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }

    public void liking(ActionEvent actionEvent) {
    }

    public static Person getPerson() {
        return person;
    }

    public void reposting(ActionEvent actionEvent) {
    }

    public void visitingcomments(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new postdetail_server()).start();
        postdetails_client postdetails_client=new postdetails_client(post);
        if(postdetails_client.detail_give_info().equals("ok")){
            new Loader().load("comments");}
    }

    public void addingcomments(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new postdetail_server()).start();
        postdetails_client postdetails_client=new postdetails_client(post);
        if(postdetails_client.detail_give_info().equals("ok")){
            new Loader().load("comments");}
    }

    public void visitingprofile(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new postdetail_server()).start();
        postdetails_client postdetails_client=new postdetails_client(post);
        if(postdetails_client.detail_give_info().equals("ok")){
        new Loader().load("profile");}
    }
}
