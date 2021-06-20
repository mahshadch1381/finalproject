package Controller;

import Client.clientlist_client;
import Server.clientlist_server;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Person;

import java.io.IOException;
import java.util.List;

public class ClientListController {

    public ListView list;
    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        (new clientlist_server()).start();
        clientlist_client client2=new clientlist_client();
        List<Person> people=client2.findingClientList();
            list.setItems(FXCollections.observableArrayList(people));
            //customize each cell of postList with new graphic object PostItem
            list.setCellFactory(postList -> new PostItem());
    }
}
