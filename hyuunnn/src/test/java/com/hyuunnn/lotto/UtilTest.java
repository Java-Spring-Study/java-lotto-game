package com.hyuunnn.lotto;

import static com.hyuunnn.lotto.Util.LOTTO_LENGTH;
import static com.hyuunnn.lotto.Util.randomize;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UtilTest {

  @Test
  @DisplayName("랜덤 생성 사이즈 테스트")
  public void RandomizeTest() {
    List<Integer> testRandomize = randomize();
    assertThat(testRandomize.size()).isEqualTo(LOTTO_LENGTH);
  }
}
