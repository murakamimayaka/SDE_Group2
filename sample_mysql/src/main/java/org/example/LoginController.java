package org.example;

import com.mysql.cj.xdevapi.Result;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
//import jdk.javadoc.internal.tool.Main;

//import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import sample.DatabaseConnecton;

public class LoginController implements Initializable {
    @FXML
    private ImageView brandingImageView;

    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("images/LMA.JPG");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("images/lock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
        }


    public void loginButtonAction(ActionEvent event){
        loginMessageLabel.setText("You try to login");
        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();
        }else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void cancelButtonOnActionn(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void onLogin(ActionEvent event) throws SQLException, IOException {
        

        System.out.println(usernameTextField.getText());
        System.out.println(enterPasswordField.getText());

        if (usernameTextField.getText().isEmpty()) {
            
            return;
        }
        if (enterPasswordField.getText().isEmpty()) {
            
            return;
        }

        String userId = usernameTextField.getText();
        String password = enterPasswordField.getText();

        Logindao jdbcDao = new Logindao();
        boolean flag = jdbcDao.validate(userId, password);

        if (!flag) {
            /*infoBox("Please enter correct Email and Password", null, "Failed")*/
            
        } else {
            Main.setRoot("Table");
        }

    }
@FXML
    void onRegister(ActionEvent event) throws IOException {
Main.setRoot("register");
    }

    public void validateLogin(){
        DatabaseConnecton connectNow = new DatabaseConnecton();
        Connection connectDB = connectNow.getConnection();

        String veryfyLogin = "SELECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() + "' AND passowrd = '" + enterPasswordField.getText() +  "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(veryfyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Congradurations!");
                }else {
                    loginMessageLabel.setText("Invalid login. Please try again.");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
