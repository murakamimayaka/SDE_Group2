/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author llyxi
 */
public class Table extends Application{
    
    @FXML
    private TableColumn<Movie, Integer> movieid;

    @FXML
    private TableColumn<Movie, String> diretor;

    @FXML
    private TableColumn<Movie, String> mainActor;

    @FXML
    private TableColumn<Movie,Double> Price;

    @FXML
    private TableColumn<Movie,Integer> duration;

        private static Scene scene;
        String url = "jdbc:mysql://localhost:3306/movie";/*your database location*/
	String user = "root";/*user name*/
	String pwd = "nicai";/*password*/
	String jdbc = "com.mysql.cj.jdbc.Driver";
	ResultSet rst = null;
	Connection cont = null;
	Statement ppst = null;


    @Override
    public void start(Stage procedrue) throws Exception{
        //定义表格的行标
        TableView<Movie> table = new TableView();
        TableColumn movieid = new TableColumn("movieid");
        TableColumn moviename = new TableColumn("moviename");
        TableColumn diretor = new TableColumn("diretor");
        TableColumn mainActor = new TableColumn("mainActor");
        TableColumn Price=new TableColumn("Price");
        TableColumn duration=new TableColumn("duration");
        
        //表格列宽宽度设置
        movieid.setMinWidth(100);
        moviename.setMinWidth(100);
        diretor.setMinWidth(100);
        mainActor.setMinWidth(100);
        Price.setMaxWidth(100);
        duration.setMaxWidth(100);
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event){
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            try {                   
                Table.setRoot("detail");
            } catch (IOException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
});
        movieid.setCellValueFactory(new PropertyValueFactory("movieid"));
        moviename.setCellValueFactory(new PropertyValueFactory("moviename"));
        diretor.setCellValueFactory(new PropertyValueFactory("diretor"));
        mainActor.setCellValueFactory(new PropertyValueFactory("mainActor"));
        Price.setCellValueFactory(new PropertyValueFactory("Price"));
        duration.setCellValueFactory(new PropertyValueFactory("duration"));
        
        date(table,movieid,moviename,diretor,mainActor,Price,duration);
        table.getColumns().addAll(movieid,moviename,diretor,mainActor,Price,duration);
 
        scene = new Scene(table,400, 200);
        procedrue.setTitle("Table View Sample");
        procedrue.setScene(scene);
        procedrue.show();
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Table.class.getResource("/org.example/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
      public void date(TableView table,  TableColumn movieid,
		TableColumn moviename, TableColumn diretor, TableColumn mainActor,TableColumn Price,TableColumn duration) {
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
		//System.out.print("连接成功");
		while(rst.next()) {
			data.add(new         
             Movie(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(5),rst.getInt(6)));
			
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


    
  

}
