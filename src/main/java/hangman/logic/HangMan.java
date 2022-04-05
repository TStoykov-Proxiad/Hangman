package hangman.logic;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Scope("prototype")
public class HangMan implements Game {
    private Set<Character> guesses;
    private String word;
    private HangManMatrix hangingMan;
    private StringBuilder guessedLetters;

    private class HangManMatrix {
        private String[][] visual;
        private int wrongGuesses;

        public HangManMatrix() {
            visual =
                    new String[][]{
                            {"-", "-", "-", "-", "-", "-", "\n"},
                            {" ", " ", " ", " ", " ", "|", "\n"},
                            {" ", " ", " ", " ", " ", "|", "\n"},
                            {" ", " ", " ", " ", " ", "|", "\n"},
                            {" ", " ", " ", " ", " ", "|", "\n"},
                            {" ", " ", " ", " ", " ", "|", "\n"},
                            {" ", " ", " ", " ", " ", "___", "\n"}
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
                case 1 -> visual[1][1] = "|";
                case 2 -> visual[2][1] = "O";
                case 3 -> visual[3][0] = "\\";
                case 4 -> visual[3][2] = "/";
                case 5 -> visual[4][1] = "|";
                case 6 -> visual[5][0] = "/";
                case 7 -> visual[5][2] = "\\";
                default -> {
                }
            }
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

    //returns message if game has finished, and null if it is still playing
    @Override
    public String isGameOver() {
        if (!guessedLetters.toString().contains("-")) {
            return "Congratulations, you guessed the word!";
        } else if (hangingMan.getWrongGuesses() >= 7) {
            return "You lost!";
        }
        return null;
    }

    @Override
    public String play(String input) {
        input = input.toLowerCase();
        if (guesses.contains(input.charAt(0))) {
            return printFullVisual() + "This letter has already been guessed!";
        } else {
            guesses.add(input.charAt(0));
            if (!word.contains(input)) {
                hangingMan.nextWrong();
            } else {
                for (int i = 1; i < word.length() - 1; i++) {
                    if ((guessedLetters.charAt(i) == '-') && (guesses.contains(word.charAt(i)))) {
                        guessedLetters.setCharAt(i, word.charAt(i));
                    }
                }
            }
            return printFullVisual();
        }
    }

    //returns all needed visual elements in correct representations
    @Override
    public String printFullVisual() {

        return hangingMan.toString() + guessedLetters.toString() + "\n";
    }

    public HangMan(String word) {
        guesses = new HashSet<>();
        hangingMan = new HangManMatrix();
        this.word = word;
        guessedLetters = generateLetters();
    }
}
