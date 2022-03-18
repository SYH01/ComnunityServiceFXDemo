module com.example.logindemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example to javafx.fxml;
    exports com.example;
    exports com.example.Controller;
    opens com.example.Controller to javafx.fxml;
    opens com.example.DB.Table to javafx.base;
}