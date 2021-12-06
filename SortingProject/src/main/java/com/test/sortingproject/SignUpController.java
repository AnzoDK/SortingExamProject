package com.test.sortingproject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SignUpController {

    @FXML
    Button switchToLoginBtn;
 
    @FXML
    Button signUpBtn;
    
    @FXML
    PasswordField passOrigField;
    
    @FXML
    PasswordField passConfirmField;
    
    @FXML
    TextField userField;
    
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    void SignUp()
    {
        String username = userField.getText()+"@javamail.com";
        if(passOrigField.getText().equals(passConfirmField.getText()))
        {
            DBManager.LoginError err = DBManager.INSTANCE.Register(username,passOrigField.getText());
            if(err == DBManager.LoginError.SUCCESS)
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Sign Up Succeeded!");
                a.setContentText("Sign Up Complete!");
                a.showAndWait();
                try {
                    switchToLogin();
                } catch (IOException ex) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                if(err == DBManager.LoginError.USERNAME_IN_USE)
                {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Sign Up Failed!");
                    a.setContentText("Username already Taken");
                    a.showAndWait();
                }
                else
                {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Sign Up Failed!");
                    a.setContentText("SQL ERROR!!");
                    a.showAndWait();
                }
            }
        }
        else
        {
           Alert a = new Alert(Alert.AlertType.ERROR);
           a.setTitle("Sign Up Failed!");
           a.setContentText("Passwords Do Not Match!");
           a.showAndWait();
        }
    }
}