package com.hyuunnn.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

  @Test
  @DisplayName("lotto 객체 사이즈 테스트")
  public void lottoSizeTest() {
    Random random = new Random();
    Lotto lotto;

    for (int i = 0; i < 100; i++) {
      List<Integer> testList = new ArrayList<>();
      for (int j = 0; j < i; j++) {
        testList.add(random.nextInt());
      }

      lotto = new Lotto(testList);
      assertThat(lotto.getLottoList().size()).isEqualTo(i);
    }
  }

  @Test
  @DisplayName("lotto 객체 생성 데이터 테스트")
  public void lottoInitTest() {
    Random random = new Random();
    Lotto lotto;

    for (int i = 0; i < 100; i++) {
      List<Integer> testList = new ArrayList<>();
      for (int j = 0; j < i; j++) {
        testList.add(random.nextInt());
      }

      lotto = new Lotto(testList);
      assertThat(lotto.getLottoList()).isEqualTo(testList);
    }
  }

  @Test
  @DisplayName("WinningLotto match 메서드 테스트")
  public void WinningLottoMatchTest() {
    List<Integer> lottoList = Arrays.asList(1, 2, 3, 4, 5, 6);
    assertRank(Arrays.asList(1, 2, 3, 4, 5, 6), lottoList, 7, Rank.FIRST);
    assertRank(Arrays.asList(1, 2, 3, 4, 5, 7), lottoList, 7, Rank.SECOND);
    assertRank(Arrays.asList(1, 2, 3, 4, 5, 8), lottoList, 7, Rank.THIRD);
    assertRank(Arrays.asList(1, 2, 3, 4, 8, 9), lottoList, 7, Rank.FOURTH);
    assertRank(Arrays.asList(1, 2, 3, 8, 9, 10), lottoList, 7, Rank.FIFTH);
  }

  private void assertRank(List<Integer> testList, List<Integer> lottoList, int bonusNumber,
      Rank rankResult) {
    WinningLotto winningLotto;
    Rank rankTest;

    winningLotto = new WinningLotto(new Lotto(lottoList), bonusNumber);
    rankTest = winningLotto.match(new Lotto(testList));
    assertThat(rankTest).isEqualTo(rankResult);
  }
}

