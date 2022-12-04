package com.hyuunnn.lotto.Validator;

import static com.hyuunnn.lotto.Util.MAX_LOTTO_RANGE;
import static com.hyuunnn.lotto.Util.MIN_LOTTO_RANGE;
import static com.hyuunnn.lotto.Util.strToInteger;

public class Validator {

  public static void checkInteger(String strNumber) {
    if (isNotInteger(strNumber)) {
      throw new IllegalStateException(ErrorMessage.INVALID_NUMBER.get());
    }
  }

  public static boolean isNotInteger(String str) {
    try {
      strToInteger(str);
    } catch (NumberFormatException e) {
      return true;
    }
    return false;
  }

  public static void checkRange(int lottoNumber) {
    if (lottoNumber < MIN_LOTTO_RANGE || lottoNumber > MAX_LOTTO_RANGE) {
      throw new IllegalStateException(ErrorMessage.INVALID_RANGE.get());
    }
  }

  enum ErrorMessage {
    INVALID_NUMBER("32비트 정수입력 범위를 벗어났습니다."),
    INVALID_RANGE("로또 숫자 범위는 1~45 입니다.");

    private final String message;

    ErrorMessage(String message) {
      this.message = message;
    }

    public String get() {
      return message;
    }
  }
}
