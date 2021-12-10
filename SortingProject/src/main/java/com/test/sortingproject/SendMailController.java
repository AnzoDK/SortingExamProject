/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.test.sortingproject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;





/**
 * FXML Controller class
 *
 * @author anzo
 */
public class SendMailController implements Initializable {
@FXML
Button sendBtn;

@FXML
TextField headerField;

@FXML
TextField sendToField;

@FXML
TextArea messageArea;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void SendMail() throws IOException
    {
        
        if(DBManager.INSTANCE.HasUser(sendToField.getText()))
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:HH:mm:ss");
            Mail m = new Mail(headerField.getText(),Holder.INSTANCE.username,sendToField.getText(),LocalDateTime.now().format(formatter),messageArea.getText());
            DBManager.INSTANCE.AddMail(m);
            App.setRoot("Mail");
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("No Such User");
            a.setContentText("There is no user with that mailaddress");
            a.showAndWait();
        }
    }
    
}
