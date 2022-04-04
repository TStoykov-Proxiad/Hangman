package hangman.logicAndRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class Dictionary {
    private List<String> words;
    private Random rand;

    public Dictionary(Random rand) {
        words = new ArrayList<String>();
        this.rand = rand;
        setWords(
                Arrays.asList(
                        "aardvark",
                        "abasement",
                        "abutting",
                        "deserve",
                        "arrogant",
                        "chest",
                        "bargain",
                        "animal",
                        "safari",
                        "adviser",
                        "plagiarize",
                        "tool",
                        "proof",
                        "detail",
                        "execute"));
    }

    public void setWords(List<String> words) {
        this.words.addAll(words);
    }

    @Bean
    @Scope("prototype")
    public String getWord() {
        return words.get(rand.nextInt(words.size()));
    }
}
