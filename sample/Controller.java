package sample;

import Client.findmainprofile;
import Client.login_client;
import Server.findprofile_server;
import Server.loginServer;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Loader;

import java.io.IOException;

public class Controller {

    public Button log;
    public Button sign;
    public TextField username;
    public PasswordField password;
    public Label label;
    public TextField visibleusername;
    public Button forgetpass;
    public static String mainUser;
    public void login(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String user=username.getText();
        String pass=password.getText();
        login_client login_client=new login_client(user,pass);
       // (new loginServer()).start();
        if(login_client.login_connection().equalsIgnoreCase("true")){
            label.setVisible(false);
            mainUser=user;
            new Loader().load("posts");

        }else {
            label.setVisible(true);
        }
    }

    public void showing(ActionEvent actionEvent) {
        if(visibleusername.isVisible()==false){
            visibleusername.setVisible(true);
            password.setVisible(false);
            visibleusername.setText(password.getText());
        }
        else {
            visibleusername.setVisible(false);
            password.setVisible(true);
            password.setText(visibleusername.getText());
        }
    }

    public void enroll(ActionEvent actionEvent) throws IOException {
        new Loader().load("signup");
    }

    public void Recovery(ActionEvent actionEvent) throws IOException {
        new Loader().load("recoverypassword");
    }
    public static String getmainprofile() throws IOException, ClassNotFoundException {
        //(new findprofile_server()).start();
        findmainprofile findmainprofile=new findmainprofile(mainUser);
        String pro=findmainprofile.findprofile();
        return pro;
    }
}
