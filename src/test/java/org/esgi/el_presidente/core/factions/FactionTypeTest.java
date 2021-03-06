package org.esgi.el_presidente.core.factions;

import org.assertj.core.api.Assertions;
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

    @Test
    public void fromString() {
        Assertions.assertThat(FactionType.fromString("capitalistes")).isEqualTo(FactionType.capitalist);
        Assertions.assertThat(FactionType.fromString("communistes")).isEqualTo(FactionType.communist);
        Assertions.assertThat(FactionType.fromString("religieux")).isEqualTo(FactionType.religious);
    }
}
