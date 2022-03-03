package hangman;

import java.util.ArrayList;
import java.util.List;

public class HangMan {
  private List<Character> guesses;
  private int wrongGuesses;
  private String word;
  private StringBuilder visual;
  private StringBuilder guessedLetters;

  private StringBuilder generateLetters() {
    StringBuilder current = new StringBuilder(word.length());
    char firstLetter = word.charAt(0);
    char lastLetter = word.charAt(word.length() - 1);
    for (int i = 0; i < word.length(); i++) {
      if (word.charAt(i) == firstLetter || word.charAt(i) == lastLetter) {
        current.append(word.charAt(i));
      } else {
        current.append('-');
      }
    }
    guesses.add(firstLetter);
    guesses.add(lastLetter);
    return current;
  }

  private void generateHangMan() {
    if (wrongGuesses == 1) {
      visual.append("------<br />");
    } else if (wrongGuesses == 2) {
      visual.append(" |   |<br />");
    } else if (wrongGuesses == 3) {
      visual.append(" O   |<br />");
    } else if (wrongGuesses == 4) {
      visual.append("\\ ");
    } else if (wrongGuesses == 5) {
      visual.append("/  |<br />");
    } else if (wrongGuesses == 6) {
      visual.append(" |   |<br />");
    } else if (wrongGuesses == 7) {
      visual.append("/ ");
    } else if (wrongGuesses == 8) {
      visual.append("\\  |<br />");
      visual.append("    ___<br />");
    }
  }

  public HangMan(String word) {
    guesses = new ArrayList<Character>();
    wrongGuesses = 0;
    visual = new StringBuilder();
    this.word = word;
    guessedLetters = generateLetters();
  }

  public String getVisual() {
    return visual.toString();
  }

  public String getGuessedLetters() {
    return guessedLetters.toString();
  }

  public String makeGuess(String guess) {
    if (!word.contains((String.valueOf(guess.charAt(0))))) {
      wrongGuesses++;
      generateHangMan();
    } else {
      guesses.add(guess.charAt(0));
      for (int i = 1; i < word.length() - 1; i++) {
        if ((guessedLetters.charAt(i) == '-') && (guesses.contains(word.charAt(i)))) {
          guessedLetters.setCharAt(i, word.charAt(i));
        }
      }
    }
    return guessedLetters.toString();
  }

  public boolean gameIsOver() {
    if (!guessedLetters.toString().contains("-") || wrongGuesses >= 8) return true;
    return false;
  }

  public int getWrongGuesses() {
    return wrongGuesses;
  }
}
