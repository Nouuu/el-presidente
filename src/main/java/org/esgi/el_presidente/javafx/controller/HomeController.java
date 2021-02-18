package org.esgi.el_presidente.javafx.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.esgi.el_presidente.core.scenario.ScenarioList;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

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
    private ComboBox<ScenarioList> scenarioComboBox;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preventDividerMove();
        initScenarioList();
    }

    private void preventDividerMove() {
        splitPane1.getDividers().get(0).positionProperty().addListener((observableValue, number, t1) -> splitPane1.setDividerPositions(0.25));
    }

    @FXML
    private void onChooseScenario(ActionEvent event) {
        ScenarioList scenario = scenarioComboBox.getValue();
        if (scenario == null) {
            System.out.println("Action");
        } else {
            scenarioName.setText(scenario.getName());
            setVisibleStackPane(eventPane);
        }
    }


    private void setVisibleStackPane(Pane pane) {
        rightStackPane.getChildren().forEach(n -> n.setVisible(false));
        pane.setVisible(true);
    }

    private void initScenarioList() {
        scenarioComboBox.getItems().addAll(ScenarioList.values());
    }
}
