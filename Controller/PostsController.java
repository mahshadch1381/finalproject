package Controller;

import Client.postingClient;
import Client.showingposts_client;
import Server.postingServer;
import Server.showingposts_server;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import model.Loader;
import model.Post;
import model.PrivacyStatus;
import sample.Controller;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

public class PostsController {
    public Button menu;
    public Button refresh;
    public TextArea describing;
    public TextField title;
    public ImageView picture;
    public Button publish;
    public Button chossingpicture;
    public MenuButton privacy;
    public MenuItem public_one;
    public MenuItem private_one;
    public ListView postscript;
    public Button peaple;
    public String postpicture;
    List<Post> posts = new ArrayList<>();
    List<Post> allposts = new ArrayList<>();
    Post newpost = new Post();

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        (new showingposts_server()).start();
        showingposts_client showingposts_client=new showingposts_client(Controller.mainUser);
        List<Post> post2=showingposts_client.posts();
        if(post2.size()>0){
            for (int i=post2.size()-1;i>=0;i--){
                posts.add(post2.get(i));}
        }

        postscript.setItems(FXCollections.observableArrayList(posts));
        postscript.setCellFactory(postList -> new PostItem());
    }

    public void adding(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        newpost.setTitle(title.getText());
        newpost.setDescription(describing.getText());
        newpost.setPublisher(Controller.mainUser);
        newpost.postPicture=postpicture;
        newpost.profile=Controller.getmainprofile();
        newpost.setDateString(new Date());
        newpost.time_date= Instant.now().toEpochMilli();
        //save the post in arraylist
        posts.add(newpost);
        //show the arraylist in listview
        postscript.setItems(FXCollections.observableArrayList(posts));
        postscript.setCellFactory(postList -> new PostItem());
        (new postingServer()).start();
        postingClient posting = new postingClient(newpost,Controller.mainUser);
        if (posting.posting_connection().contains("ok")) {
            newpost = new Post();
            //empty fields
            title.setText("");
            describing.setText("");
        }
    }

    public void makingpublic(ActionEvent actionEvent) {
        newpost.setPrivacyStatus(PrivacyStatus.PUBLIC);
    }

    public void makingprivate(ActionEvent actionEvent) {
        newpost.setPrivacyStatus(PrivacyStatus.PRIVATE);
    }

    public void menupage(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }

    public void refreshing(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        posts.clear();
        initialize();
    }

    public void allclients(ActionEvent actionEvent) throws IOException {
        new Loader().load("clientslist");
    }

    public void choosing(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(new Popup());
        Image image=new Image(file.toURI().toString());
        picture.setImage(image);
        postpicture=file.toURI().toString();
    }
}

