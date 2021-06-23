package Controller;

import javafx.scene.control.ListCell;
import model.Post;

import java.io.IOException;

public class PostItem extends ListCell<Post> {

    @Override
    public void updateItem(Post post, boolean empty) {
        super.updateItem(post, empty);
        if (post != null) {
            try {
                setGraphic(new PostItemController(post).init());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

