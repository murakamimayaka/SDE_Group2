package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Detail implements Initializable{

    @FXML
    private ImageView image;

    @FXML
    Label name;

    @FXML
    private Label derector;

    @FXML
    private Label actor;

    @FXML
    private Label price;

    @FXML
    private Label duration;
    
    @FXML
    private Button Buy;

    @FXML
    private Button Back;
    @FXML
    void onBack(ActionEvent event) throws IOException {
           Main.setRoot("Table");
    }

    @FXML
    void onBuy(ActionEvent event) {

    }
    void initData(Movie a) {
    name.setText(a.getMoviename());
    derector.setText(a.getDiretor());
    actor.setText(a.getMainActor());
    price.setText(a.getPrice()+"");
    duration.setText(a.getDuration()+"");
    if(a.getImage()!=null){
        Image imageObject = new Image(a.getImage()+"");
    image.setImage(imageObject);
    }
  }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
