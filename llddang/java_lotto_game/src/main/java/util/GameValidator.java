package util;

import static util.Constant.MAXIMUM_LOTTO_NUMBER;
import static util.Constant.MINIMUM_LOTTO_NUMBER;
import static util.Constant.TICKET_PRICE;

import domain.Lotto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameValidator {

  public static boolean isInvalidMoney(String money) {
    try {
      checkLongNumber(money);
      long longMoney = Long.parseLong(money);
      checkNegativeNumber(longMoney);
      checkUnderTicketPrice(longMoney);
    } catch (IllegalArgumentException e) {
      return true;
    }
    return false;
  }

  private static void checkLongNumber(String money) {
    try {
      Long.parseLong(money);
    } catch (Exception e) {
      printErrorMessage("64비트 정수입력 범위를 벗어났습니다.");
      throw new IllegalArgumentException();
    }
  }

  private static void checkNegativeNumber(long money) {
    if (money < 0) {
      printErrorMessage("돈은 음수가 될 수 없습니다.");
      throw new IllegalArgumentException();
    }
  }

  private static void checkUnderTicketPrice(long money) {
    if (money < TICKET_PRICE) {
      printErrorMessage("구매할 돈이 부족이 부족합니다.");
      throw new IllegalArgumentException();
    }
  }

  public static boolean isInvalidLottoNumber(List<String> lotto) {
    try {
      checkLottoSize(lotto);
      checkDuplicate(lotto);
      checkLottoNumber(lotto);
    } catch (Exception e) {
      return true;
    }
    return false;
  }

  private static void checkLottoSize(List<String> data) {
    if (data.size() != 6) {
      printErrorMessage("로또는 6개의 숫자가 필요합니다.");
      throw new IllegalStateException();
    }
  }

  private static void checkDuplicate(List<String> data) {
    Set<String> dataSet = new HashSet<>(data);
    if (dataSet.size() != data.size()) {
      printErrorMessage("로또 숫자는 서로 중복될 수 없습니다.");
      throw new IllegalStateException();
    }
  }

  private static void checkLottoNumber(List<String> data) {
    try {
      data.stream()
          .forEach(ball -> checkIntNumber(ball));
      data.stream()
          .forEach(ball -> checkValidBall(ball));
    } catch (Exception e) {
      throw new IllegalStateException();
    }
  }

  public static boolean isInvalidBonusBall(String ball, Lotto lotto){
    try {
      checkIntNumber(ball);
      checkValidBall(ball);
      checkDuplicateLottoNumber(ball, lotto);
    } catch (Exception e) {
      return true;
    }
    return false;
  }

  private static void checkIntNumber(String ball) {
    try {
      Integer.parseInt(ball);
    } catch (Exception e) {
      printErrorMessage("32비트 정수입력 범위를 벗어났습니다.");
      throw new IllegalArgumentException();
    }
  }

  private static void checkValidBall(String data) {
    int ball = Integer.parseInt(data);
    if (ball < MINIMUM_LOTTO_NUMBER || ball > MAXIMUM_LOTTO_NUMBER) {
      printErrorMessage("로또 숫자 범위가 아닙니다.");
      throw new IllegalStateException();
    }
  }

  private static void checkDuplicateLottoNumber(String data, Lotto lotto){
    int ball = Integer.parseInt(data);
    boolean checkDuplicate = lotto.getLotto().stream().anyMatch(lo -> lo == ball);
    if(checkDuplicate){
      printErrorMessage("보너스볼은 당첨 로또 번호 6개 숫자와 중복될 수 없습니다.");
      throw new IllegalStateException();
    }
  }

  private static void printErrorMessage(String error) {
    System.out.println(error);
  }

}
