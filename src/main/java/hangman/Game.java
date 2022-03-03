package hangman;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/game/*")
public class Game extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getSession().removeAttribute("lost");
    req.getSession().removeAttribute("hangman");
    req.getSession().removeAttribute("result");
    req.getSession().removeAttribute("guess");
    req.getSession().removeAttribute("printHangMan");
    req.getSession().setAttribute("hangman", new HangMan("test"));
    RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/game.jsp");
    rd.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HangMan hangman = (HangMan) req.getSession().getAttribute("hangman");
    String guess = req.getParameter("guess");

    req.getSession().setAttribute("result", hangman.makeGuess(guess));
    req.getSession().setAttribute("printHangMan", hangman.getVisual());
    if (hangman.gameIsOver()) {
      if (hangman.getWrongGuesses() >= 8) {
        req.getSession().setAttribute("lost", true);
      } else req.getSession().setAttribute("lost", false);
    }
    req.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getMethod().equalsIgnoreCase("get")) doGet(req, resp);
    else if (req.getMethod().equalsIgnoreCase("post")) doPost(req, resp);
  }
}
