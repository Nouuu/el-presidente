package org.esgi.el_presidente.core.scenario;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

public class ScenarioListTest {

    @Test
    public void testSandBoxExists() {
        Assertions.assertThat(ScenarioList.valueOf("SANDBOX")).isNotNull();
    }

    @Test
    public void getName() {
        Assertions.assertThat(ScenarioList.SANDBOX.getName()).isEqualTo("Bac à sable");
    }

    @Test
    public void getPath() {
        Assertions.assertThat(ScenarioList.SANDBOX.getPath()).isEqualTo("sandbox.json");
    }

    @Test
    public void testToString() {
        Arrays.stream(ScenarioList.values()).forEach(s -> Assertions.assertThat(s.toString()).isEqualTo(s.getName()));
    }
}