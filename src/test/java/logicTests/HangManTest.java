package logicTests;

import hangman.logicAndRepository.HangMan;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

public class HangManTest implements WithAssertions {
    private final HangMan hangMan = new HangMan("test");

    @Test
    void testInitalPrint() {
        assertThat(hangMan.printFullVisual()).isEqualTo("t--t");
    }
}
