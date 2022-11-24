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
      throw new IllegalStateException(ErrorMessage.INVALID_PRICE_RANGE.get());
    }
  }

  public static void checkNumberPrice(int price) {
    if (price < MINIMUM_LOTTO_PRICE) {
      throw new IllegalStateException(ErrorMessage.INVALID_PRICE.get());
    }
  }

  enum ErrorMessage {
    INVALID_PRICE_RANGE("돈은 음수가 될 수 없습니다."),
    INVALID_PRICE("구매할 돈이 부족합니다.");

    private final String message;

    ErrorMessage(String message) {
      this.message = message;
    }

    public String get() {
      return message;
    }
  }
}
