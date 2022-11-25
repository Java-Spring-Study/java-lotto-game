package domain;

import static util.Constant.LOTTO_SIZE;
import static util.Constant.MAXIMUM_LOTTO_NUMBER;
import static util.Constant.MINIMUM_LOTTO_NUMBER;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    this.numbers = numbers;
  }

  public List<Integer> getLotto() {
    return numbers;
  }

  public static Lotto getRandomNumbers(){
    List<Integer> lottoSet = IntStream.range(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER+1)
        .boxed()
        .collect(Collectors.toList());
    Collections.shuffle(lottoSet);
    return new Lotto(lottoSet.subList(0, LOTTO_SIZE));
  }
}