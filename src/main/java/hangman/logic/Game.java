package hangman.logic;

public interface Game {
    public static final String VISUAL_ATTR = "visual";
    public static final String RESULT_ATTR = "isOver";

    public String play(String input);

    public String printFullVisual();

    public String isGameOver();

}
