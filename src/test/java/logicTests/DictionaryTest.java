package logicTests;

import hangman.logic.Dictionary;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

public class DictionaryTest implements WithAssertions {
    private final Dictionary dictionary = new Dictionary(new Random());


    public DictionaryTest() throws IOException {
    }

    @Test
    void testGetWord() {
        assertThat(dictionary.getWord()).isNotEmpty();
    }
}
