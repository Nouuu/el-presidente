package org.esgi.el_presidente.javafx;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.events.EventChoice;
import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.game.Game;
import org.esgi.el_presidente.core.scenario.Sandbox;
import org.esgi.el_presidente.core.scenario.Scenario;
import org.esgi.el_presidente.core.scenario.ScenarioList;
import org.esgi.el_presidente.core.season.Season;
import org.esgi.el_presidente.javafx.controller.HomeController;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FxApp extends Application {
    private Game game;
    private ScenarioList scenario;
    public ObservableList<String> gameInfosObservable = FXCollections.observableArrayList();
    public ObservableList<String> factionsInfosObservable = FXCollections.observableArrayList();
    public ObservableList<String> factionsBrideInfosObservable = FXCollections.observableArrayList();

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
        scenario = scenarioL;
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

        Scenario scenario;
        if (scenarioL.equals(ScenarioList.SANDBOX)) {
            scenario = Sandbox.createFromJson(scenarioL.getPath());
        } else {
            scenario = Scenario.createFromJson(scenarioL.getPath());
        }
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

    public ObservableList<String> getFactionsBrideInfosObservable() {
        return factionsBrideInfosObservable;
    }

    public void refreshGameInfos() {
        gameInfosObservable.clear();
        gameInfosObservable.add("Argent : " + game.getRessourceManager().getMoney() + "$");
        gameInfosObservable.add("Nourriture : " + game.getRessourceManager().getFoodReserves());
        gameInfosObservable.add("Occupation industrie : " + game.getRessourceManager().getIndustryPart() + "%");
        gameInfosObservable.add("Occupation agriculture : " + game.getRessourceManager().getAgriculturePart() + "%");
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

    public void refreshFactionsBride() {
        List<FactionType> factions = Arrays.stream(FactionType.values()).filter(fT -> fT != FactionType.loyalist).collect(Collectors.toList());
        factionsBrideInfosObservable.clear();
        factions.forEach(f -> {
            Faction faction = game.getFactionManager().getFaction(f);
            factionsBrideInfosObservable.add(StringUtils.capitalize(f.toString()) + ": Satisfaction à " + faction.getSatisfaction() + "%, " + faction.getPartisans() + " partisans");
        });
    }

    public ToggleGroup nextEvent(Label eventDescription, VBox eventRadioButtonStack, Label eventSeason) {
        Season season = game.getCurrentSeason();
        game.nextTurn();
        Event event = game.getCurrentEvent();

        eventDescription.setText(event.getEventDetails());
        eventSeason.setText(season.toString().toUpperCase());

        eventRadioButtonStack.getChildren().clear();
        ToggleGroup toggleGroup = new ToggleGroup();

        for (int i = 0; i < event.getEventChoices().size(); i++) {
            RadioButton radioButton = new RadioButton();
            if (i == 0) {
                radioButton.setSelected(true);
            }
            EventChoice eventChoice = event.getEventChoices().get(i);
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setId(String.valueOf(i));
            radioButton.setText(eventChoice.toString(game.getDifficulty()));
            eventRadioButtonStack.getChildren().add(radioButton);

        }
        return toggleGroup;
    }

    public ToggleGroup chooseEventChoice(int choiceIndex, Label eventDescription, VBox eventRadioButtonStack, Label eventSeason) {
        if (isTheEndOfTheYear(eventSeason)) {
            game.triggerEventEffect(choiceIndex);
            game.triggerEndOfYearRessource();
            refreshGameInfos();
            return null;
        }
        game.triggerEventEffect(choiceIndex);
        refreshGameInfos();
        return nextEvent(eventDescription, eventRadioButtonStack, eventSeason);
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
        mediaList.addAll(media1, media2);


        playList(mediaList, 0);
    }

    private void playList(ObservableList<Media> mediaList, int i) {
        if (mediaList.size() == 0) {
            return;
        }
        MediaPlayer player = new MediaPlayer(mediaList.get(i));
        int nextI = (i + 1) % mediaList.size();
        player.setOnEndOfMedia(() -> playList(mediaList, nextI));
        player.setAutoPlay(true);
        player.play();
    }

    public void endOfYear(Label moneyGain, Label foodGain, Label foodImpact, Label partisanImpact, Label brideLoyalistSatisfactionLabel) {
        refreshEndOfYearGains(moneyGain, foodGain);
        refreshEndOfYearImpacts(foodImpact, partisanImpact);
        refreshBrideLoyalistSatifsfactionLabel(brideLoyalistSatisfactionLabel);
        refreshFactionsBride();
    }

    private void refreshEndOfYearImpacts(Label foodImpact, Label partisanImpact) {
        foodImpact.setText("Coût en nourriture de l'année : " + game.calculateFoodImpact() + " => " +
                Math.max(0, game.getRessourceManager().getFoodReserves() - game.calculateFoodImpact()));
        partisanImpact.setText("Estimation de partisans mort de famine : " + game.calculatePartisanToRemove());
    }

    private void refreshEndOfYearGains(Label moneyGain, Label foodGain) {
        moneyGain.setText("Argent gagné : " + game.getEndOfYearMoneyProduction() + "$");
        foodGain.setText("Nourriture gagnée : " + game.getEndOfYearFoodProduction());
    }

    public void refreshBuyFoodCosts(Label foodCost, Button buyFoodButton, int food) {
        int price = food * game.getFoodPrice();
        foodCost.setText("x " + game.getFoodPrice() + "$ = " + price + "$");
        buyFoodButton.setDisable(price > game.getRessourceManager().getMoney());
    }

    private boolean isTheEndOfTheYear(Label season) {
        return Season.fromString(season.getText()).equals(Season.winter);
    }

    public FactionType selectFactionToBride(Label costLabel, Button buyBribeButton, String selectedLine) {
        FactionType selectedFaction = FactionType.fromString(selectedLine.split(":")[0]);
        if (selectedFaction == null) {
            System.out.println("Error ! Can't find selected faction");
            return null;
        }
        Faction faction = game.getFactionManager().getFaction(selectedFaction);
        int cost = game.getRessourceManager().getBrideCost(faction.getPartisans());
        costLabel.setText("Coût : " + cost + "$");
        buyBribeButton.setDisable(cost > game.getRessourceManager().getMoney());
        return selectedFaction;
    }

    public void buyFood(int value, Label foodImpact, Label partisanImpact) {
        game.buyFood(value);
        refreshGameInfos();
        refreshEndOfYearImpacts(foodImpact, partisanImpact);
    }

    public void buyBride(FactionType factionType, Label brideLoyalistSatisfactionLabel) {
        assert factionType != null;
        Faction faction = game.getFactionManager().getFaction(factionType);
        try {
            game.getRessourceManager().buyBribe(faction);
        } catch (Exception e) {
            System.out.println("Error while bride faction !!");
        }
        refreshBrideLoyalistSatifsfactionLabel(brideLoyalistSatisfactionLabel);

        refreshGameInfos();
        refreshFactionsBride();
    }

    private void refreshBrideLoyalistSatifsfactionLabel(Label brideLoyalistSatisfactionLabel) {
        Faction loyalist = game.getFactionManager().getFaction(FactionType.loyalist);
        brideLoyalistSatisfactionLabel.setText("Satisfaction des Loyalistes : " + loyalist.getSatisfaction() + "%");
        if (loyalist.getSatisfaction() == 0) {
            brideLoyalistSatisfactionLabel.setTextFill(Paint.valueOf("#940300"));
        } else {
            brideLoyalistSatisfactionLabel.setTextFill(Paint.valueOf("#DCDCDC"));
        }
    }

    public boolean nextYear() {
        game.triggerEndOfYearCost();
        refreshGameInfos();
        return game.isNotLost();
    }

    public void gameOver(Label gameOverStatusLabel,
                         Label gameOverMoney,
                         Label gameOverFood,
                         Label gameOverSatisfaction,
                         Label gameOverSatisfactionLimit,
                         Label gameOverPartisans) {

        if (game.getScenario() instanceof Sandbox) {
            gameOverStatusLabel.setText("Vous avez perdu cette partie... T'es nul !");
            gameOverStatusLabel.setTextFill(Paint.valueOf("#940300"));
        } else if (game.isEndOfScenario()) {
            //Win
            gameOverStatusLabel.setText("Félicitations !\nVous avez complété le scénario '" + scenario.getName() + "'");
            gameOverStatusLabel.setTextFill(Paint.valueOf("#25AE2F"));
        } else {
            // Loose
            gameOverStatusLabel.setText("Vous avez perdu cette partie... T'es nul !");
            gameOverStatusLabel.setTextFill(Paint.valueOf("#940300"));
        }
        gameOverMoney.setText("Argent : " + game.getRessourceManager().getMoney() + "$");
        gameOverFood.setText("Nourriture : " + game.getRessourceManager().getFoodReserves());
        gameOverSatisfaction.setText("Satisfaction globale : " + game.getFactionManager().getGlobalSatisfaction());
        gameOverSatisfactionLimit.setText("Satisfaction minimum requise : " + game.getSatisfactionLimit());
        gameOverPartisans.setText("Partisans : " + game.getFactionManager().getTotalPartisan());
    }
}
