package com.hyuunnn.lotto.Validator;

import static com.hyuunnn.lotto.Util.strToInteger;

import com.hyuunnn.lotto.Lotto;
import java.util.List;

public class BonusNumberValidator extends Validator {

  public static void validateNumber(List<Integer> lottoList, String strNumber) {
    checkInteger(strNumber);
    int bonusNumber = strToInteger(strNumber);
    checkRange(bonusNumber);
    checkOverlap(lottoList, bonusNumber);
  }

  public static void checkOverlap(List<Integer> lottoList, int bonusNumber) {
    if (lottoList.contains(bonusNumber)) {
      throw new IllegalStateException(ErrorMessage.INVALID_OVERLAP.get());
    }
  }

  enum ErrorMessage {
    INVALID_OVERLAP("보너스볼은 당첨 로또 번호 6개 숫자와 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
      this.message = message;
    }

    public String get() {
      return message;
    }
  }
}
