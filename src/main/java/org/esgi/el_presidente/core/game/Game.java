package org.esgi.el_presidente.core.game;

import java.util.List;

import org.esgi.el_presidente.core.events.*;
import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionManager;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.ressources.Agriculture;
import org.esgi.el_presidente.core.ressources.Industry;
import org.esgi.el_presidente.core.ressources.RessourceManager;
import org.esgi.el_presidente.core.scenario.Scenario;
import org.esgi.el_presidente.core.season.Season;

public class Game {
    private Difficulty difficulty;
    private TimeManager timeManager;
    private Scenario scenario;
    private FactionManager factionManager;
    private RessourceManager ressourceManager;
    private Event currentEvent;
    private int satisfactionLimit;

    private FactionManager constructFactionManagerFromScenario(Scenario scenario) {
        int initialPartisansSatisfaction = scenario.getInitialPartisansSatisfaction();
        int initialPartisans = scenario.getInitialPartisans();
        int initialLoyalistPartisansSatisfaction = scenario.getInitialLoyalistPartisansSatisfaction();
        int initialLoyalistPartisans = scenario.getInitialLoyalistPartisans();

        return new FactionManager().initFactionList(initialPartisansSatisfaction, initialPartisans,
                initialLoyalistPartisansSatisfaction, initialLoyalistPartisans);
    }

    private RessourceManager constructRessourceManagerFromScenario(Scenario scenario) {
        Agriculture agriculture = new Agriculture(scenario.getInitialAgriculture(), 4);
        Industry industry = new Industry(scenario.getInitialIndustrialization(), 15);
        Faction loyalist = factionManager.getFaction(FactionType.loyalist);
        int initialMoney = scenario.getInitialMoney();
        return new RessourceManager(loyalist, initialMoney, 0, agriculture, industry);
    }

    public Game(Scenario scenario, Difficulty difficulty) {
        this.difficulty = difficulty;
        this.scenario = scenario;
        satisfactionLimit = difficulty.getSatisfactionLimit();

        this.factionManager = constructFactionManagerFromScenario(scenario);
        this.ressourceManager = constructRessourceManagerFromScenario(scenario);
        this.timeManager = new TimeManager();
    }

    public void nextTurn() {
        System.out.println(timeManager.getSeason());
        currentEvent = scenario.getNextEvent(timeManager.getSeason());

        if (timeManager.isTheEndOfTheYear()) {
            // TODO buy bribe / food
            System.out.println(reviewTheGame());
        }

        timeManager.nextSeason();
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    // TODO
    public String reviewTheGame() {
        StringBuilder game = new StringBuilder();
        game.append("Money: " + ressourceManager.getMoney() + "\n");
        game.append("FoodReseve: " + ressourceManager.getFoodReserves() + "\n");
        game.append("GlobalSatisfaction: " + factionManager.getGlobalSatisfaction() + "\n");
        return game.toString();
    }

    public boolean isNotLost() {
        int globalSatisfaction = factionManager.getGlobalSatisfaction();
        return satisfactionLimit < globalSatisfaction;
    }

    public void triggerEventEffect(int index) {
        Event event = getCurrentEvent();
        EventChoice eventChoice = event.getEventChoice(index);

        int agricultureEffect = eventChoice.getAgricultureEffect();
        int industryEffect = agricultureEffect = eventChoice.getIndustryEffect();
        int financeEffect = eventChoice.getFinanceEffect();
        int foodEffect = eventChoice.getFoodEffect();
        List<EventFactionEffect> factionEffects = eventChoice.getFactionEffects();

        ressourceManager.increaseSizeOfAgriculture(agricultureEffect);
        ressourceManager.increaseSizeOfIndustry(industryEffect);
        ressourceManager.handleMoneyAction(financeEffect);
        ressourceManager.handleFoodAction(foodEffect);

        factionEffects.stream().forEach(effect -> triggerEventsFactionEffect(effect));
    }

    private void triggerEventsFactionEffect(EventFactionEffect effect) {
        Faction faction = factionManager.getFaction(effect.getFactionType());
        faction.updatePartisansPercent(effect.getPartisansPercentEffect());
        faction.updateSatisfaction(effect.getSatisfactionEffect());
    }

    public FactionManager getFactionManager() {
        return factionManager;
    }

    public RessourceManager getRessourceManager() {
        return ressourceManager;
    }

    public int getSatisfactionLimit() {
        return satisfactionLimit;
    }

    public Season getCurrentSeason() {
        return timeManager.getSeason();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
