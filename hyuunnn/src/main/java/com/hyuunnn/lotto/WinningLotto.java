package com.hyuunnn.lotto;

import java.util.Arrays;
import java.util.Optional;

/**
 * 당첨 번호를 담당하는 객체
 */
public class WinningLotto {

  private final Lotto lotto;
  private final int bonusNo;

  public WinningLotto(Lotto lotto, int bonusNo) {
    this.lotto = lotto;
    this.bonusNo = bonusNo;
  }

  public Optional<Rank> match(Lotto userLotto) {
    int matchCount = getMatchCount(userLotto);
    boolean bonusMatch = isBonusMatch(userLotto);
    return getRank(matchCount, bonusMatch);
  }

  private int getMatchCount(Lotto userLotto) {
    return (int) lotto.getLottoList().stream()
        .filter(e -> userLotto.getLottoList().contains(e))
        .count();
  }

  private boolean isBonusMatch(Lotto userLotto) {
    return userLotto.getLottoList().contains(bonusNo);
  }

  private Optional<Rank> getRank(int matchCount, boolean bonusMatch) {
    return Arrays.stream(Rank.values())
        .filter(rank -> rank.isMatch(matchCount, bonusMatch))
        .findFirst();
  }
}
