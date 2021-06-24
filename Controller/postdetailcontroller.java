package Controller;

import Client.*;
import Server.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Loader;
import model.Person;
import model.Post;
import sample.Controller;

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
    public Label postdate;

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        (new postdetails2_server2()).start();
        postdetails_client postdetails_client=new postdetails_client();
        post=postdetails_client.detail_get_info();
         title.setText(post.getTitle());
        description.setText(post.getDescription());
        username.setText(post.getPublisher());
        postdate.setText(post.date);
        Image image=new Image(post.postPicture);
        profile.setImage(image);
        (new getpersoninfo_server()).start();
        getpersoninfo_client getpersoninfo_client=new getpersoninfo_client();
        Person p=getpersoninfo_client.findingClientsinformation(post.publisher);
        person=p;
        (new count_of_likes_server()).start();
        count_of_likes col=new count_of_likes(post.publisher,post.title);
        String result=col.numoflikes();
        numoflike.setText(result);
        (new counts_of_reposts()).start();
        counts_of_reposts_client countsOfRepostsClient=new counts_of_reposts_client(post.publisher,post.title);
        String repost=countsOfRepostsClient.numofreposts();
        numofrepost.setText(repost);
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }

    public void liking(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        ( new like_server()).start();
        likerepost_client likerepost_client=new likerepost_client(post);
        if(likerepost_client.liking().equals("ok")){
            (new count_of_likes_server()).start();
            count_of_likes col=new count_of_likes(post.publisher,post.title);
            String result=col.numoflikes();
            numoflike.setText(result);
        }

    }
    public static Person getPerson() {
        return person;
    }

    public void reposting(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new repost_server()).start();
        likerepost_client likerepost_client=new likerepost_client(post, Controller.mainUser);
        if(likerepost_client.reposting().equals("ok")){
            (new counts_of_reposts()).start();
            counts_of_reposts_client countsOfRepostsClient=new counts_of_reposts_client(post.publisher,post.title);
            String repost=countsOfRepostsClient.numofreposts();
            numofrepost.setText(repost);
        }
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
        (new carryoverperson_give1_server()).start();
        carryoverperson_client  carryoverperson_client=new carryoverperson_client(person.username);
        if(carryoverperson_client.person_give_info().equals("ok")){
        new Loader().load("profile");}
    }
}
