package com.hyuunnn.lotto;

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

  public Rank match(Lotto userLotto) {
    int matchCount = getMatchCount(userLotto);
    return getRank(userLotto, matchCount, bonusNo);
  }

  private int getMatchCount(Lotto userLotto) {
    return (int) lotto.getLottoList().stream()
        .filter(e -> userLotto.getLottoList().contains(e))
        .count();
  }

  private Rank getRank(Lotto userLotto, int matchCount, int bonusNumber) {
    if (matchCount == 3) {
      return Rank.FIFTH;
    } else if (matchCount == 4) {
      return Rank.FOURTH;
    } else if (matchCount == 5) {
      return checkBonusNumber(userLotto, bonusNumber);
    } else if (matchCount == 6) {
      return Rank.FIRST;
    }
    return null;
  }

  private Rank checkBonusNumber(Lotto userLotto, int bonusNumber) {
    if (userLotto.getLottoList().contains(bonusNumber)) {
      return Rank.SECOND;
    }
    return Rank.THIRD;
  }
}
