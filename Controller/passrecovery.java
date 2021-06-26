package Controller;

import Client.recoverypass_client;
import Server.recoverypass_server;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Initialize;
import model.Loader;
import model.Main;
import model.Person;

import java.io.IOException;

public class passrecovery {

    public Label result;
    public TextField color;
    public Button logging;
    public Button recover;
    public TextField username;

    public void recovering(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String answer=color.getText();
        String user=username.getText();
        //(new recoverypass_server()).start();
        recoverypass_client recoverypass_client=new recoverypass_client(user,answer);
        String pass=recoverypass_client.recovering();
        result.setText(pass);
        result.setVisible(true);
    }
    public void loging(ActionEvent actionEvent) throws IOException {
        new Loader().load("sample");
    }
}
