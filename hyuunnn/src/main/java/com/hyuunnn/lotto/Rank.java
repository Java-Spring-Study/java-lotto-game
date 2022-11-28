package com.hyuunnn.lotto;

public enum Rank {
  FIFTH(3, 5_000, Bonus.NONE),
  FOURTH(4, 50_000, Bonus.NONE),
  THIRD(5, 1_500_000, Bonus.MISMATCH),
  SECOND(5, 30_000_000, Bonus.MATCH),
  FIRST(6, 2_000_000_000, Bonus.NONE);

  private final int match;
  private final int price;
  private final Bonus bonus;

  Rank(int match, int price, Bonus bonus) {
    this.match = match;
    this.price = price;
    this.bonus = bonus;
  }

  public int getMatch() {
    return this.match;
  }

  public int getPrice() {
    return this.price;
  }

  public boolean isMatch(int matchCount, boolean bonusMatch) {
    return match == matchCount && bonus.isMatch(bonusMatch);
  }

  enum Bonus {
    MATCH(true),
    MISMATCH(false),
    NONE(true);

    private final boolean match;

    Bonus(boolean match) {
      this.match = match;
    }

    public boolean isMatch(boolean bonusMatch) {
      if (this == Bonus.NONE) {
        return true;
      }
      return match == bonusMatch;
    }
  }
}
