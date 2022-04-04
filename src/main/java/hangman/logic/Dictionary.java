package hangman.logic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Dictionary {
    private List<String> words;
    private Random rand;

    public Dictionary(Random rand) throws IOException {
        words = new ArrayList<String>();
        this.rand = rand;
        setWords(Files.readAllLines(new ClassPathResource("HangManDictionary").getFile().toPath(), Charset.defaultCharset()));
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
