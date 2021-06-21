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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    Set<Post> allposts=new HashSet<>();
    Post newpost=new Post();
    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
            Post p = new Post();
            p.setTitle("post" + 1);
            p.setDescription("description" + 1);
            p.setPublisher("are");
            allposts.add(p);
        (new showingposts_server()).start();
        showingposts_client showingposts_client=new showingposts_client(Controller.mainUser);
        List<Post> post2=showingposts_client.posts();
        for (Post s:post2){
        allposts.add(s);}
        posts.clear();
        for (Post po:allposts){
            posts.add(po);
        }
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
            postingClient posting = new postingClient(newpost);
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
        List<Post> newones=new ArrayList<>();
        List<Post> array=showingposts_client.posts();
        for (Post s:array){
            for (Post p:posts){
             if(s.publisher.contains(p.publisher)&&s.title.contains(p.title)) {
                 continue;
               }else {
                 allposts.add(s);
             }
            }
        }
        posts.clear();
        for (Post p:allposts){
            posts.add(p);
        }
        postscript.setItems(FXCollections.observableArrayList(posts));
        postscript.setCellFactory(postList -> new PostItem());
    }

    public void allclients(ActionEvent actionEvent) throws IOException {
       new Loader().load("clientslist");

    }
}
