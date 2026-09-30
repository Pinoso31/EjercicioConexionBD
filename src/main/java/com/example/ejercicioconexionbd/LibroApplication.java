package com.example.ejercicioconexionbd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibroApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("libro-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ventana registro libro");
        stage.setScene(scene);
        stage.show();
    }
}
