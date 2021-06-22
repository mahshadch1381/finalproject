package Controller;

import Client.comments_client;
import Client.postdetails_client;
import Server.ReadingComments_Server;
import Server.SavingComments_server;
import Server.postdetails2_server2;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import model.Loader;
import model.Post;
import sample.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class commentsController {
    public TextArea description;
    public Button menubutton;
    public ListView<String> commentslist;
    public Button addingbutton;
    public List<String> list = new ArrayList<>();
    public Post post;

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        (new postdetails2_server2()).start();
        postdetails_client postdetails_client = new postdetails_client();
        post = postdetails_client.detail_get_info();
        list.add("pppppp");
        (new ReadingComments_Server()).start();
        comments_client comments_client = new comments_client(post.title);
        List<String> comments = comments_client.Reading_comments();
        for (String s : comments) {
            list.add(s);
        }

        commentslist.setItems(FXCollections.observableArrayList(list));
    }

    public void adding(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String comment = description.getText();
        (new SavingComments_server()).start();
        comments_client comments_client = new comments_client(Controller.mainUser, comment, post.title);
        if (comments_client.Saving_comments().equals("ok")) {
              String new_comment="username: "+Controller.mainUser+"  //   comment: "+comment;
                  commentslist.getItems().add(new_comment);
                  description.setText("");
        }
    }

    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }
}