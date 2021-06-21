package Controller;

import Client.likerepost_client;
import Client.postdetails_client;
import Server.like_server;
import Server.postdetail_server;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Loader;
import model.Post;

import java.io.IOException;
import java.nio.file.Paths;

public class PostItemController {
   /* public Label username;
    public ImageView profile;
   public Post post;
    public Label title;
    public AnchorPane root;
    public PostItemController(Post post) throws IOException {
        new Loader().load("PostItem",this);
        this.post=post;
    }
    public AnchorPane init(){
        username.setText(post.getPublisher());
        title.setText(post.getTitle());
        return root;*/

    public AnchorPane root;
    public ImageView profileImage;
    public Label username;
    public Label title;
    public Label likedlabel;
    public Button repost;
    public Button details;
    public Post post;
    public Button like;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new Loader().load("postItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        username.setText(post.getPublisher());
        title.setText(post.getTitle());

        //set another image dynamically
       // if (post.getPublisher().equals("ali alavi"))
          //  profileImage.setImage(new Image(Paths.get("images/ali_alavi.jpg").toUri().toString()));
        return root;
    }
    //you can show post's detail in new page with this method
    public void detail(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        (new postdetail_server()).start();
        postdetails_client postdetails_client=new postdetails_client(post);
        if(postdetails_client.detail_give_info().equals("ok")){
        new Loader().load("postdetail");}
    }

    public void reposting(ActionEvent actionEvent){

    }
    public void liking(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        ( new like_server()).start();
        likerepost_client likerepost_client=new likerepost_client(post);
        if(likerepost_client.liking().equals("ok")){
            likedlabel.setVisible(true);
        }
    }
}
