package com.hyuunnn.lotto.Validator;

import static com.hyuunnn.lotto.Util.LOTTO_LENGTH;

import java.util.Arrays;

public class WinningNumberValidator extends Validator {

  public static void validateNumber(String[] strList) {
    checkIntegerList(strList);
    checkRangeList(strList);
    checkLength(strList);
    checkOverlap(strList);
  }

  public static void checkIntegerList(String[] strList) {
    Arrays.stream(strList)
        .forEach(str -> checkInteger(str));
  }

  public static void checkRangeList(String[] strList) {
    Arrays.stream(strList)
        .mapToInt(Integer::parseInt)
        .forEach(n -> checkRange(n));
  }

  public static void checkLength(String[] strList) {
    if (strList.length != LOTTO_LENGTH) {
      throw new IllegalStateException(ErrorMessage.INVALID_LENGTH.get());
    }
  }

  public static void checkOverlap(String[] strList) {
    if (strList.length != Arrays.stream(strList).distinct().count()) {
      throw new IllegalStateException(ErrorMessage.INVALID_OVERLAP.get());
    }
  }

  enum ErrorMessage {
    INVALID_OVERLAP("로또 숫자는 서로 중복될 수 없습니다."),
    INVALID_LENGTH("로또는 6개의 숫자가 필요합니다.");

    private final String message;

    ErrorMessage(String message) {
      this.message = message;
    }

    public String get() {
      return message;
    }
  }
}
