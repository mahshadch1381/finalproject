package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Loader {
    public static Stage stage;
    public static Scene scene;
    public static void StageLoader(Stage primaryStage){
        stage=primaryStage;
        stage.setTitle("SBU GRAM");
       // stage.setWidth(400);
       // stage.setHeight(600);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
    }
    public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Stage getPrimaryStage() {
        return stage;
    }

    public Parent loadFXML(String fxml)throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("/sample/"+fxml+".fxml"));
        return fxmlLoader.load();
    }
    public void load(String url)throws IOException{
    scene=new Scene(new Loader().loadFXML(url));
    stage.setScene(scene);
    stage.show();
    }
    public void load(String url,Object controller) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("/sample/"+url+".fxml"));
        fxmlLoader.setController(controller);
        fxmlLoader.load();
    }
}
