package cardgameapi;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {
    Game game;
    
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    
    private Response successResponse(String message) {
        Response response = new Response();
        response.setStatus(SUCCESS);
        response.setMessage(message);
        return response;
    }
    
    private Response failureResponse(String message) {
        Response response = new Response();
        response.setStatus(ERROR);
        response.setMessage(message);
        return response;
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Response newGame() {
        if (game != null) {
            return failureResponse("Game has already been created. Please delete game.");
        }
        game = Game.getInstance();
        return successResponse("Game created successfully.");
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response deleteGame() {
        if (game == null) {
            return failureResponse("Game has not been created.");
        }
        game = Game.deleteInstance();
        return successResponse("Game deleted successfully.");
    }
    
    @RequestMapping(value = "/deck/create", method = RequestMethod.POST)
    public Response createDeck() {
        if (game == null) {
            return failureResponse("Game has not been created.");
        }
        game.createDeck();
        return successResponse("Number of decks: " + game.getNumberOfDecks());
    }
    
    @RequestMapping(value = "/gamedeck/add", method = RequestMethod.POST)
    public Response addDeck() {
        if (game == null) {
            return failureResponse("Game has not been created.");
        }
        if (game.addDeckToGameDeck()) {
            return successResponse("Number of decks: " + game.getNumberOfDecks());
        }
        return failureResponse("No decks to be added");
    }
    
    @RequestMapping(value = "/gamedeck/shuffle", method = RequestMethod.POST)
    public Response shuffleDeck() {
        if (game == null) {
            return failureResponse("Game has not been created.");
        }
        if (game.shuffleGameDeck()) {
            return successResponse("Game deck has been shuffled.");
        }
        return failureResponse("No cards to be shuffled.");
    }
    
    @RequestMapping(value = "/gamedeck/undealt", method = RequestMethod.GET)
    public String getUndealt() {
        if (game == null) {
            return failureResponse("Game has not been created.").toString();
        }
        return game.listUndealtCardSuits();
    }
    
    @RequestMapping(value = "/players/{id}/add", method = RequestMethod.POST)
    public Response addPlayer(@PathVariable String id) {
        if (game == null) {
            return failureResponse("Game has not been created.");
        }
        if (game.isPlayerInGame(id)) {
            return failureResponse("Player already exists.");
        }
        String playerId = game.addPlayerToGame(id);
        return successResponse("Player ID: " + playerId);
    }
    
    @RequestMapping(value = "/players/{id}/remove", method = RequestMethod.POST)
    public Response removePlayer(@PathVariable String id) {
        if (game == null) {
            return failureResponse("Game has not been created.");
        }
        if (game.removePlayerFromGame(id)) {
            return successResponse(id + " removed from game.");
        }
        return failureResponse(id + " not found in game");
    }
    
    @RequestMapping(value = "/players/{id}/deal", method = RequestMethod.POST)
    public Response dealCards(@PathVariable String id, @RequestParam int numCards) {
        if (game == null) {
            return failureResponse("Game has not been created.");
        }
        if (!game.isPlayerInGame(id)) {
            return failureResponse(id + " is not in the game.");
        }
        int numDealt = game.dealCardsToPlayer(id, numCards);
        if (numDealt > 0) {
            return successResponse(numDealt + " cards dealt");
        } else {
            return failureResponse("No cards dealt");
        }
    }
    
    @RequestMapping(value = "/players/{id}/hand", method = RequestMethod.GET)
    public String getHand(@PathVariable String id) {
        if (game == null) {
            return failureResponse("Game has not been created.").toString();
        }
        if (!game.isPlayerInGame(id)) {
            return failureResponse(id + " is not in the game.").toString();
        }
        return game.getPlayerHand(id);
    }
    
    @RequestMapping(value = "/players/score", method = RequestMethod.GET)
    public String getScores() {
        if (game == null) {
            return failureResponse("Game has not been created.").toString();
        }
        if (game.numPlayers() == 0) {
            return failureResponse("No players to display").toString();
        }
        return game.listPlayersByScore();
    }
}
