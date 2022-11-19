package com.hyuunnn.lotto;

import java.util.List;

/**
 * 로또 한장을 의미하는 객체
 */
public class Lotto {

  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    this.numbers = numbers;
  }

  public List<Integer> getLottoList() {
    return numbers;
  }

  public int size() {
    return numbers.size();
  }

  @Override
  public String toString() {
    return numbers.toString();
  }
}
