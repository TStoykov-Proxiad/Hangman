package hangman.presentation;

import hangman.logicAndRepository.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@Controller
@WebServlet("/gamers/*")
public class GameServlet extends HttpServlet {
    private static final String GAME_ATTR = "game";
    private Game game;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // clear session
        clearSession(req);
        //ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute("context");
        //game = ctx.getBean(Game.class);
        // set new values
        req.getSession().setAttribute(GAME_ATTR, game);
        //
        // req.getSession().setAttribute(Game.RESULT_ATTR, game.printFullVisual());

        req.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //to be replaces ((Game) req.getSession().getAttribute(GAME_ATTR)).play(req);
        req.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    private void clearSession(HttpServletRequest req) {
        var names = req.getSession().getAttributeNames();
        while (names.hasMoreElements()) {
            req.getSession().removeAttribute(names.nextElement());
        }
    }
}
