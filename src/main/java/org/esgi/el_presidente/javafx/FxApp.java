package org.esgi.el_presidente.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.esgi.el_presidente.javafx.controller.HomeController;

import java.io.IOException;
import java.net.URL;

public class FxApp extends Application {
    public static void launchApp() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = getLoader();
        Scene scene = new Scene(getRootLayout(loader));
        linkController(loader);
        showApp(primaryStage, scene);
    }

    private Parent getRootLayout(FXMLLoader loader) throws IOException {
        return loader.load();
    }

    private FXMLLoader getLoader() {
        URL file = getClass().getResource("/javafx/home.fxml");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(file);
        return loader;
    }

    private void linkController(FXMLLoader loader) {
        HomeController controller = loader.getController();
        controller.setFxApp(this);
    }

    public void testOut() {
        System.out.println("OUT");
    }

    private void showApp(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("El Presidente");
        primaryStage.show();
    }
}
