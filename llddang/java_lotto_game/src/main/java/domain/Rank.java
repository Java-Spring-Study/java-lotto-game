package domain;

public enum Rank {
  FIRST_PRICE(6, 200000000),
  SECOND_PRICE(5, 30000000),
  THIRD_PRICE(5, 1500000),
  FOURTH_PRICE(4, 50000),
  FIFTH_PRICE(3, 5000),
  MISS(0, 0);

  private static final int WINNING_MIN_COUNT = 3;

  private int matchCount;
  private int winningMoney;

  Rank(int matchCount, int winningMoney) {
    this.matchCount = matchCount;
    this.winningMoney = winningMoney;
  }

  public int getMatchCount() {
    return matchCount;
  }

  public int getWinningMoney() {
    return winningMoney;
  }

  public static Rank value(int matchCount, boolean matchBonus) {
    if (matchCount < WINNING_MIN_COUNT) {
      return MISS;
    }else if (SECOND_PRICE.isCountMatch(matchCount) && matchBonus) {
      return SECOND_PRICE;
    } else if (SECOND_PRICE.isCountMatch(matchCount) && !matchBonus){
      return THIRD_PRICE;
    }
    for(Rank rank : values()){
      if(rank.isCountMatch(matchCount)){
        return rank;
      }
    }
    return null;
  }

  private boolean isCountMatch(int matchCount) {
    return this.matchCount == matchCount;
  }

  public void printResult(Long totalCount) {
    System.out.println(matchCount + "개 일치 " + "(" + winningMoney + "원)-" + totalCount + "개");
  }
}
