package logicTests;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import hangman.logicAndRepository.HangMan;

public class HangManTest implements WithAssertions {
  private final HangMan hangMan = new HangMan("test");

  @Test
  void testInitalPrint() {
    assertThat(hangMan.initialPrint()).isEqualTo("t--t");
  }
}
