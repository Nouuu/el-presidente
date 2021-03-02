package org.esgi.el_presidente.javafx;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.events.EventChoice;
import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.scenario.ScenarioList;
import org.esgi.el_presidente.core.season.Season;

import java.io.IOException;
import java.net.URL;

public class FxApp extends Application {
    private final FxMusic fxMusic = new FxMusic();
    private final FxGameManager fxGameManager = new FxGameManager();

    /////////////////////// INITIALIZATION ////////////////

    public static void launchApp() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = getLoader();
        Scene scene = new Scene(getRootLayout(loader));
        linkController(loader);
        fxMusic.startMusicPlayer();
        showApp(primaryStage, scene);
    }

    private void linkController(FXMLLoader loader) {
        FxController controller = loader.getController();
        controller.setFxApp(this);
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

    private void showApp(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("El Presidente");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/javafx/icons/dictator.jpg")));
        primaryStage.show();
    }

    public ObservableList<String> getGameInfosObservable() {
        return fxGameManager.getGameInfosObservable();
    }

    public ObservableList<String> getFactionsInfosObservable() {
        return fxGameManager.getFactionsInfosObservable();
    }

    public ObservableList<String> getFactionsBrideInfosObservable() {
        return fxGameManager.getFactionsBrideInfosObservable();
    }

    ///////////////////////// COMMONS /////////////////////

    public void refreshGameInfos() {
        fxGameManager.refreshGameInfos();
    }

    private void refreshEndOfYearImpacts(Label foodImpact, Label partisanImpact) {
        foodImpact.setText("Coût en nourriture de l'année : " + fxGameManager.endOfYearFoodImpact() + " => " +
                fxGameManager.endOfYearFoodImpactResult());
        partisanImpact.setText("Estimation de partisans mort de famine : " + fxGameManager.endOfYearParsisanToRemove());
    }

    private void refreshEndOfYearGains(Label moneyGain, Label foodGain) {
        moneyGain.setText("Argent gagné : " + fxGameManager.endOfYearMoneyGain() + "$");
        foodGain.setText("Nourriture gagnée : " + fxGameManager.endOfYearFoodGain());
    }

    public void refreshBuyFoodCosts(Label foodCost, Button buyFoodButton, int food) {
        int price = food * fxGameManager.getFoodPrice();
        foodCost.setText("x " + fxGameManager.getFoodPrice() + "$ = " + price + "$");
        buyFoodButton.setDisable(price > fxGameManager.getMoney());
    }

    public void refreshFactionsBride() {
        fxGameManager.refreshFactionsBride();
    }

    public void playMusic() {
        fxMusic.resume();
    }

    public void pauseMusic() {
        fxMusic.pause();
    }

    ///////////////////////// GAMEMODE CHOOSE /////////////

    public void chooseGameMode(ScenarioList scenarioL, Difficulty difficulty, Text scenarioName, Text scenarioDescription, Label difficultyLabel) throws JsonProcessingException {
        fxGameManager.newGame(scenarioL, difficulty);

        difficultyMusic(difficulty);
        newGameDifficultyLabel(difficultyLabel, difficulty);
        newGameScenarioLabel(scenarioName, scenarioDescription, fxGameManager.getScenarioIntroductions(), scenarioL);

        refreshGameInfos();
    }

    public void difficultyMusic(Difficulty difficulty) {
        if (difficulty.equals(Difficulty.HARDCORE)) {
            fxMusic.JDG();
        } else {
            fxMusic.startMusicPlayer();
        }
    }

    public void newGameDifficultyLabel(Label difficultyLabel, Difficulty difficulty) {
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
    }

    public void newGameScenarioLabel(Text scenarioName, Text scenarioDescription, String scenarioIntroduction, ScenarioList scenarioL) {
        scenarioName.setText(scenarioL.getName());
        scenarioDescription.setText(scenarioIntroduction);
        scenarioDescription.setVisible(true);
    }

    ////////////////////// EVENT //////////////////////////

    public ToggleGroup nextEvent(Label eventDescription, VBox eventRadioButtonStack, Label eventSeason) {
        eventSeason.setText(fxGameManager.getCurrentSeason().toString().toUpperCase());
        fxGameManager.nextTurn();

        Event event = fxGameManager.getCurrentEvent();
        eventDescription.setText(event.getEventDetails());

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
            radioButton.setText(eventChoice.toString(fxGameManager.getChoosenDifficulty()));
            eventRadioButtonStack.getChildren().add(radioButton);
        }
        return toggleGroup;
    }

    public ToggleGroup chooseEventChoice(int choiceIndex, Label eventDescription, VBox eventRadioButtonStack, Label eventSeason) {
        fxGameManager.chooseEventChoice(choiceIndex);
        if (isTheEndOfTheYear(eventSeason)) {
            fxGameManager.triggerEndOfYearGains();
            refreshGameInfos();
            return null;
        }
        refreshGameInfos();
        return nextEvent(eventDescription, eventRadioButtonStack, eventSeason);
    }

    /////////////////////// END OF YEAR //////////////////

    private boolean isTheEndOfTheYear(Label season) {
        return Season.fromString(season.getText()).equals(Season.winter);
    }

    public void endOfYear(Label moneyGain, Label foodGain, Label foodImpact, Label partisanImpact, Label brideLoyalistSatisfactionLabel) {
        refreshEndOfYearGains(moneyGain, foodGain);
        refreshEndOfYearImpacts(foodImpact, partisanImpact);
        refreshBrideLoyalistSatifsfactionLabel(brideLoyalistSatisfactionLabel);
        refreshFactionsBride();
    }

    public FactionType selectFactionToBride(Label costLabel, Button buyBribeButton, String selectedLine) {
        FactionType selectedFaction = FactionType.fromString(selectedLine.split(":")[0]);
        if (selectedFaction == null) {
            System.out.println("Error ! Can't find selected faction");
            return null;
        }
        int cost = fxGameManager.getBrideCost(selectedFaction);
        costLabel.setText("Coût : " + cost + "$");
        buyBribeButton.setDisable(cost > fxGameManager.getMoney());
        return selectedFaction;
    }

    public void buyFood(int value, Label foodImpact, Label partisanImpact) {
        fxGameManager.buyFood(value);
        refreshGameInfos();
        refreshEndOfYearImpacts(foodImpact, partisanImpact);
    }

    public void buyBride(FactionType factionType, Label brideLoyalistSatisfactionLabel) {
        assert factionType != null;
        fxGameManager.buyBride(factionType);
        refreshBrideLoyalistSatifsfactionLabel(brideLoyalistSatisfactionLabel);

        refreshGameInfos();
        refreshFactionsBride();
    }

    private void refreshBrideLoyalistSatifsfactionLabel(Label brideLoyalistSatisfactionLabel) {
        Faction loyalist = fxGameManager.getFaction(FactionType.loyalist);
        brideLoyalistSatisfactionLabel.setText("Satisfaction des Loyalistes : " + loyalist.getSatisfaction() + "%");
        if (loyalist.getSatisfaction() == 0) {
            brideLoyalistSatisfactionLabel.setTextFill(Paint.valueOf("#940300"));
        } else {
            brideLoyalistSatisfactionLabel.setTextFill(Paint.valueOf("#DCDCDC"));
        }
    }

    public boolean nextYear() {
        return fxGameManager.nextYear();
    }

    ////////////////////// GAME OVER //////////////////////

    public void gameOver(Label gameOverStatusLabel,
                         Label gameOverMoney,
                         Label gameOverFood,
                         Label gameOverSatisfaction,
                         Label gameOverSatisfactionLimit,
                         Label gameOverPartisans) {

        if (fxGameManager.isSandboxGame()) {
            gameOverStatusLabel.setText("Vous avez perdu cette partie... T'es nul !");
            gameOverStatusLabel.setTextFill(Paint.valueOf("#940300"));
        } else if (fxGameManager.isNotLost()) {
            //Win
            gameOverStatusLabel.setText("Félicitations !\nVous avez complété le scénario '" + fxGameManager.getChoosenScenarioLabel().getName() + "'");
            gameOverStatusLabel.setTextFill(Paint.valueOf("#25AE2F"));
        } else {
            // Loose
            gameOverStatusLabel.setText("Vous avez perdu cette partie... T'es nul !");
            gameOverStatusLabel.setTextFill(Paint.valueOf("#940300"));
        }
        gameOverMoney.setText("Argent : " + fxGameManager.getMoney() + "$");
        gameOverFood.setText("Nourriture : " + fxGameManager.getFoodReserves());
        gameOverSatisfaction.setText("Satisfaction globale : " + fxGameManager.getFactionGlobalSatisfaction());
        gameOverSatisfactionLimit.setText("Satisfaction minimum requise : " + fxGameManager.getFactionGlobalSatisfactionLimit());
        gameOverPartisans.setText("Partisans : " + fxGameManager.getTotalPartisan());
    }
}
