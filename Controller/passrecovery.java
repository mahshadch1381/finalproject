package Controller;

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

    public void recovering(ActionEvent actionEvent) {
        String answer=color.getText();
        for (Person p: Initialize.people){
            if(answer.equalsIgnoreCase(p.accidentalQuestion)){
                result.setText("your password :"+p.password);
                result.setVisible(true);
                break;
            }
        }
    }

    public void loging(ActionEvent actionEvent) throws IOException {
        new Loader().load("sample");
    }
}
