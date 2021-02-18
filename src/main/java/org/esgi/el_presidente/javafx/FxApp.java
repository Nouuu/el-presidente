package org.esgi.el_presidente.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class FxApp extends Application {
    public static void launchApp() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL file = getClass().getResource("/javafx/home.fxml");

        Parent root = FXMLLoader.load(file);
        Scene scene = new Scene(root,1280,720);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("El Presidente");
        primaryStage.show();
    }

}