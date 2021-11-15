package com.test.sortingproject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;

public class LoginController {

    @FXML
    private Text clkSignUp;

    @FXML
    private void switchToSecondary() throws IOException, SQLException, NoSuchAlgorithmException {
        if(DBManager.INSTANCE.Login("anders","anders"))
        {
            System.out.println("What?");
        }
        else
        {
            System.out.println("No Dice");
        }
        //App.setRoot("secondary");
    }
}
