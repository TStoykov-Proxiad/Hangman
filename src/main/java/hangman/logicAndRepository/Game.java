package hangman.logicAndRepository;

import jakarta.servlet.http.HttpSession;

public interface Game {
    public static final String VISUAL_ATTR = "visual";
    public static final String RESULT_ATTR = "isOver";

    public String play(String input);

    public String printFullVisual();

    public String isGameOver();

    public static void resetSession(HttpSession session) {
        var names = session.getAttributeNames();
        while (names.hasMoreElements()) {
            session.removeAttribute(names.nextElement());
        }
    }
}
