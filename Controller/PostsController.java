package Controller;

import Client.postingClient;
import Client.showingposts_client;
import Server.postingServer;
import Server.showingposts_server;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Loader;
import model.Post;
import model.PrivacyStatus;
import sample.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostsController {
    public TextArea describing;
    public TextField title;
    public Button publish;
    public ListView<Post> postscript;
    public MenuItem public_one;
    public MenuItem private_one;
    public MenuButton privacy;
    public Button menu;
    public Button refresh;
    public Button peaple;
    List<Post> posts=new ArrayList<>();
    Post newpost=new Post();
    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
            Post p = new Post();
            p.setTitle("post" + 1);
            p.setDescription("description" + 1);
            p.setPublisher("are");
            posts.add(p);
        (new showingposts_server()).start();
        showingposts_client showingposts_client=new showingposts_client(Controller.mainUser);
        for (Post s:showingposts_client.posts()){
        posts.add(s);}
        postscript.setItems(FXCollections.observableArrayList(posts));
        postscript.setCellFactory(postList -> new PostItem());
    }
    public void adding(javafx.event.ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        newpost.setTitle(title.getText());
        newpost.setDescription(describing.getText());
        newpost.setPublisher(Controller.mainUser);
        //save the post in arraylist
        posts.add(newpost);
        //show the arraylist in listview
        postscript.setItems(FXCollections.observableArrayList(posts));
        postscript.setCellFactory(postList -> new PostItem());
        (new postingServer()).start();
        postingClient posting=new postingClient(newpost);
        /*
        if the listview cells are not customized and list view is made of strings
        this code will add new post title to the list view
        postList.getItems().add(currentPost.getTitle());
         */
        if(posting.posting_connection().contains("ok")){
        newpost = new Post();
        //empty fields
        title.setText("");
        describing.setText("");}
    }

    public void makingpublic(ActionEvent actionEvent) {
        newpost.setPrivacyStatus(PrivacyStatus.PUBLIC);
    }

    public void makingprivate(ActionEvent actionEvent) {
        newpost.setPrivacyStatus(PrivacyStatus.PRIVATE);
    }

    public void showingpost(MouseEvent mouseEvent) {
        Post post=new Post();
       // post.setTitle(postscript.getSelectionModel().getSelectedItem().getTitle());
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).equals(post)) {
                title.setText(posts.get(i).getTitle());
                describing.setText(posts.get(i).getDescription());
            }
        }

    }

    public void menupage(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }

    public void refreshing(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new showingposts_server()).start();
        showingposts_client showingposts_client=new showingposts_client(Controller.mainUser);
        for (Post s:showingposts_client.posts()){
            posts.add(s);}
        postscript.setItems(FXCollections.observableArrayList(posts));
        postscript.setCellFactory(postList -> new PostItem());
    }

    public void allclients(ActionEvent actionEvent) throws IOException {
       new Loader().load("clientslist");

    }
}
