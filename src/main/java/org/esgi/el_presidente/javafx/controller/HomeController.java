package org.esgi.el_presidente.javafx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.scenario.ScenarioList;
import org.esgi.el_presidente.javafx.FxApp;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private FxApp fxApp;
    private ToggleGroup radioToggleGroup;

    //////////// GENERAL //////////////////////////////////
    @FXML
    private SplitPane splitPane1;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private StackPane rightStackPane;


    /////////// LEFT PANE /////////////////////////////////

    @FXML
    private Text scenarioName;

    @FXML
    private Label scenarioDetails;

    @FXML
    private Label difficultyLabel;

    @FXML
    private VBox statusVbox;

    @FXML
    private ListView<String> gameInfos;

    @FXML
    private ListView<String> factionsInfos;

    /////// SCENARIO CHOOSE ///////////////////////////////

    @FXML
    private AnchorPane scenarioChoosePane;

    @FXML
    private ComboBox<ScenarioList> scenarioComboBox;

    @FXML
    private ComboBox<Difficulty> difficultyComboBox;

    /////// EVENT PANE ////////////////////////////////////

    @FXML
    private AnchorPane eventPane;

    @FXML
    private Label eventSeason;

    @FXML
    private Label eventDescription;

    @FXML
    private VBox eventRadioButtonStack;

    ///// END YEAR PANE ///////////////////////////////////

    @FXML
    private AnchorPane endOfYearPane;

    @FXML
    private Label endOfYearFoodGain;

    @FXML
    private Label endOfYearMoneyGain;

    @FXML
    private Label endOfYearFoodImpact;

    @FXML
    private Label endOfYearPartisanImpact;

    @FXML
    private Spinner<Integer> buyFoodSpinner;

    private SpinnerValueFactory<Integer> buyFoodSpinnerValueFactory;

    @FXML
    private Label buyFoodLabel;

    @FXML
    private Button buyFoodButton;

    @FXML
    private Label brideLoyalistSatisfactionLabel;

    @FXML
    private ListView<String> brideFactionListView;

    private FactionType selectedFactionType;

    @FXML
    private Label brideFactionCostLabel;

    @FXML
    private Button buyBrideButton;

    @FXML
    private Button nextYearButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preventDividerMove();
        setVisibleStackPane(scenarioChoosePane);
        initScenarioList();
        initDifficultyList();
        initbuyFoodSpinner();
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
        initializeFactionBrideListView();
    }

    private void initializeFactionBrideListView() {
        brideFactionListView.setItems(fxApp.getFactionsBrideInfosObservable());
        brideFactionListView.setOnMouseClicked(event -> selectedFactionType = fxApp.selectFactionToBride(brideFactionCostLabel, buyBrideButton, brideFactionListView.getSelectionModel().getSelectedItem()));
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
        if (radioToggleGroup == null) {
            //End of year
            endOfYear();
        }
    }

    private void endOfYear() {
        setVisibleStackPane(endOfYearPane);
        fxApp.endOfYear(endOfYearMoneyGain, endOfYearFoodGain, endOfYearFoodImpact, endOfYearPartisanImpact, brideLoyalistSatisfactionLabel);
        buyFoodSpinnerValueFactory.setValue(1);
        buyFoodSpinnerNewValue(1);
        selectedFactionType = null;
    }

    private void initbuyFoodSpinner() {
        buyFoodSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99999, 1);
        buyFoodSpinnerValueFactory.valueProperty().addListener((obs, oldValue, newValue)
                -> buyFoodSpinnerNewValue(newValue));
        buyFoodSpinner.setValueFactory(buyFoodSpinnerValueFactory);
    }

    private void buyFoodSpinnerNewValue(int newValue) {
        fxApp.refreshBuyFoodCosts(buyFoodLabel, buyFoodButton, newValue);
    }

    @FXML
    private void onNextYear() {
        System.out.println("Next Year");
    }

    @FXML
    private void onBuyFood() {
        fxApp.buyFood(buyFoodSpinnerValueFactory.getValue(), endOfYearFoodImpact, endOfYearPartisanImpact);
        buyFoodSpinnerValueFactory.setValue(1);
    }

    @FXML
    private void onBuyBride() {
        if (selectedFactionType != null) {
            fxApp.buyBride(selectedFactionType, brideLoyalistSatisfactionLabel);
        }
    }
}
