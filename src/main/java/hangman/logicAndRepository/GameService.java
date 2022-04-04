package hangman.logicAndRepository;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
import java.util.Map;

@Service
@ApplicationScope
public class GameService {
    private Map<String, Game> games;

    public GameService() {
        this.games = new HashMap<>();
    }

    public Game startNewGame(String gameID) {
        if (games.containsKey(gameID)) {
            return games.get(gameID);
        }
        Game game = getNewGame();
        games.put(gameID, game);
        return game;
    }

    public String makeTry(String gameID, String input) {
        Game game = games.get(gameID);
        if (game.isGameOver() != null) {
            return game.printFullVisual();
        }
        return game.play(input);
    }

    public String isOver(String gameID) {
        return games.get(gameID).isGameOver();
    }

    @Lookup
    public Game getNewGame() {
        return null;
    }
}
