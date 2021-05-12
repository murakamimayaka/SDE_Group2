/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author llyxi
 */
public class Main extends Application{
     private static Scene scene;
     
     @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        
        stage.show();
    }
    public static void main(String[] args) {
launch(args);
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Table.class.getResource("/org.example/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
