package Controller;

import javafx.scene.control.ListCell;
import model.Person;
import model.Post;

import java.io.IOException;

public class personitem extends ListCell<Person> {

    @Override
    public void updateItem(Person person, boolean empty) {
        super.updateItem(person, empty);
        if (person != null) {
            try {
                setGraphic(new personitemcontroller(person).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
