package cardgameapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private GameDeck gameDeck;
    private ArrayList<Deck> decks = new ArrayList<Deck>();
    
    private Map<String, Player> players = new HashMap<String, Player>();
    
    private static Game instance = null;
    
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
    public static Game deleteInstance() {
        instance = null;
        return instance;
    }
    
    public void createDeck() {
        Deck newDeck = new Deck();
        decks.add(newDeck);
    }
    
    public int getNumberOfDecks() {
        return decks.size();
    }
    
    public boolean addDeckToGameDeck() {
        if (decks.isEmpty()) {
            return false;
        }
        gameDeck.addDeck(decks.remove(decks.size() - 1));
        return true;
    }
    
    public boolean shuffleGameDeck() {
        if (gameDeck.getGameDeckSize() > 0) {
            gameDeck.shuffle();
            return true;
        }
        return false;
    }
    
    public String addPlayerToGame(String playerToken) {
        players.put(playerToken, new Player(playerToken));
        return playerToken;
    }
    
    public boolean removePlayerFromGame(String playerToken) {
        Player player = players.remove(playerToken);
        return player != null;
    }
    
    public boolean isPlayerInGame(String playerToken) {
        return players.containsKey(playerToken);
    }
    
    public int numPlayers() {
        return players.size();
    }
    
    public String getPlayerHand(String playerToken) {
        return players.get(playerToken).listPlayerHand();
    }
    
    public int dealCardsToPlayer(String playerToken, int numCards) {
        int numDealt = Math.min(numCards, gameDeck.getGameDeckSize());
        for (int i = 0; i < numDealt; i++) {
            players.get(playerToken).addCard(gameDeck.dealCard());
        }
        return numDealt;
    }
    
    public String listPlayersByScore() {
        ArrayList<Player> playerList = new ArrayList<Player>(players.values());
        Collections.sort(playerList);
        String array = "";
        for (Player player : playerList) {
            array += String.format("{ playerId: %s, totalScore: %d }, ", player.getPlayerToken(), player.getTotalScore());
        }
        if (array != "") {
            array = array.substring(0, array.length() - 2);
        }
        return "[ " + array + " ]";
    }
    
    public String listUndealtCardSuits() {
        return gameDeck.getUndealtSuitCounts();
    }
}
