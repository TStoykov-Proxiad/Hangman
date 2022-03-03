package hangman;

import java.util.Random;

public class RandomBean {
  private static final Random RANDOM = new Random();

  public int getNextInt() {
    return RANDOM.nextInt();
  }
}
