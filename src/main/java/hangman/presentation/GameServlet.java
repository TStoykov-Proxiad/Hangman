package hangman.presentation;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import hangman.logicAndRepository.Game;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@WebServlet("/games/*")
public class GameServlet extends HttpServlet {
  private static final String GAME_ATTR = "game";
  private Game game;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // clear session
    clearSession(req);
    ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute("context");
    game = ctx.getBean(Game.class);
    // set new values
    req.getSession().setAttribute(GAME_ATTR, game);
    req.getSession().setAttribute(Game.RESULT_ATTR, game.initialPrint());

    req.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ((Game) req.getSession().getAttribute(GAME_ATTR)).play(req);
    req.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
  }

  private void clearSession(HttpServletRequest req) {
    var names = req.getSession().getAttributeNames();
    while (names.hasMoreElements()) {
      req.getSession().removeAttribute(names.nextElement());
    }
  }
}
