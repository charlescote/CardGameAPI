package cardgameapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameDeck {
    private ArrayList<Card> gameDeck = new ArrayList<>();
    
    
    /**
     * Adds a deck to the game deck
     *
     * @param deck to add
     */
    public void addDeck(Deck deck) {
        gameDeck.addAll(deck.getDeck());
    }
    
    /**
     * Shuffle the game deck using Fisher-Yates
     */
    public void shuffle() {
        Random rand = new Random();
        for (int i = gameDeck.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i);
            Card temp = gameDeck.get(i);
            gameDeck.set(i, gameDeck.get(j));
            gameDeck.set(j, temp);
        }
    }
    
    /**
     * Remove last card from the game deck
     * 
     * @return the last card
     */
    public Card dealCard() {
        Card card = gameDeck.remove(gameDeck.size() - 1);
        return card;
    }
    
    /**
     * Lists the count of undealt cards by suit
     *
     * @param string of suit counts
     */
    public String getUndealtSuitCounts() {
    	int numHearts = Collections.frequency(gameDeck, new Card(Card.Suit.HEARTS));
    	int numSpades = Collections.frequency(gameDeck, new Card(Card.Suit.SPADES));
    	int numClubs = Collections.frequency(gameDeck, new Card(Card.Suit.CLUBS));
    	int numDiamonds = Collections.frequency(gameDeck, new Card(Card.Suit.DIAMONDS));
    	String suitCounts = String.format("[ { \"suit\": %s, \"count\": %d }, { \"suit\": %s, \"count\": %d }, { \"suit\": %s, \"count\": %d }, { \"suit\": %s, \"count\": %d } ] ",
    			Card.Suit.HEARTS.toString(), numHearts,
    			Card.Suit.SPADES.toString(), numSpades,
    			Card.Suit.CLUBS.toString(), numClubs,
    			Card.Suit.DIAMONDS.toString(), numDiamonds);
    	return suitCounts;
    }
    
    /**
     * Get number of cards in game deck
     *
     * @return game deck size
     */
    public int getGameDeckSize() {
        return gameDeck.size();
    }
}
