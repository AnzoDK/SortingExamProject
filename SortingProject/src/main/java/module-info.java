module com.test.sortingproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.test.sortingproject to javafx.fxml;
    exports com.test.sortingproject;
}
