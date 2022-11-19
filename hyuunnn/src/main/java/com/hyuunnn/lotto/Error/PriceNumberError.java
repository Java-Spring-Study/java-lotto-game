package com.hyuunnn.lotto.Error;

import static com.hyuunnn.lotto.Util.MINIMUM_LOTTO_PRICE;

public class PriceNumberError extends Error {

  public static void validateNumber(String strNumber) {
    checkNumeric(strNumber);
    int price = Integer.parseInt(strNumber);
    checkNegativePrice(price);
    checkNumberPrice(price);
  }

  public static void checkNegativePrice(int price) {
    if (price < 0) {
      throw new IllegalStateException("돈은 음수가 될 수 없습니다.");
    }
  }

  public static void checkNumberPrice(int price) {
    if (price < MINIMUM_LOTTO_PRICE) {
      throw new IllegalStateException("구매할 돈이 부족합니다.");
    }
  }
}
