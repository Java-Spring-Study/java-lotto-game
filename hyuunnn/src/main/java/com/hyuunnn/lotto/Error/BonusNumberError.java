package com.hyuunnn.lotto.Error;

import com.hyuunnn.lotto.Lotto;

public class BonusNumberError extends Error {

  public static void validateNumber(Lotto lottoList, String strNumber) {
    checkNumeric(strNumber);
    checkOverlap(lottoList, strNumber);
  }

  public static void checkOverlap(Lotto lottoList, String strNumber) {
    if (lottoList.getLottoList().contains(Integer.parseInt(strNumber))) {
      throw new IllegalStateException("보너스볼은 당첨 로또 번호 6개 숫자와 중복될 수 없습니다.");
    }
  }
}
