package com.hyuunnn.lotto;

import static com.hyuunnn.lotto.Util.SCANNER;
import static com.hyuunnn.lotto.Util.strToArray;
import static com.hyuunnn.lotto.Util.strToInteger;
import static com.hyuunnn.lotto.Util.strToIntegerList;

import com.hyuunnn.lotto.Validator.BonusNumberValidator;
import com.hyuunnn.lotto.Validator.PriceNumberValidator;
import com.hyuunnn.lotto.Validator.WinningNumberValidator;
import java.util.List;

public class GameInput {

  public int inputPrice() {
    while (true) {
      try {
        String strInputPrice = SCANNER.nextLine();
        PriceNumberValidator.validateNumber(strInputPrice);
        return strToInteger(strInputPrice);

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public List<Integer> inputWinningNumber() {
    while (true) {
      try {
        String strInputWinningNumber = SCANNER.nextLine();
        WinningNumberValidator.validateNumber(strToArray(strInputWinningNumber));
        return strToIntegerList(strInputWinningNumber);

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public int inputBonusNumber(List<Integer> winningNumberList) {
    while (true) {
      try {
        String strInputBonusNumber = SCANNER.nextLine();
        BonusNumberValidator.validateNumber(winningNumberList, strInputBonusNumber);
        return strToInteger(strInputBonusNumber);

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
