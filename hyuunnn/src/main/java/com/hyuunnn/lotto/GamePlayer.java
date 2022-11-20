package com.hyuunnn.lotto;

import static com.hyuunnn.lotto.Util.MINIMUM_LOTTO_PRICE;
import static com.hyuunnn.lotto.Util.SCANNER;
import static com.hyuunnn.lotto.Util.SEPARATOR;
import static com.hyuunnn.lotto.Util.randomize;

import com.hyuunnn.lotto.Error.PriceNumberError;
import com.hyuunnn.lotto.Error.WinningNumberError;
import com.hyuunnn.lotto.Error.BonusNumberError;
import com.hyuunnn.lotto.Util.InputType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class GamePlayer {

  private final List<Lotto> lottoList = new ArrayList<>();
  private WinningLotto winninglotto;
  private Lotto WinningNumberList;
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

    winninglotto = new WinningLotto(WinningNumberList, bonusNumber);
  }

  private InputType inputPrice() {
    try {
      String strInputPrice = SCANNER.nextLine();
      PriceNumberError.validateNumber(strInputPrice);
      addLotto(Integer.parseInt(strInputPrice));

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

  public static int getLottoCount(int price) {
    return price / MINIMUM_LOTTO_PRICE;
  }

  private InputType inputWinningNumber() {
    try {
      String strInputWinningNumber = SCANNER.nextLine();
      String[] strWinningNumberList = strInputWinningNumber.split(SEPARATOR);
      WinningNumberError.validateNumber(strWinningNumberList);

      WinningNumberList = new Lotto(Arrays.stream(strWinningNumberList)
          .mapToInt(Integer::parseInt)
          .boxed()
          .toList());

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return InputType.INVALID;
    }
    return InputType.VALID;
  }

  private InputType inputBonusNumber() {
    try {
      String strInputBonusNumber = SCANNER.nextLine();
      BonusNumberError.validateNumber(WinningNumberList, strInputBonusNumber);
      bonusNumber = Integer.parseInt(strInputBonusNumber);

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
    lottoList.forEach(lottoObj -> {
      Rank lottoRank = winninglotto.match(lottoObj);
      rankMap.merge(lottoRank.getMatch(), 1, Integer::sum);
    });
    return rankMap;
  }

  private void printRankMap(HashMap<Integer, Integer> rankMap) {
    Arrays.stream(Rank.values())
        .filter(rankObj -> rankObj != Rank.NONE)
        .forEach(rankObj -> {
          Optional<Integer> priceCount = Optional.ofNullable(rankMap.get(rankObj.getMatch()));
          System.out.printf("%d개 일치 (%d원)-%d개\n", rankObj.getMatch(), rankObj.getPrice(),
              priceCount.orElse(0));
        });
  }

  private int getTotalPrice(HashMap<Integer, Integer> rankMap) {
    return Arrays.stream(Rank.values())
        .filter(rankObj -> rankMap.get(rankObj.getMatch()) != null)
        .map(rankObj -> rankMap.get(rankObj.getMatch()) * rankObj.getPrice())
        .reduce(0, Integer::sum);
  }
}
