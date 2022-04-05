package logicTests;

import hangman.logic.HangMan;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class HangManTest implements WithAssertions {
    private final HangMan hangMan = new HangMan("test");

    @Test
    void testPrint() {
        assertThat(hangMan.printFullVisual()).endsWith("t--t");
    }

    @Test
    void testPlay() {
        assertThat(hangMan.play("e")).endsWith("te-t");
    }

    @Test
    void testPlayWrongInput() {
        assertThat(hangMan.play("x")).endsWith("t--t");
    }

    @Test
    void testPlayDuplicateInput() {
        assertThat(hangMan.play("t")).contains("t--t").endsWith("This letter has already been guessed!");
    }

    @Test
    void testIsGameOverVictory() {
//        Field privateGuessedLetters = HangMan.class.getDeclaredField("guessedLetters");
//        privateGuessedLetters.setAccessible(true);
//        privateGuessedLetters.set(hangMan, new StringBuilder("test"));
        ReflectionTestUtils.setField(hangMan, HangMan.class, "guessedLetters", new StringBuilder("test"), StringBuilder.class);
        assertThat(hangMan.isGameOver()).isEqualTo("Congratulations, you guessed the word!");
    }

    @Test
    void testIsGameOverLoss() {
        //7 wrong guesses
        hangMan.play("a");
        hangMan.play("b");
        hangMan.play("c");
        hangMan.play("d");
        hangMan.play("f");
        hangMan.play("g");
        hangMan.play("h");
        assertThat(hangMan.isGameOver()).isEqualTo("You lost!");
    }

    @Test
    void testIsGameOverNO() {
        assertThat(hangMan.isGameOver()).isNull();
    }
}
