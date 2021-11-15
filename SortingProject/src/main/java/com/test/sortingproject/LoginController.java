package com.test.sortingproject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private Text clkSignUp;
    @FXML
    private ImageView JavailPic;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
