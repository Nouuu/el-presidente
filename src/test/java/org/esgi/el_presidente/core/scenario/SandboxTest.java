package org.esgi.el_presidente.core.scenario;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.season.Season;
import org.junit.Before;
import org.junit.Test;

public class SandboxTest {

    private final String sandboxPath = "test/sandboxTest.json";
    private Sandbox sandbox;

    @Before
    public void setUp() throws JsonProcessingException {
        sandbox = Sandbox.createFromJson(sandboxPath);
    }

    @Test
    public void createFromJson() throws JsonProcessingException {
        Assertions.assertThat((Sandbox.createFromJson(sandboxPath)))
                .isInstanceOf(Sandbox.class)
                .isInstanceOf(Scenario.class);
    }

    @Test
    public void createFromJsonAsScenario() throws JsonProcessingException {
        Scenario scenario = Sandbox.createFromJson(sandboxPath);
        Assertions.assertThat(scenario)
                .isInstanceOf(Sandbox.class)
                .isInstanceOf(Scenario.class);
    }

    @Test
    public void getNextEvent() {
        for (int i = 0; i < 10; i++) {
            Assertions.assertThat(sandbox.getNextEvent(Season.spring).getSeason()).isEqualTo(Season.spring);
        }
    }

    @Test
    public void getNextEventAsScenario() {
        Scenario scenario = sandbox;
        for (int i = 0; i < 10; i++) {
            Assertions.assertThat(scenario.getNextEvent(Season.winter).getSeason()).isEqualTo(Season.winter);
        }
    }

    @Test
    public void testIsLastEvent() {
        for (int i = 0; i < sandbox.getEventsCount() * 2; i++) {
            Assertions.assertThat(sandbox.isLastEvent()).isFalse();
            sandbox.getNextEvent(Season.winter);
        }
    }

    @Test
    public void testIsLastEventAsScenario() {
        Scenario scenario = sandbox;

        for (int i = 0; i < scenario.getEventsCount() * 2; i++) {
            Assertions.assertThat(scenario.isLastEvent()).isFalse();
            scenario.getNextEvent(Season.winter);
        }
    }

    @Test
    public void testGetEventManagerStep() {
        for (int i = 0; i < sandbox.getEventsCount() * 2; i++) {
            Assertions.assertThat(sandbox.getEventManagerStep()).isEqualTo(0);
            sandbox.getNextEvent(Season.winter);
        }
    }

    @Test
    public void testGetEventManagerStepAsScenario() {
        Scenario scenario = sandbox;

        for (int i = 0; i < scenario.getEventsCount() * 2; i++) {
            Assertions.assertThat(scenario.getEventManagerStep()).isEqualTo(0);
            scenario.getNextEvent(Season.winter);
        }

    }
}