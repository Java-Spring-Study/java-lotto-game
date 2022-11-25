import static domain.Lotto.getRandomNumbers;
import static util.Constant.TICKET_PRICE;
import static util.GameValidator.isInvalidBonusBall;
import static util.GameValidator.isInvalidLottoNumber;
import static util.GameValidator.isInvalidMoney;

import domain.Lotto;
import domain.Rank;
import domain.WinningLotto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GamePlay {

  final Scanner scanner = new Scanner(System.in);

  private long money;
  private long ticketNumber;
  private final List<Lotto> lottoList = new ArrayList<>();
  private WinningLotto winningLotto;

  public void gameStart() {
    buyTicket();
    setRandomLotto();
    setWinningLotto();
    matching();
  }

  private void buyTicket() {
    printGuideMessage("구입금액을 입력해주세요.");
    inputTicketMoney();
    setTicketNumber();
  }

  private void inputTicketMoney() {
    String moneyStr;
    do {
      moneyStr = scanner.next();
    } while (isInvalidMoney(moneyStr));
    money = Long.parseLong(moneyStr);
  }

  private void setTicketNumber() {
    ticketNumber = money / TICKET_PRICE;
  }

  private void setRandomLotto() {
    for (long i = 0; i < ticketNumber; i++) {
      lottoList.add(getRandomNumbers());
    }
    printRandomLotto();
  }

  private void printRandomLotto() {
    System.out.println(ticketNumber + "개를 구매했습니다.");
    lottoList.forEach(lotto ->
        System.out.println(lotto.getLotto()));
  }

  private void setWinningLotto() {
    printGuideMessage("지난 주 당첨번호를 입력해 주세요.");
    Lotto lotto = setWinLotto();
    printGuideMessage("보너스 볼을 입력해 주세요.");
    int bonusBall = setBonusBall(lotto);
    winningLotto = new WinningLotto(lotto, bonusBall);
  }

  private Lotto setWinLotto() {
    String winLotto;
    List<String> winLottoList;
    do {
      winLotto = scanner.next();
      winLottoList = Arrays.stream(winLotto.split(","))
          .collect(Collectors.toList());
    } while (isInvalidLottoNumber(winLottoList));

    return new Lotto(winLottoList.stream().map(Integer::parseInt).collect(Collectors.toList()));
  }

  private int setBonusBall(Lotto lotto) {
    String bonusBall;
    do {
      bonusBall = scanner.next();
    } while (isInvalidBonusBall(bonusBall, lotto));
    return Integer.parseInt(bonusBall);
  }

  private void matching() {
    HashMap<Rank, Long> formattedLottoList = getFormattedLottoList();
    printHittingResult(formattedLottoList);
    printProfitResult(formattedLottoList);
  }

  private HashMap<Rank, Long> getFormattedLottoList() {
    List<Rank> rankList = new ArrayList<>();
    lottoList.forEach(lotto -> rankList.add(winningLotto.match(lotto)));
    HashMap<Rank, Long> rankLongHashMap = new LinkedHashMap<>();
    Arrays.stream(Rank.values()).forEach(rank -> rankLongHashMap.put(rank, 0L));
    rankList.forEach(rank -> rankLongHashMap.put(rank, rankLongHashMap.get(rank) + 1));
    return rankLongHashMap;
  }

  private void printHittingResult(HashMap<Rank, Long> map) {
    System.out.println("당첨 통계");
    System.out.println("---------");
    map.entrySet().stream()
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
        .filter(e -> e.getKey().getWinningMoney() != 0)
        .forEach(e -> e.getKey().printResult(map.get(e.getKey())));
  }

  private void printProfitResult(HashMap<Rank, Long> map) {
    long totalHitMoney = map.entrySet().stream()
        .mapToLong(e -> e.getKey().getWinningMoney() * e.getValue()).sum();
    System.out.print("총 수익률은 ");
    System.out.print(String.format("%.3f", (float) totalHitMoney / money));
    System.out.println("입니다.");
  }

  private void printGuideMessage(String message) {
    System.out.println(message);
  }
}
