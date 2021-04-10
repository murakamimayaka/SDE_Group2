/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author llyxi
 */
public class table extends Application{
        String url = "jdbc:mysql://localhost:3306/movie";/*your database location*/
	String user = "";/*user name*/
	String pwd = "";/*password*/
	String jdbc = "com.mysql.cj.jdbc.Driver";
	ResultSet rst = null;
	Connection cont = null;
	Statement ppst = null;


    @Override
    public void start(Stage procedrue) throws Exception{
      
        TableView<Movie> table = new TableView();
        TableColumn movieid = new TableColumn("movieid");
        TableColumn moviename = new TableColumn("moviename");
        TableColumn diretor = new TableColumn("diretor");
        TableColumn mainActor = new TableColumn("mainActor");
        TableColumn Price=new TableColumn("Price");
        TableColumn duration=new TableColumn("duration");
        
      
        movieid.setMinWidth(100);
        moviename.setMinWidth(100);
        diretor.setMinWidth(100);
        mainActor.setMinWidth(100);
        Price.setMaxWidth(100);
        duration.setMaxWidth(100);
        
        movieid.setCellValueFactory(new PropertyValueFactory("movieid"));
        moviename.setCellValueFactory(new PropertyValueFactory("moviename"));
        diretor.setCellValueFactory(new PropertyValueFactory("diretor"));
        mainActor.setCellValueFactory(new PropertyValueFactory("mainActor"));
        Price.setCellValueFactory(new PropertyValueFactory("Price"));
        duration.setCellValueFactory(new PropertyValueFactory("duration"));
        
        date(table,movieid,moviename,diretor,mainActor,Price,duration);
        table.getColumns().addAll(movieid,moviename,diretor,mainActor,Price,duration);
 
        Scene scene = new Scene(table,400, 200);
        procedrue.setTitle("Table View Sample");
        procedrue.setScene(scene);
        procedrue.show();
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
		//System.out.print("connected");
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
