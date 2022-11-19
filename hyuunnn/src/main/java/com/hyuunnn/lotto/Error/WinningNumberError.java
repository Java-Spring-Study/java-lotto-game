package com.hyuunnn.lotto.Error;

import static com.hyuunnn.lotto.Util.LOTTO_LENGTH;
import static com.hyuunnn.lotto.Util.MAX_LOTTO_RANGE;
import static com.hyuunnn.lotto.Util.MIN_LOTTO_RANGE;

import java.util.Arrays;

public class WinningNumberError extends Error {

  public static void validateNumber(String[] strList) {
    checkNumericList(strList);
    checkRangeList(strList);
    checkLength(strList);
    checkOverlap(strList);
  }

  public static void checkNumericList(String[] strList) {
    Arrays.stream(strList)
        .forEach(str -> checkNumeric(str));
  }

  public static void checkRangeList(String[] strList) {
    Arrays.stream(strList)
        .mapToInt(Integer::parseInt)
        .forEach(n -> checkRange(n));
  }

  public static void checkRange(int lottoNumber) {
    if (lottoNumber < MIN_LOTTO_RANGE || lottoNumber > MAX_LOTTO_RANGE) {
      throw new IllegalStateException("로또 숫자 범위는 1~45 입니다.");
    }
  }

  public static void checkLength(String[] strList) {
    if (strList.length != LOTTO_LENGTH) {
      throw new IllegalStateException("로또는 6개의 숫자가 필요합니다.");
    }
  }

  public static void checkOverlap(String[] strList) {
    if (strList.length != Arrays.stream(strList).distinct().count()) {
      throw new IllegalStateException("로또 숫자는 서로 중복될 수 없습니다.");
    }
  }
}
