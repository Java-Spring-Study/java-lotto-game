package com.hyuunnn.lotto;

public enum Rank {
  FIFTH(3, 5_000),
  FOURTH(4, 50_000),
  THIRD(5, 1_500_000),
  SECOND(5, 30_000_000),
  FIRST(6, 2_000_000_000);

  private int match;
  private int price;

  Rank(int match, int price) {
    this.match = match;
    this.price = price;
  }

  public int getMatch() {
    return this.match;
  }

  public int getPrice() {
    return this.price;
  }
}
