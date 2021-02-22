package org.esgi.el_presidente.core.game;

import java.util.List;
import java.util.Random;

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
        Agriculture agriculture = new Agriculture(scenario.getInitialAgriculture(), 40);
        Industry industry = new Industry(scenario.getInitialIndustrialization(), 10);
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
        currentEvent = scenario.getNextEvent(timeManager.getSeason());

        timeManager.nextSeason();
    }

    public void triggerEventEffect(int eventIndex) {
        Event event = getCurrentEvent();
        EventChoice eventChoice = event.getEventChoice(eventIndex);

        int agricultureEffect = eventChoice.getAgricultureEffect();
        int industryEffect = eventChoice.getIndustryEffect();
        int financeEffect = eventChoice.getFinanceEffect();
        int foodEffect = eventChoice.getFoodEffect();
        System.out.println("foodEffect: " + foodEffect);
        List<EventFactionEffect> factionEffects = eventChoice.getFactionEffects();

        ressourceManager.updateSizeOfAgriculture(agricultureEffect);
        ressourceManager.updateSizeOfIndustry(industryEffect);
        ressourceManager.handleMoneyAction(financeEffect);
        ressourceManager.handleFoodAction(foodEffect);

        factionEffects.stream().forEach(effect -> triggerEventsFactionEffect(effect));
    }

    private void triggerEventsFactionEffect(EventFactionEffect effect) {
        Faction faction = factionManager.getFaction(effect.getFactionType());
        faction.updatePartisansPercent(effect.getPartisansPercentEffect());
        faction.updateSatisfaction(effect.getSatisfactionEffect());
    }

    public boolean isTheEndOfTheYear() {
        return timeManager.isTheEndOfTheYear();
    }

    public boolean isNotLost() {
        int globalSatisfaction = factionManager.getGlobalSatisfaction();
        return satisfactionLimit < globalSatisfaction;
    }

    public RessourceManager getRessourceManager() {
        return ressourceManager;
    }

    public FactionManager getFactionManager() {
        return factionManager;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void triggerEndOfYearCost() {
        int foodImpact = calculateFoodImpact();
        int foodReserves = ressourceManager.getFoodReserves();
        int partisansToRemove = calculatePartisanToRemove();

        if (foodImpact > foodReserves) {
            factionManager.removeRandomlyFactionPartisans(partisansToRemove);
            foodImpact = calculateFoodImpact();
            factionManager.handleEndOfYearFoodAction(partisansToRemove);
        } else {
            Random rand = new Random();
            int max = 10;
            int min = 1;
            int partisanPercentToadd = rand.nextInt(max + min) + min;
            factionManager.addAllFactionsPartisanPercent(partisanPercentToadd);
        }
        ressourceManager.handleFoodAction(-foodImpact);
    }

    public int calculateFoodImpact() {
        int yearlyConsomationOfFoodByPartisan = 4;
        int totalPartisan = factionManager.getTotalPartisan();
        return totalPartisan * yearlyConsomationOfFoodByPartisan;
    }

    public int calculatePartisanToRemove() {
        if (calculateFoodImpact() <= ressourceManager.getFoodReserves()) {
            return 0;
        }
        return (calculateFoodImpact() - ressourceManager.getFoodReserves()) / 4;
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

    public void triggerEndOfYearRessource() {
        ressourceManager.triggerEndOfYearAction();
    }

    public int getEndOfYearMoneyProduction() {
        return ressourceManager.getEndOfYearMoneyProduction();
    }

    public int getEndOfYearFoodProduction() {
        return ressourceManager.getEndOfYearFoodProduction();
    }

    public void buyFood(int unitOfFood) {
        int price = ressourceManager.getFoodPrice() * unitOfFood;
        if (price <= ressourceManager.getMoney()) {
            try {
                ressourceManager.buyFood(unitOfFood);
            } catch (Exception e) {
                System.out.println("Error when buying food !");
            }
        }
    }

    public int getFoodPrice() {
        return ressourceManager.getFoodPrice();
    }

    public boolean isEndOfScenario() {
        return scenario.isLastEvent();
    }

    public Scenario getScenario() {
        return scenario;
    }
}