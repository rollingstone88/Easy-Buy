module com.example.projecteasybuy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens com.example.projecteasybuy to javafx.fxml;
    exports com.example.projecteasybuy;
}