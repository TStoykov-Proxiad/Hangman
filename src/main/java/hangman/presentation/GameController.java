package hangman.presentation;

import hangman.logicAndRepository.Game;
import hangman.logicAndRepository.GameService;
import hangman.models.HangManModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/games/{gameID}")
public class GameController {
    @Autowired
    GameService games;

    @GetMapping
    public String getNewGame(ModelMap model, @PathVariable("gameID") String gameID) {
        model.clear();
        Game game = games.startNewGame(gameID);
        model.addAttribute(Game.VISUAL_ATTR, game.printFullVisual());
        model.addAttribute(Game.RESULT_ATTR, game.isGameOver());
        return "game";
    }

    @PostMapping
    public String playGame(@ModelAttribute("hangManModel") HangManModel hangman, ModelMap model, @PathVariable("gameID") String gameID) {
        model.addAttribute(Game.VISUAL_ATTR, games.makeTry(gameID, hangman.getInput()));
        model.addAttribute(Game.RESULT_ATTR, games.isOver(gameID));
        return "game";
    }
}
