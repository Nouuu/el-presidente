package org.esgi.el_presidente.core.factions;

import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.factions.FactionType;
import org.junit.Test;

public class FactionTypeTest {

    @Test
    public void testFactionTypeLoyalistExists() {
        Assertions.assertThat(FactionType.loyalist).isNotNull();
    }

    @Test
    public void testFactionTypeToString() {
        Assertions.assertThat(FactionType.loyalist.toString()).isEqualTo("loyalistes");
    }

    @Test
    public void testGetFactionTypeFromString() {
        Assertions.assertThat(FactionType.valueOf("loyalist")).isEqualTo(FactionType.loyalist);
    }

    @Test
    public void testGetInexistantFactionTypeFromString() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> FactionType.valueOf("inexistant"));
    }
}
