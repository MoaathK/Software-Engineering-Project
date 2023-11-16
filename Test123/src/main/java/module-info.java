module com.example.test123 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires jfxrt;
    requires rt;


    opens com.example.test123 to javafx.fxml;
    exports com.example.test123;
}