package hangman.presentation;

import hangman.logicAndRepository.Game;
import hangman.logicAndRepository.GameService;
import hangman.models.GameModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/games/{gameID}")
public class GameController {
    @Autowired
    GameService games;

    @GetMapping
    public String getGame(@ModelAttribute("gameModel") GameModel hangman, ModelMap model, @PathVariable("gameID") String gameID) {
        model.clear();
        Game game = games.startNewGame(gameID);
        model.addAttribute(Game.VISUAL_ATTR, game.printFullVisual());
        model.addAttribute(Game.RESULT_ATTR, game.isGameOver());
        return "game";
    }

    @PostMapping
    public String playGame(@Valid @ModelAttribute("gameModel") GameModel hangman, BindingResult br, ModelMap model, @PathVariable("gameID") String gameID) {
        if (br.hasErrors()) {
            model.addAttribute(Game.VISUAL_ATTR, games.getGame(gameID).printFullVisual());
            return "game";
        }
        model.addAttribute(Game.VISUAL_ATTR, games.makeTry(gameID, hangman.getInput()));
        model.addAttribute(Game.RESULT_ATTR, games.isOver(gameID));
        return "game";
    }
}
