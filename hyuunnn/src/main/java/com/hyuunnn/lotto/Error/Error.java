package com.hyuunnn.lotto.Error;

import static com.hyuunnn.lotto.Util.isNumeric;

import com.hyuunnn.lotto.Util.InputType;

public class Error {

  public static void checkNumeric(String strNumber) {
    if (isNumeric(strNumber) == InputType.INVALID) {
      throw new IllegalStateException("64비트 정수입력 범위를 벗어났습니다.");
    }
  }
}
