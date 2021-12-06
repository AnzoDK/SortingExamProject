package com.test.sortingproject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.sql.SQLException;
import javafx.scene.control.*;
import java.security.NoSuchAlgorithmException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;

public class LoginController {

    @FXML
    private Text clkSignUp;
    @FXML
    private ImageView JavailPic;
    
    @FXML
    TextField txEmail;
    
    @FXML
    PasswordField passField;
    
    @FXML
    Button btnLogin;
    
    @FXML
    private void switchToSignUp() throws IOException, SQLException, NoSuchAlgorithmException {
        App.setRoot("SignUp");
    }
    
    @FXML
    void Login() throws IOException, SQLException, NoSuchAlgorithmException
    {
        if(DBManager.INSTANCE.Login(txEmail.getText(), passField.getText()))
        {
            App.setRoot("Mail");
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Failed To Login");
            a.setContentText("Check your login details");
            a.showAndWait();
        }
    }
}