package org.example;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Detail {

    @FXML
    private ImageView image;

    @FXML
    private Label name;

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
           Table.setRoot("Table");
    }

    @FXML
    void onBuy(ActionEvent event) {

    }

}
