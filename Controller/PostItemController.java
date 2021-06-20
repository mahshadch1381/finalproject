package Controller;

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
    public Button repost;
    public Button details;
    public static Post post;

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

    public Post getPost() {
        return post;
    }

    //you can show post's detail in new page with this method
    public void detail(ActionEvent actionEvent) throws IOException {
       // postdetailcontroller p=new postdetailcontroller(post);
        new Loader().load("postdetail");
    }
}
