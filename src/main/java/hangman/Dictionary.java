package hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dictionary {
  private List<String> words;
  private Random rand;

  @Autowired
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

  public String getWord() {
    return words.get(rand.nextInt(words.size()));
  }
}
