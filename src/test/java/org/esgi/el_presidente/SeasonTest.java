package org.esgi.el_presidente;

public class SeasonTest {

    @Test
    public void testSeasonsCount4() {
        Assertions.assertThat(Arrays.stream(Season.values()).count()).isEqualTo(4);
    }

    @Test
    public void testSeasonToString() {
        Assertions.assertThat(Season.summer.toString()).isEqualTo("été");
    }

}
