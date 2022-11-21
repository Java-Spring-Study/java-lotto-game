package com.hyuunnn.lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.hyuunnn.lotto.Error.BonusNumberError;
import com.hyuunnn.lotto.Error.PriceNumberError;
import com.hyuunnn.lotto.Error.WinningNumberError;

public class ErrorTest {

  private Throwable exception;

  @Test
  @DisplayName("구매 금액 입력 에러 테스트")
  public void PriceNumberErrorTest() {
    TryPriceNumberException("-1", "돈은 음수가 될 수 없습니다.");
    TryPriceNumberException("0", "구매할 돈이 부족합니다.");
    TryPriceNumberException("1", "구매할 돈이 부족합니다.");
    TryPriceNumberException("100", "구매할 돈이 부족합니다.");
    TryPriceNumberException("999", "구매할 돈이 부족합니다.");
    TryPriceNumberException("100000000000000", "64비트 정수입력 범위를 벗어났습니다.");
    TryPriceNumberException("asdf", "64비트 정수입력 범위를 벗어났습니다.");
  }

  private void TryPriceNumberException(String strNumber, String msg) {
    exception = assertThrows(RuntimeException.class, () -> {
      PriceNumberError.validateNumber(strNumber);
    });
    assertEquals(msg, exception.getMessage());
  }

  @Test
  @DisplayName("당첨번호 입력 에러 테스트")
  public void WinningNumberErrorTest() {
    TryWinningNumberException(new String[]{"-1", "46", "-3", "47", "-5", "0"},
        "로또 숫자 범위는 1~45 입니다.");
    TryWinningNumberException(new String[]{"46", "-1"}, "로또 숫자 범위는 1~45 입니다.");
    TryWinningNumberException(new String[]{"34", "20"}, "로또는 6개의 숫자가 필요합니다.");
    TryWinningNumberException(new String[]{"34", "20", "18", "43", "45"}, "로또는 6개의 숫자가 필요합니다.");
    TryWinningNumberException(new String[]{"34", "20", "18", "43", "45", "9", "10"},
        "로또는 6개의 숫자가 필요합니다.");
    TryWinningNumberException(new String[]{"1", "1", "1", "1", "1", "1"}, "로또 숫자는 서로 중복될 수 없습니다.");
    TryWinningNumberException(new String[]{"34", "20", "18", "43", "45", "34"},
        "로또 숫자는 서로 중복될 수 없습니다.");
    TryWinningNumberException(new String[]{"aa", "bb", "44"}, "64비트 정수입력 범위를 벗어났습니다.");
  }

  private void TryWinningNumberException(String[] strList, String msg) {
    exception = assertThrows(RuntimeException.class, () -> {
      WinningNumberError.validateNumber(strList);
    });
    assertEquals(msg, exception.getMessage());
  }

  @Test
  @DisplayName("보너스 볼 입력 에러 테스트")
  public void BonusNumberErrorTest() {

    Lotto lotto = new Lotto(Arrays.asList(34, 20, 18, 43, 45, 9));

    TryBonusNumberException(lotto, "-1", "로또 숫자 범위는 1~45 입니다.");
    TryBonusNumberException(lotto, "0", "로또 숫자 범위는 1~45 입니다.");
    TryBonusNumberException(lotto, "9", "보너스볼은 당첨 로또 번호 6개 숫자와 중복될 수 없습니다.");
    TryBonusNumberException(lotto, "20", "보너스볼은 당첨 로또 번호 6개 숫자와 중복될 수 없습니다.");
    TryBonusNumberException(lotto, "100", "로또 숫자 범위는 1~45 입니다.");
    TryBonusNumberException(lotto, "100000000000000", "64비트 정수입력 범위를 벗어났습니다.");
    TryBonusNumberException(lotto, "asdf", "64비트 정수입력 범위를 벗어났습니다.");
  }

  private void TryBonusNumberException(Lotto lotto, String strNumber, String msg) {
    exception = assertThrows(RuntimeException.class, () -> {
      BonusNumberError.validateNumber(lotto, strNumber);
    });
    assertEquals(msg, exception.getMessage());
  }
}
