module com.example.ejercicioconexionbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ejercicioconexionbd.model to javafx.base;
    opens com.example.ejercicioconexionbd to javafx.fxml;
    exports com.example.ejercicioconexionbd.controller to javafx.fxml;
    opens com.example.ejercicioconexionbd.controller to javafx.fxml;
    exports com.example.ejercicioconexionbd;
}