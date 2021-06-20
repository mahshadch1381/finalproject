package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Loader;
import model.Person;
import model.Post;

import java.io.IOException;

public class personitemcontroller {
    public AnchorPane root;
    public ImageView picture;
    public Label userlabel;
    public Button details;
    public static Person person;

    public personitemcontroller(Person person) throws IOException {
        new Loader().load("personitem", this);
        this.person=person;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        userlabel.setText(person.username);
        //set another image dynamically
        // if (post.getPublisher().equals("ali alavi"))
        //  profileImage.setImage(new Image(Paths.get("images/ali_alavi.jpg").toUri().toString()));
        return root;
    }

    public Person getPerson() {
        return person;
    }

    //you can show post's detail in new page with this method
    public void detail(ActionEvent actionEvent) throws IOException {
        new Loader().load("profileforfollowing");
    }
}

