package org.esgi.el_presidente.core.scenario;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.events.Event;
import org.junit.Before;
import org.junit.Test;

public class ScenarioTest {
    private final String scenarioPath = "test/scenarioTest.json";
    private final String scenarioErrorPath = "test/scenarioTestError.json";
    private final String scenarioError2Path = "test/scenarioTestError2.json";
    private final String scenarioError3Path = "test/scenarioTestError3.json";
    private final String scenarioError4Path = "test/scenarioTestError4.json";
    private final String scenarioError5Path = "test/scenarioTestError5.json";

    private final Event expectedEvent1 = Event.createFromJson("test/eventTest.json");
    private final Event expectedEvent2 = Event.createFromJson("test/eventTest2.json");

    private Scenario scenario;

    public ScenarioTest() throws JsonProcessingException {
    }

    @Before
    public void setUp() throws JsonProcessingException {
        scenario = Scenario.createFromJson(scenarioPath);
    }

    @Test
    public void testCreateFromJson() throws JsonProcessingException {
        Assertions.assertThat(Scenario.createFromJson(scenarioPath)).isInstanceOf(Scenario.class);
    }

    @Test
    public void testCreateFromJsonErrorUnknownProperty() {
        Assertions.assertThatThrownBy(() -> Scenario.createFromJson(scenarioErrorPath))
                .isInstanceOf(JsonProcessingException.class);
    }

    @Test
    public void testCreateFromJsonError2UnknownEvent() {
        Assertions.assertThatThrownBy(() -> Scenario.createFromJson(scenarioError2Path))
                .isInstanceOf(JsonProcessingException.class);
    }

    @Test
    public void testCreateFromJsonErrorSyntax() {
        Assertions.assertThatThrownBy(() -> Scenario.createFromJson(scenarioError3Path))
                .isInstanceOf(JsonProcessingException.class);
    }

    @Test
    public void testCreateFromJsonErrorMissingEvents() {
        Assertions.assertThatThrownBy(() -> Scenario.createFromJson(scenarioError4Path))
                .getCause()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide at least 2 events to your scenario !");
    }
    @Test
    public void testCreateFromJsonErrorMissingEvents2() {
        Assertions.assertThatThrownBy(() -> Scenario.createFromJson(scenarioError5Path))
                .getCause()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide at least 2 events to your scenario !");
    }

    @Test
    public void testGetIntroduction() {
        String expectedIntroduction = "Scenario test";
        Assertions.assertThat(scenario.getIntroduction()).isEqualTo(expectedIntroduction);
    }

    @Test
    public void testGetInitialPartisansSatisfaction() {
        int expectedInitialPartisansSatisfaction = 50;
        Assertions.assertThat(scenario.getInitialPartisansSatisfaction()).isEqualTo(expectedInitialPartisansSatisfaction);
    }

    @Test
    public void testGetInitialPartisans() {
        int expectedInitialPartisans = 10;
        Assertions.assertThat(scenario.getInitialPartisans()).isEqualTo(expectedInitialPartisans);
    }

    @Test
    public void testGetInitialLoyalistPartisansSatisfaction() {
        int expectedInitialLoyalistPartisansSatisfaction = 100;
        Assertions.assertThat(scenario.getInitialLoyalistPartisansSatisfaction()).isEqualTo(expectedInitialLoyalistPartisansSatisfaction);
    }

    @Test
    public void testGetInitialLoyalistPartisans() {
        int expectedInitialLoyalistPartisans = 20;
        Assertions.assertThat(scenario.getInitialLoyalistPartisans()).isEqualTo(expectedInitialLoyalistPartisans);
    }

    @Test
    public void testGetNextEvent() {
        Assertions.assertThat(scenario.getNextEvent(null).toString()).isEqualTo(expectedEvent1.toString());
        Assertions.assertThat(scenario.getNextEvent(null).toString()).isEqualTo(expectedEvent2.toString());
        Assertions.assertThat(scenario.getNextEvent(null).toString()).isEqualTo(expectedEvent1.toString());
    }

    @Test
    public void testGetEventManagerStep() {
        Assertions.assertThat(scenario.getEventManagerStep()).isEqualTo(0);
        scenario.getNextEvent(null);
        Assertions.assertThat(scenario.getEventManagerStep()).isEqualTo(1);
        scenario.getNextEvent(null);
        Assertions.assertThat(scenario.getEventManagerStep()).isEqualTo(0);
    }

    @Test
    public void testGetEventsCount() {
        Assertions.assertThat(scenario.getEventsCount()).isEqualTo(2);
    }

    @Test
    public void testGetInitialMoney() {
        int expectedInitialMoney = 2000;
        Assertions.assertThat(scenario.getInitialMoney()).isEqualTo(expectedInitialMoney);
    }

    @Test
    public void testGetInitialFood() {
        int expectedInitialFood = 3;
        Assertions.assertThat(scenario.getInitialFood()).isEqualTo(expectedInitialFood);
    }

    @Test
    public void testGetInitialIndustrialization() {
        int expectedInitialIndustrialization = 15;
        Assertions.assertThat(scenario.getInitialIndustrialization()).isEqualTo(expectedInitialIndustrialization);
    }

    @Test
    public void testGetInitialAgriculture() {
        int expectedInitialAgriculture = 10;
        Assertions.assertThat(scenario.getInitialAgriculture()).isEqualTo(expectedInitialAgriculture);
    }

    @Test
    public void testIsLastEvent() {
        Assertions.assertThat(scenario.isLastEvent()).isFalse();
        scenario.getNextEvent(null);
        Assertions.assertThat(scenario.isLastEvent()).isFalse();
        scenario.getNextEvent(null);
        Assertions.assertThat(scenario.isLastEvent()).isTrue();
    }
}