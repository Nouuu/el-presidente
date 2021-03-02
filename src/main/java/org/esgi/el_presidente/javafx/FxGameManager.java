package org.esgi.el_presidente.javafx;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;
import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.game.Game;
import org.esgi.el_presidente.core.scenario.Sandbox;
import org.esgi.el_presidente.core.scenario.Scenario;
import org.esgi.el_presidente.core.scenario.ScenarioList;
import org.esgi.el_presidente.core.season.Season;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FxGameManager {
    private Game game;
    private ScenarioList choosenScenarioLabel;
    private Difficulty choosenDifficulty;
    private final ObservableList<String> gameInfosObservable = FXCollections.observableArrayList();
    private final ObservableList<String> factionsInfosObservable = FXCollections.observableArrayList();
    private final ObservableList<String> factionsBrideInfosObservable = FXCollections.observableArrayList();

    public FxGameManager() {

    }

    public void newGame(ScenarioList scenarioL, Difficulty difficulty) throws JsonProcessingException {
        choosenScenarioLabel = scenarioL;
        choosenDifficulty = difficulty;
        game = new Game(resolveChoosenScenario(scenarioL), difficulty);
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

    public void nextTurn() {
        game.nextTurn();
    }

    public void chooseEventChoice(int choiceIndex) {
        game.triggerEventEffect(choiceIndex);
    }

    public void triggerEndOfYearGains() {
        game.triggerEndOfYearRessource();
    }

    public void buyFood(int value) {
        game.buyFood(value);
    }

    public void buyBride(FactionType factionType) {
        try {
            game.getRessourceManager().buyBribe(getFaction(factionType));
        } catch (Exception e) {
            System.out.println("Error while bride faction !!");
        }
    }

    public boolean nextYear() {
        game.triggerEndOfYearCost();
        refreshGameInfos();
        return game.isNotLost() && !game.isEndOfScenario();
    }

    private Scenario resolveChoosenScenario(ScenarioList scenarioL) throws JsonProcessingException {
        if (scenarioL.equals(ScenarioList.SANDBOX)) {
            return Sandbox.createFromJson(scenarioL.getPath());
        } else {
            return Scenario.createFromJson(scenarioL.getPath());
        }
    }

    // GETTER

    public ObservableList<String> getGameInfosObservable() {
        return gameInfosObservable;
    }

    public ObservableList<String> getFactionsInfosObservable() {
        return factionsInfosObservable;
    }

    public ObservableList<String> getFactionsBrideInfosObservable() {
        return factionsBrideInfosObservable;
    }

    public ScenarioList getChoosenScenarioLabel() {
        return choosenScenarioLabel;
    }

    public Difficulty getChoosenDifficulty() {
        return choosenDifficulty;
    }

    public String getScenarioIntroductions() {
        return game.getScenario().getIntroduction();
    }

    public Season getCurrentSeason() {
        return game.getCurrentSeason();
    }

    public Event getCurrentEvent() {
        return game.getCurrentEvent();
    }

    public int endOfYearFoodImpact() {
        return game.calculateFoodImpact();
    }

    public int endOfYearFoodImpactResult() {
        return Math.max(0, game.getRessourceManager().getFoodReserves() - game.calculateFoodImpact());
    }

    public int endOfYearParsisanToRemove() {
        return game.calculatePartisanToRemove();
    }

    public int endOfYearMoneyGain() {
        return game.getEndOfYearMoneyProduction();
    }

    public int endOfYearFoodGain() {
        return game.getEndOfYearFoodProduction();
    }

    public int getFoodPrice() {
        return game.getFoodPrice();
    }

    public int getMoney() {
        return game.getRessourceManager().getMoney();
    }

    public int getFoodReserves() {
        return game.getRessourceManager().getFoodReserves();
    }

    public int getFactionGlobalSatisfaction() {
        return game.getFactionManager().getGlobalSatisfaction();
    }

    public int getFactionGlobalSatisfactionLimit() {
        return game.getSatisfactionLimit();
    }

    public int getTotalPartisan() {
        return game.getFactionManager().getTotalPartisan();
    }

    public Faction getFaction(FactionType selectedFactionType) {
        return game.getFactionManager().getFaction(selectedFactionType);
    }

    public int getBrideCost(FactionType factionType) {
        return game.getRessourceManager().getBrideCost(getFaction(factionType).getPartisans());
    }

    public boolean isSandboxGame() {
        return game.getScenario() instanceof Sandbox;
    }

    public boolean isNotLost() {
        return game.isNotLost();
    }
}
