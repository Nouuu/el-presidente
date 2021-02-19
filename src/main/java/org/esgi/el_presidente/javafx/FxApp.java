package org.esgi.el_presidente.javafx;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.game.Game;
import org.esgi.el_presidente.core.scenario.Scenario;
import org.esgi.el_presidente.core.scenario.ScenarioList;
import org.esgi.el_presidente.javafx.controller.HomeController;

import javafx.scene.media.Media;

import java.io.IOException;
import java.net.URL;

public class FxApp extends Application {
    private Game game;
    public ObservableList<String> gameInfosObservable = FXCollections.observableArrayList();
    public ObservableList<String> factionsInfosObservable = FXCollections.observableArrayList();

    public static void launchApp() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = getLoader();
        Scene scene = new Scene(getRootLayout(loader));
        linkController(loader);
        playIt();
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

    private void showApp(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("El Presidente");
        primaryStage.show();
    }

    public void chooseGameMode(ScenarioList scenarioL, Difficulty difficulty, Text scenarioName, Label scenarioDescription, Label difficultyLabel) throws JsonProcessingException {
        scenarioName.setText(scenarioL.getName());
        difficultyLabel.setText(difficulty.toString().toUpperCase());
        difficultyLabel.setVisible(true);
        switch (difficulty) {
            case EASY:
                difficultyLabel.setTextFill(Paint.valueOf("#00FF08"));
                break;
            case MEDIUM:
                difficultyLabel.setTextFill(Paint.valueOf("#BEFF00"));
                break;
            case HARD:
                difficultyLabel.setTextFill(Paint.valueOf("#FFAD1C"));
                break;
            case HARDCORE:
                difficultyLabel.setTextFill(Paint.valueOf("#FF0800"));
                break;
            default:
                difficultyLabel.setTextFill(Paint.valueOf("#ffffff"));
                break;
        }

        Scenario scenario = Scenario.createFromJson(scenarioL.getPath());
        scenarioDescription.setText(scenario.getIntroduction());
        scenarioDescription.setVisible(true);
        game = new Game(scenario, difficulty);
        refreshGameInfos();
    }

    public ObservableList<String> getGameInfosObservable() {
        return gameInfosObservable;
    }

    public ObservableList<String> getFactionsInfosObservable() {
        return factionsInfosObservable;
    }

    public void refreshGameInfos() {
        gameInfosObservable.clear();
        gameInfosObservable.add("Argent : " + game.getRessourceManager().getMoney() + "$");
        gameInfosObservable.add("Nourriture : " + game.getRessourceManager().getFoodReserves());
        gameInfosObservable.add("Occupation industrie : " + game.getRessourceManager().getIndustryOccupation() + "%");
        gameInfosObservable.add("Occupation agriculture : " + game.getRessourceManager().getAgricultureOccupation() + "%");
        gameInfosObservable.add("Satisfaction globale minimum : " + game.getSatisfactionLimit() + "%");
        gameInfosObservable.add("Satisfaction globale actuelle : " + game.getFactionManager().getGlobalSatisfaction() + "%");
        gameInfosObservable.add("Population totale : " + game.getFactionManager().getTotalPartisan());

        factionsInfosObservable.clear();

        game.getFactionManager().getFactionList().forEach(f ->
                factionsInfosObservable.add(
                        StringUtils.capitalize(f.getFactionType().toString())
                                + "\nPartisans : " + f.getPartisans()
                                + "\nSatisfaction : " + f.getSatisfaction() + "%"));
    }

    private void playIt() {
        Media media1;
        Media media2;
        try {
            media1 = new Media(getClass().getResource("/music/glorious-morning.mp3").toString());
            media2 = new Media(getClass().getResource("/music/glorious-morning-2.mp3").toString());
        } catch (Exception e) {
            System.out.println("Can't read sound files !");
            return;
        }

        ObservableList<Media> mediaList = FXCollections.observableArrayList();
        mediaList.addAll(media1,media2);


        playList(mediaList, 0);
    }

    private void playList(ObservableList<Media> mediaList, int i) {
        if (mediaList.size() == 0) {
            return;
        }
        MediaPlayer player = new MediaPlayer(mediaList.get(i));
        int nextI = (i + 1) % mediaList.size();
        player.setOnEndOfMedia(() -> playList(mediaList, nextI));
        player.play();
    }
}
