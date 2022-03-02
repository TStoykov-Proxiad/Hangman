package hangman;

import java.util.ArrayList;
import java.util.List;

public class HangMan {
  private List<Character> guesses;
  private int wrongGuesses;
  private StringBuffer visual;

  public HangMan() {
    guesses = new ArrayList<Character>();
    wrongGuesses = 0;
    visual = new StringBuffer();
  }

  public int getWrongGuesses() {
    return wrongGuesses;
  }

  public String printHangMan() {
    updateHangMan();
    return visual.toString();
  }

  private void setInitialState() {}

  private void updateHangMan() {
    if (wrongGuesses >= 1) {
      visual.append("------<br />");
    }
    if (wrongGuesses >= 2) {
      visual.append("|    |<br />");
    }
    if (wrongGuesses >= 3) {
      visual.append(" O   |<br />");
    }
    if (wrongGuesses >= 4) {
      visual.append("\\ ");
      if (wrongGuesses >= 5) {
        visual.append("/  |<br />");
      } else {
        visual.append("   |<br />");
      }
    }
    if (wrongGuesses >= 6) {
      visual.append(" |   |<br />");
    }
    if (wrongGuesses >= 7) {
      visual.append("/ ");
      if (wrongGuesses >= 8) {
        visual.append("\\  |<br />");
      } else {
        visual.append("   |<br />");
      }
    }
  }

  public String makeGuess(String guess, String word) {
    StringBuffer currentState = new StringBuffer();
    if (!word.contains(guess)) wrongGuesses++;
    else {
      guesses.add(guess.charAt(0));
      for (int i = 0; i < word.length(); i++) {
        if (guesses.contains(word.charAt(i))) {
          currentState.append(word.charAt(i));
        } else {
          currentState.append("-");
        }
      }
    }
    return currentState.toString();
  }
}
