package com.test.sortingproject;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimaryy() throws IOException {
        App.setRoot("Login");
    }
}