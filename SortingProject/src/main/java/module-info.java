module com.test.sortingproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    opens com.test.sortingproject to javafx.fxml;
    exports com.test.sortingproject;
}
