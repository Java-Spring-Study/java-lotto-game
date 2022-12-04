package com.hyuunnn.lotto;

import static com.hyuunnn.lotto.Util.MINIMUM_LOTTO_PRICE;
import static com.hyuunnn.lotto.Util.SCANNER;
import static com.hyuunnn.lotto.Util.getLottoCount;
import static com.hyuunnn.lotto.Util.randomize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class GamePlayer extends GameInput {

  private final List<Lotto> lottoList = new ArrayList<>();
  private WinningLotto winningLotto;
  private int lottoCount;

  public void run() {
    input();
    printResult();
    SCANNER.close();
  }

  private void input() {
    System.out.println("구입 금액을 입력해주세요.");
    addLotto(inputPrice());

    System.out.println("지난 주 당첨번호를 입력해 주세요.");
    List<Integer> winningNumberList = inputWinningNumber();

    System.out.println("보너스 볼을 입력해 주세요.");
    int bonusNumber = inputBonusNumber(winningNumberList);

    winningLotto = new WinningLotto(new Lotto(winningNumberList), bonusNumber);
  }

  private void addLotto(int price) {
    lottoCount = getLottoCount(price);
    System.out.printf("%d개를 구매했습니다.\n", lottoCount);

    IntStream.range(0, lottoCount).forEach(i -> {
      lottoList.add(new Lotto(randomize()));
      System.out.println(lottoList.get(i));
    });
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
