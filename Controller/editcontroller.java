package Controller;

import Client.carryoverperson_client;
import Client.edit_client;
import Server.carryoverperson_get1_server;
import Server.edit_server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Loader;

import java.awt.*;
import java.io.IOException;

public class editcontroller {
    public Label username;
    public Label password;
    public Label oldname;
    public Label oldcountry;
    public Label oldpicture;
    public TextField newname;
    public TextField newcountry;
    public TextField newpicture;
    public TextField newphone;
    public Button savebutton;
    public Button menubutton;
    public Label correct;
    public String name;
    public String country;
    public String picture;
    public String phone;
    public ImageView profilepicture;
     public String user;

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        //(new carryoverperson_get1_server()).start();
        carryoverperson_client carryoverperson_client=  new carryoverperson_client();
        String client=carryoverperson_client.person_get_info();
        String[] info=client.split("#");
        username.setText(info[2]);
        password.setText(info[3]);
        oldname.setText(info[0]);
        oldcountry.setText(info[1]);
        oldpicture.setText(info[4]);
        Image image=new Image(info[4]);
        profilepicture.setImage(image);
        user=info[2];

    }
    public void saving(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        name=newname.getText();
        country=newcountry.getText();
        picture=newpicture.getText();
        phone=newphone.getText();
        String s=user+"%";
        if(name.length()!=0){
            s=s+"name:"+name;
        }
        if (name.length()==0){
            s=s+"-";
        }
        if(country.length()!=0){
            s=s+"#"+"country:"+country;
        }
        if(country.length()==0){
            s=s+"#"+"-";
        }
        if(picture.length()!=0){
            s=s+"#"+"picture:"+picture;
        }
        if (picture.length()==0){
            s=s+"#"+"-";
        }
        (new edit_server()).start();
        edit_client edit_client=new edit_client(s);
        if(edit_client.editing().equals("ok")){
            correct.setVisible(true);
        }else {

        }
    }
    public void gotomenu(ActionEvent actionEvent) throws IOException {
        new Loader().load("menu");
    }
}
