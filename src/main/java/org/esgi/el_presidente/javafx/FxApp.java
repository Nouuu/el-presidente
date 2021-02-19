package org.esgi.el_presidente.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.esgi.el_presidente.javafx.controller.HomeController;

import java.net.URL;

public class FxApp extends Application {
    public static void launchApp() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL file = getClass().getResource("/javafx/home.fxml");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(file);

        Parent rootLayout = loader.load();
        HomeController controller = loader.getController();
        controller.setFxApp(this);

        Scene scene = new Scene(rootLayout);


        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("El Presidente");
        primaryStage.show();
    }

    public void testOut() {
        System.out.println("OUT");
    }
}
