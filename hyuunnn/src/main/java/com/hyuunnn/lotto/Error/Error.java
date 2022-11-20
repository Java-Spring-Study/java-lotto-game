package com.hyuunnn.lotto.Error;

import static com.hyuunnn.lotto.Util.MAX_LOTTO_RANGE;
import static com.hyuunnn.lotto.Util.MIN_LOTTO_RANGE;
import static com.hyuunnn.lotto.Util.isNumeric;

import com.hyuunnn.lotto.Util.InputType;

public class Error {

  public static void checkNumeric(String strNumber) {
    if (isNumeric(strNumber) == InputType.INVALID) {
      throw new IllegalStateException("64비트 정수입력 범위를 벗어났습니다.");
    }
  }

  public static void checkRange(int lottoNumber) {
    if (lottoNumber < MIN_LOTTO_RANGE || lottoNumber > MAX_LOTTO_RANGE) {
      throw new IllegalStateException("로또 숫자 범위는 1~45 입니다.");
    }
  }
}
