package com.hyuunnn.lotto;

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

  public static InputType isNumeric(String str) {
    try {
      Integer.parseInt(str);
      return InputType.VALID;

    } catch (NumberFormatException e) {
      return InputType.INVALID;
    }
  }

  public static List<Integer> randomize() {
    Random r = new Random();
    Set<Integer> numberList = new LinkedHashSet<>();

    while (numberList.size() < LOTTO_LENGTH) {
      numberList.add(r.nextInt(MAX_LOTTO_RANGE) + 1);
    }
    return List.copyOf(numberList);
  }

  public enum InputType {VALID, INVALID}
}
