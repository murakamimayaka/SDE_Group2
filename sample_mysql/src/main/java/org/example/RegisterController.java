/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author llyxi
 */
public class RegisterController implements Initializable {

     @FXML
    private ImageView shieldImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;

    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){
       
        
    

    }
    @FXML
    public void registerButtonOnAction(ActionEvent event) throws SQLException{
       
        if (setPasswordField.getText().isEmpty()) {
            System.out.println("asd");
            return;
        }
       if(confirmPasswordField.getText() == null ? setPasswordField.getText() != null : !confirmPasswordField.getText().equals(setPasswordField.getText())){
           System.out.println("no same");
       return;
       }
        String userId = usernameTextField.getText();
        String fname=firstnameTextField.getText();
        String lname=lastnameTextField.getText();
        String password = confirmPasswordField.getText();

        RegisterDao jdbcDao = new RegisterDao();
        jdbcDao.insertdate(fname, lname, userId, password);
    }

    @FXML
    void closeButton(ActionEvent event) throws IOException {
        Main.setRoot("login");
    }

    public void registerUser(){
       DatabaseConnecton connectNow = new DatabaseConnecton();
       Connection connectDB = connectNow.getConnection();

       String firstname = firstnameTextField.getText();
       String lastname = lastnameTextField.getText();
       String username = usernameTextField.getText();
       String password = setPasswordField.getText();

       String insertFields = "INSERT INTO user_account(lastname, firstname, username, password) VALUES (' ";
       String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "')";
       String insertToRegister = insertFields + insertValues;

       try{
           Statement statement = connectDB.createStatement();
           statement.executeUpdate(insertToRegister);

           registrationMessageLabel.setText("User has been registered successfully!");

       }catch (Exception e){
           e.printStackTrace();
           e.getCause();
       }

       
    }
}
