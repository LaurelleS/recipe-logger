module com.example.recipebook {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.recipebook to javafx.fxml;
    exports com.example.recipebook;
}