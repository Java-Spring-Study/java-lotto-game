package com.hyuunnn.lotto;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Util {

  public static final Scanner SCANNER = new Scanner(System.in);
  public static final int LOTTO_LENGTH = 6;
  public static final int MAX_LOTTO_RANGE = 45;
  public static final int MIN_LOTTO_RANGE = 1;
  public static final int MINIMUM_LOTTO_PRICE = 1000;
  public static final String SEPARATOR = ",";

  public static List<Integer> randomize() {
    Random r = new Random();
    Set<Integer> numberList = new LinkedHashSet<>();

    while (numberList.size() < LOTTO_LENGTH) {
      numberList.add(r.nextInt(MAX_LOTTO_RANGE) + 1);
    }
    return List.copyOf(numberList);
  }

  public static List<Integer> strToIntegerList(String strNumber) {
    return Arrays.stream(strToArray(strNumber))
        .mapToInt(Integer::parseInt)
        .boxed()
        .toList();
  }

  public static String[] strToArray(String strNumber) {
    return strNumber.split(SEPARATOR);
  }

  public static int strToInteger(String strNumber) {
    return Integer.parseInt(strNumber);
  }

  public static int getLottoCount(int price) {
    return price / MINIMUM_LOTTO_PRICE;
  }

}
