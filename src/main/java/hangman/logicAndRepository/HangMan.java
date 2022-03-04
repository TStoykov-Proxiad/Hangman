package hangman.logicAndRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class HangMan implements Game {
  private List<Character> guesses;
  private int wrongGuesses;
  private String word;
  private StringBuilder visual;
  private StringBuilder guessedLetters;
  private Dictionary words;

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

  private String getVisual() {
    return visual.toString();
  }

  private String getGuessedLetters() {
    return guessedLetters.toString();
  }

  private String makeGuess(Character guess) {
    guesses.add(guess);
    if (!word.contains((String.valueOf(guess)))) {
      wrongGuesses++;
      generateHangMan();
    } else {
      for (int i = 1; i < word.length() - 1; i++) {
        if ((guessedLetters.charAt(i) == '-') && (guesses.contains(word.charAt(i)))) {
          guessedLetters.setCharAt(i, word.charAt(i));
        }
      }
    }
    return guessedLetters.toString();
  }

  private boolean gameIsOver() {
    if (!guessedLetters.toString().contains("-") || wrongGuesses >= 8) return true;
    return false;
  }

  @Override
  public void play(HttpServletRequest req) {
    String guess = req.getParameter(Game.INPUT_ATTR);
    req.getSession().removeAttribute(Game.WRONG_ATTR);
    if (!guess.matches("^[a-zA-Z]*$")) {
      req.getSession().setAttribute(Game.WRONG_ATTR, "Please input only letters!");
    } else if (guess.length() > 1) {
      req.getSession().setAttribute(Game.WRONG_ATTR, "Please input only 1 character at a time!");
    } else {
      if (!guesses.contains(guess.charAt(0))) {
        req.getSession().setAttribute(Game.RESULT_ATTR, makeGuess(guess.charAt(0)));
        req.getSession().setAttribute(Game.VISUAL_ATTR, getVisual());
        if (gameIsOver()) {
          if (wrongGuesses >= 8) {
            req.getSession().setAttribute(Game.LOST_ATTR, true);
          } else req.getSession().setAttribute(Game.LOST_ATTR, false);
        }
      } else {
        req.getSession().setAttribute(Game.WRONG_ATTR, "This letter has already been guessed!");
      }
    }
  }

  @Override
  public String initialPrint() {

    return getGuessedLetters();
  }

  @Autowired
  public HangMan(Dictionary words) {
    guesses = new ArrayList<Character>();
    wrongGuesses = 0;
    visual = new StringBuilder();
    this.words = words;
    this.word = this.words.getWord();
    guessedLetters = generateLetters();
  }
}
