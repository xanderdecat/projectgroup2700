module com.example.projectgroup27 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projectgroup27 to javafx.fxml;
    exports com.example.projectgroup27;
}