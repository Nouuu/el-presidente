package org.esgi.el_presidente.core.game;

import org.esgi.el_presidente.core.season.Season;

public class TimeManager {
  private Season season;
  private int seasonCount;

  public TimeManager() {
    season = Season.spring;
    seasonCount = 0;
  }

  public int getCurrentYear() {
    // should we start at year 1 ?
    return seasonCount / 4;
  }

  public void nextSeason() {
    seasonCount += 1;
    if (season == Season.winter) {
      season = Season.spring;
    } else {
      int currentSeasonIndex = season.ordinal();
      season = Season.values()[currentSeasonIndex + 1];
    }

  }

  public Season getSeason() {
    return season;
  }

  public int getSeasonCount() {
    return seasonCount;
  }

  public boolean isTheEndOfTheYear() {
    return season == Season.winter;
  }
}
