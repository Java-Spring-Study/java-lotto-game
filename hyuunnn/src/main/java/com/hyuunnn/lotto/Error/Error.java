package com.hyuunnn.lotto.Error;

import static com.hyuunnn.lotto.Util.MAX_LOTTO_RANGE;
import static com.hyuunnn.lotto.Util.MIN_LOTTO_RANGE;
import static com.hyuunnn.lotto.Util.isInteger;

import com.hyuunnn.lotto.Util.InputType;

public class Error {

  public static void checkNumeric(String strNumber) {
    if (isInteger(strNumber) == InputType.INVALID) {
      throw new IllegalStateException(ErrorMessage.INVALID_NUMBER.get());
    }
  }

  public static void checkRange(int lottoNumber) {
    if (lottoNumber < MIN_LOTTO_RANGE || lottoNumber > MAX_LOTTO_RANGE) {
      throw new IllegalStateException(ErrorMessage.INVALID_RANGE.get());
    }
  }

  enum ErrorMessage {
    INVALID_NUMBER("64비트 정수입력 범위를 벗어났습니다."),
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
