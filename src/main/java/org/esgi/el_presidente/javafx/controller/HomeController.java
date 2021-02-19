package org.esgi.el_presidente.javafx.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.apache.commons.lang3.StringUtils;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.scenario.ScenarioList;
import org.esgi.el_presidente.javafx.FxApp;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private FxApp fxApp;
    private ToggleGroup radioToggleGroup;

    @FXML
    private SplitPane splitPane1;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private AnchorPane scenarioChoosePane;

    @FXML
    private AnchorPane eventPane;

    @FXML
    private StackPane rightStackPane;

    @FXML
    private Text scenarioName;

    @FXML
    private Label scenarioDetails;

    @FXML
    private Label difficultyLabel;

    @FXML
    private ComboBox<ScenarioList> scenarioComboBox;

    @FXML
    private ComboBox<Difficulty> difficultyComboBox;

    @FXML
    private ListView<String> gameInfos;

    @FXML
    private ListView<String> factionsInfos;

    @FXML
    private VBox statusVbox;

    @FXML
    private Label eventDescription;

    @FXML
    private VBox eventRadioButtonStack;

    @FXML
    private Label eventSeason;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preventDividerMove();
        setVisibleStackPane(scenarioChoosePane);
        initScenarioList();
        initDifficultyList();
    }

    private void preventDividerMove() {
        splitPane1.getDividers().get(0).positionProperty().addListener((observableValue, number, t1) -> splitPane1.setDividerPositions(0.25));
    }

    @FXML
    private void onChooseGameMode() throws JsonProcessingException {
        ScenarioList scenario = scenarioComboBox.getValue();
        Difficulty difficulty = difficultyComboBox.getValue();
        if (scenario != null && difficulty != null) {
            fxApp.chooseGameMode(scenario, difficulty, scenarioName, scenarioDetails, difficultyLabel);
            setVisibleStackPane(eventPane);
            statusVbox.setVisible(true);
            getNextEvent();
        }
    }


    private void setVisibleStackPane(Pane pane) {
        rightStackPane.getChildren().forEach(n -> n.setVisible(false));
        pane.setVisible(true);
    }

    private void initScenarioList() {
        scenarioComboBox.getItems().addAll(ScenarioList.values());
        scenarioComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(ScenarioList object) {
                return StringUtils.capitalize(object.getName());
            }

            @Override
            public ScenarioList fromString(String string) {
                return scenarioComboBox.getItems()
                        .stream()
                        .filter(s -> s.getName().equals(string))
                        .findFirst().orElse(null);
            }
        });
    }

    private void initDifficultyList() {
        difficultyComboBox.getItems().addAll(Difficulty.values());
        difficultyComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Difficulty object) {
                return StringUtils.capitalize(object.toString());
            }

            @Override
            public Difficulty fromString(String string) {
                return difficultyComboBox.getItems()
                        .stream()
                        .filter(s -> s.toString().equals(string))
                        .findFirst().orElse(null);
            }
        });
    }

    public void setFxApp(FxApp fxApp) {
        this.fxApp = fxApp;
        gameInfos.setItems(fxApp.getGameInfosObservable());
        factionsInfos.setItems(fxApp.getFactionsInfosObservable());
    }

    private void getNextEvent() {
//        setVisibleStackPane(eventPane);
        radioToggleGroup = fxApp.nextEvent(eventDescription, eventRadioButtonStack, eventSeason);
    }

    @FXML
    private void onSelectChoice() {
        RadioButton radioButton = (RadioButton) radioToggleGroup.getSelectedToggle();
        int index = Integer.parseInt(radioButton.getId());
        radioToggleGroup = fxApp.chooseEventChoice(index, eventDescription, eventRadioButtonStack, eventSeason);
    }

}
