package domain;

import static domain.Rank.SECOND_PRICE;

import java.util.concurrent.atomic.AtomicInteger;
public class WinningLotto {

  private final Lotto lotto;
  private final int bonusNo;

  public WinningLotto(Lotto lotto, int bonusNo) {
    this.lotto = lotto;
    this.bonusNo = bonusNo;
  }

  public Rank match(Lotto userLotto) {
    int count = (int) userLotto.getLotto().stream()
        .filter(i -> lotto.getLotto().contains(i))
        .count();

    boolean isBonusMatch = userLotto.getLotto().stream()
        .anyMatch(i -> i == bonusNo);

    return Rank.value(count, isBonusMatch);
  }
}
