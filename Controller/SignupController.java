package Controller;

import Client.signup_client;
import Server.loginServer;
import Server.signupServer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import model.Loader;
import model.Person;

import java.io.File;
import java.io.IOException;

public class SignupController {
    public TextField username;
    public Button signupbutton;
    public Button loginbutton;
    public TextField password;
    public Label correct;
    public Label wrong;
    public boolean ok=false;
    public Label unacceptable;
    public ImageView profile;
    public TextField name;
    public TextField country;
    public TextField favoriteColor;
    public Button choosebutton;
    public String photo;

    public void signing(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String user=username.getText();
        String pass=password.getText();
        String country_person=country.getText();
        String name_person=name.getText();
        String color=favoriteColor.getText();
        if(passwordchecking(pass)==false){
          unacceptable.setVisible(true);
        }
        else {
            unacceptable.setVisible(false);
           // (new signupServer()).start();
            signup_client signup_client = new signup_client(user, pass);
            Person p=new Person(user,pass);
            p.setCountry(country_person);
            p.setName(name_person);
            p.setAccidentalQuestion(color);
            p.profilePath=photo;
            if (signup_client.signup_connection(p).equals("true")) {
                wrong.setVisible(false);
                correct.setVisible(true);
            } else {
                correct.setVisible(false);
                wrong.setVisible(true);
            }
        }
    }

    public void login(ActionEvent actionEvent) throws IOException {
        new Loader().load("sample");
    }
    public boolean passwordchecking(String s){
        boolean length = false,character=false,number=false;
        if(s.length()>=8){
            length=true;
        }
        for (int i=0;i<10;i++){
            String c=i+"";
            if(s.contains(c)){
                number=true;
                break;
            }
        }
        for (int i=65;i<91;i++){
            char c=(char) i;
            String s2=c+"";
            if(s.contains(s2)){
                character=true;
                break;
            }
        }
        for (int i=97;i<123;i++){
            char c=(char) i;
            String s2=c+"";
            if(s.contains(s2)){
                character=true;
                break;
            }
        }
        if(length&&character&&number){
            return true;
        }
        return false;

    }

    public void choosing(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(new Popup());
        Image image=new Image(file.toURI().toString());
        profile.setImage(image);
        photo=file.toURI().toString();
    }
}
