package com.hyuunnn.lotto;

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
    return getRank(userLotto, matchCount, bonusNo);
  }

  private int getMatchCount(Lotto userLotto) {
    return (int) lotto.getLottoList().stream()
        .filter(e -> userLotto.getLottoList().contains(e))
        .count();
  }

  private Optional<Rank> getRank(Lotto userLotto, int matchCount, int bonusNumber) {
    if (matchCount == 3) {
      return Optional.ofNullable(Rank.FIFTH);
    } else if (matchCount == 4) {
      return Optional.ofNullable(Rank.FOURTH);
    } else if (matchCount == 5) {
      return Optional.ofNullable(checkBonusNumber(userLotto, bonusNumber));
    } else if (matchCount == 6) {
      return Optional.ofNullable(Rank.FIRST);
    }
    return Optional.empty();
  }

  private Rank checkBonusNumber(Lotto userLotto, int bonusNumber) {
    if (userLotto.getLottoList().contains(bonusNumber)) {
      return Rank.SECOND;
    }
    return Rank.THIRD;
  }
}
