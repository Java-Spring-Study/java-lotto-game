package com.hyuunnn.lotto;

import static com.hyuunnn.lotto.Util.MINIMUM_LOTTO_PRICE;
import static com.hyuunnn.lotto.Util.SCANNER;
import static com.hyuunnn.lotto.Util.getLottoCount;
import static com.hyuunnn.lotto.Util.randomize;
import static com.hyuunnn.lotto.Util.strToArray;
import static com.hyuunnn.lotto.Util.strToInteger;
import static com.hyuunnn.lotto.Util.strToIntegerList;

import com.hyuunnn.lotto.Validator.PriceNumberValidator;
import com.hyuunnn.lotto.Validator.WinningNumberValidator;
import com.hyuunnn.lotto.Validator.BonusNumberValidator;
import com.hyuunnn.lotto.Util.InputType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class GamePlayer {

  private final List<Lotto> lottoList = new ArrayList<>();
  private WinningLotto winningLotto;
  private Lotto winningNumberList;
  private int bonusNumber;
  private int lottoCount;

  public void run() {
    input();
    printResult();
  }

  private void input() {
    System.out.println("구입 금액을 입력해주세요.");
    while (inputPrice() == InputType.INVALID)
      ;

    System.out.println("지난 주 당첨번호를 입력해 주세요.");
    while (inputWinningNumber() == InputType.INVALID)
      ;

    System.out.println("보너스 볼을 입력해 주세요.");
    while (inputBonusNumber() == InputType.INVALID)
      ;

    winningLotto = new WinningLotto(winningNumberList, bonusNumber);
  }

  private InputType inputPrice() {
    try {
      String strInputPrice = SCANNER.nextLine();
      PriceNumberValidator.validateNumber(strInputPrice);
      addLotto(strToInteger(strInputPrice));

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return InputType.INVALID;
    }
    return InputType.VALID;
  }

  private void addLotto(int price) {
    lottoCount = getLottoCount(price);
    System.out.printf("%d개를 구매했습니다.\n", lottoCount);

    IntStream.range(0, lottoCount).forEach(i -> {
      lottoList.add(new Lotto(randomize()));
      System.out.println(lottoList.get(i));
    });
  }

  private InputType inputWinningNumber() {
    try {
      String strInputWinningNumber = SCANNER.nextLine();
      WinningNumberValidator.validateNumber(strToArray(strInputWinningNumber));
      winningNumberList = new Lotto(strToIntegerList(strInputWinningNumber));

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return InputType.INVALID;
    }
    return InputType.VALID;
  }

  private InputType inputBonusNumber() {
    try {
      String strInputBonusNumber = SCANNER.nextLine();
      BonusNumberValidator.validateNumber(winningNumberList, strInputBonusNumber);
      bonusNumber = strToInteger(strInputBonusNumber);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return InputType.INVALID;
    }
    return InputType.VALID;
  }

  private void printResult() {
    System.out.println("당첨 통계");
    System.out.println("---------");

    HashMap<Integer, Integer> rankMap = getRankMap();
    printRankMap(rankMap);

    System.out.printf("총 수익률은 %.3f입니다.",
        (double) getTotalPrice(rankMap) / (lottoCount * MINIMUM_LOTTO_PRICE));
  }

  private HashMap<Integer, Integer> getRankMap() {
    HashMap<Integer, Integer> rankMap = new HashMap<>();
    lottoList.forEach(lotto -> {
      Optional<Rank> lottoRank = winningLotto.match(lotto);
      lottoRank.ifPresent(rank -> rankMap.merge(rank.getPrice(), 1, Integer::sum));
    });
    return rankMap;
  }

  private void printRankMap(HashMap<Integer, Integer> rankMap) {
    Arrays.stream(Rank.values())
        .forEach(rank -> {
          Optional<Integer> priceCount = Optional.ofNullable(rankMap.get(rank.getPrice()));
          System.out.printf("%d개 일치 (%d원)-%d개\n", rank.getMatch(), rank.getPrice(),
              priceCount.orElse(0));
        });
  }

  private int getTotalPrice(HashMap<Integer, Integer> rankMap) {
    return Arrays.stream(Rank.values())
        .map(rank -> Optional.ofNullable(rankMap.get(rank.getPrice())).orElse(0)
            * rank.getPrice())
        .reduce(0, Integer::sum);
  }
}
