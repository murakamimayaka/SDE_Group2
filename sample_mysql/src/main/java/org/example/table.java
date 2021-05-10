/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example;

import java.io.IOException;
import java.net.URL;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author llyxi
 */
public class Table implements Initializable{
    @FXML
    public TableView table;
    @FXML
    private TableColumn<Movie, Integer> movieid;

    @FXML
    private TableColumn<Movie, String> diretor;
 @FXML
    private TableColumn<Movie, String> moviename;

    @FXML
    private TableColumn<Movie, String> mainActor;

    @FXML
    private TableColumn<Movie,Double> Price;

    @FXML
    private TableColumn<Movie,Integer> duration;
      @FXML
    private TableColumn<Movie, String> image;
      @FXML
    private Button logout;

    @FXML
    void Logout(ActionEvent event) {
          Main.setRoot("login");
    }

       
        String url = "jdbc:mysql://localhost:3306/movie";/*your database location*/
	String user = "root";/*user name*/
	String pwd = "nicai";/*password*/
	String jdbc = "com.mysql.cj.jdbc.Driver";
	ResultSet rst = null;
	Connection cont = null;
	Statement ppst = null;


    
    public void start() throws Exception{
        
       
       
        
      
       
        movieid.setCellValueFactory(new PropertyValueFactory("movieid"));
        moviename.setCellValueFactory(new PropertyValueFactory("moviename"));
        diretor.setCellValueFactory(new PropertyValueFactory("diretor"));
        mainActor.setCellValueFactory(new PropertyValueFactory("mainActor"));
        Price.setCellValueFactory(new PropertyValueFactory("Price"));
        duration.setCellValueFactory(new PropertyValueFactory("duration"));
        image.setCellValueFactory(new PropertyValueFactory("image"));
         table.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event){
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            try {
                showCustomerDialog((Movie) table.getSelectionModel().getSelectedItem());
                System.out.println(table.getSelectionModel().getSelectedItem());
              
            } catch (IOException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
});
       
        
        
 try {
		Class.forName(jdbc);
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	String sql4 = "SELECT * FROM movielist";
	ObservableList<Movie> data = FXCollections.observableArrayList();
	try {
		cont = DriverManager.getConnection(url, user, pwd);
		ppst = cont.createStatement();
		rst = ppst.executeQuery(sql4);
		System.out.print("连接成功");
		while(rst.next()) {
			data.add(new         
             Movie(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(5),rst.getDouble(6),rst.getString(7),rst.getInt(8),rst.getString(9),rst.getInt(10)));
			
			table.setItems(data);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(cont != null && ppst != null && rst != null) {
			try {
				cont.close();
				ppst.close();
				rst.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

    }
    
      public Stage showCustomerDialog(Movie a) throws IOException {
  FXMLLoader loader = new FXMLLoader(
    getClass().getResource(
      "/org.example/detail.fxml"
    )
  );

  Stage stage = new Stage(StageStyle.DECORATED);
  stage.setScene(
    new Scene(loader.load())
  );

  Detail controller = loader.getController();
  controller.initData(a);

  stage.show();

  return stage;
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
          
            start();
            
        } catch (Exception ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
  

}
