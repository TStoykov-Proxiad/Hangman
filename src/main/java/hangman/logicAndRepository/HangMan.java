package hangman.logicAndRepository;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

@Service
@Scope("prototype")
public class HangMan implements Game {
  private Set<Character> guesses;
  private String word;
  private HangManMatrix visual;
  private StringBuilder guessedLetters;
  private GuessValidator validate;

  private class HangManMatrix {
    private String[][] visual;
    private int wrongGuesses;

    public HangManMatrix() {
      visual =
          new String[][] {
            {"-", "-", "-", "-", "-", "-", "</br>"},
            {" ", " ", " ", " ", " ", "|", "</br>"},
            {" ", " ", " ", " ", " ", "|", "</br>"},
            {" ", " ", " ", " ", " ", "|", "</br>"},
            {" ", " ", " ", " ", " ", "|", "</br>"},
            {" ", " ", " ", " ", " ", "|", "</br>"},
            {" ", " ", " ", " ", " ", "___", "</br>"}
          };
      wrongGuesses = 0;
    }

    public int getWrongGuesses() {
      return wrongGuesses;
    }

    @Override
    public String toString() {
      StringBuilder print = new StringBuilder();
      for (String[] row : visual) {
        for (String element : row) {
          print.append(element);
        }
      }
      return print.toString();
    }

    public void nextWrong() {
      wrongGuesses++;
      switch (wrongGuesses) {
        case 1:
          visual[1][1] = "|";
          break;
        case 2:
          visual[2][1] = "O";
          break;
        case 3:
          visual[3][0] = "\\";
          break;
        case 4:
          visual[3][2] = "/";
          break;
        case 5:
          visual[4][1] = "|";
          break;
        case 6:
          visual[5][0] = "/";
          break;
        case 7:
          visual[5][2] = "\\";
          break;
        default:
      }
    }
  }

  private class GuessValidator {

    private String message = null;

    public boolean validateGuess(String guess) {
      if (guess.isEmpty()) {
        message = "Please make a guess!";
      } else if (!guess.matches("^[a-zA-Z]*$")) {
        message = "Please input only letters!";
      } else if (guess.length() > 1) {
        message = "Please input only 1 character at a time!";
      } else if (guesses.contains(guess.charAt(0))) {
        message = "This letter has already been guessed!";
      } else {
        return true;
      }
      return false;
    }

    public String getMessage() {
      return message;
    }
  }

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
    return current; // "t--t"
  }

  private String makeGuess(String guess) {
    guesses.add(guess.charAt(0));
    if (!word.contains(guess)) {
      visual.nextWrong();
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
    return (!guessedLetters.toString().contains("-") || visual.getWrongGuesses() >= 7);
  }

  @Override
  public void play(HttpServletRequest req) {
    String guess = req.getParameter(Game.INPUT_ATTR);
    req.getSession().removeAttribute(Game.ERROR_ATTR);
    if (!validate.validateGuess(guess)) {
      req.getSession()
          .setAttribute(Game.ERROR_ATTR, validate.getMessage()); // sets appropriate message to user
    } else {
      req.getSession().setAttribute(Game.RESULT_ATTR, makeGuess(guess)); // updates guesses so far
      req.getSession().setAttribute(Game.VISUAL_ATTR, visual.toString()); // updates hangman
      if (gameIsOver()) {
        req.getSession().setAttribute(Game.LOST_ATTR, visual.getWrongGuesses() >= 7);
        // True if game is over because of too many wrong guesses, false if it was because word was
        // guessed
      }
    }
  }

  @Override
  public String initialPrint() {

    return visual.toString() + guessedLetters.toString();
  }

  public HangMan(String word) {
    guesses = new HashSet<>();
    visual = new HangManMatrix();
    validate = new GuessValidator();
    this.word = word;
    guessedLetters = generateLetters();
  }
}
