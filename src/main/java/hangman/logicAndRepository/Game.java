package hangman.logicAndRepository;

import jakarta.servlet.http.HttpServletRequest;

public interface Game {
  public static final String VISUAL_ATTR = "printVisual";
  public static final String RESULT_ATTR = "result";
  public static final String LOST_ATTR = "lost";
  public static final String INPUT_ATTR = "input";
  public static final String WRONG_ATTR = "wrongInput";

  public void play(HttpServletRequest req);

  public String initialPrint();
}
