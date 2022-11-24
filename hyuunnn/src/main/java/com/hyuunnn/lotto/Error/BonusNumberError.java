package com.hyuunnn.lotto.Error;

import com.hyuunnn.lotto.Lotto;

public class BonusNumberError extends Error {

  public static void validateNumber(Lotto lottoList, String strNumber) {
    checkNumeric(strNumber);
    int bonusNumber = Integer.parseInt(strNumber);
    checkRange(bonusNumber);
    checkOverlap(lottoList, bonusNumber);
  }

  public static void checkOverlap(Lotto lottoList, int bonusNumber) {
    if (lottoList.getLottoList().contains(bonusNumber)) {
      throw new IllegalStateException(ErrorMessage.INVALID_OVERLAP.get());
    }
  }

  enum ErrorMessage {
    INVALID_OVERLAP("보너스볼은 당첨 로또 번호 6개 숫자와 중복될 수 업습니다.");

    private final String message;

    ErrorMessage(String message) {
      this.message = message;
    }

    public String get() {
      return message;
    }
  }
}
