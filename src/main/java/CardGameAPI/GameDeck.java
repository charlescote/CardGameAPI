package cardgameapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map; 
import java.util.Map.Entry;
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
     * Lists the count of individual undealt cards
     *
     * @param string of suit counts
     */
    public String getUndealtCardCounts() {
        String array = "";
        ArrayList<Card> cards = new ArrayList<Card>(gameDeck);
        Map<Card, Integer> counts = new LinkedHashMap<Card, Integer>();
        
    	Collections.sort(cards);
        for (Card card : cards) {
            Integer count = counts.get(card); 
            counts.put(card, (count == null) ? 1 : count + 1); 
        }
        
        for (Map.Entry<Card, Integer> count : counts.entrySet()) {
            array += String.format("{ \"suit\": %s, \"rank\": %s, \"count\": %d }, ", count.getKey().getSuit(), count.getKey().getRank(), count.getValue());
        }
        
        if (array != "") {
            array = array.substring(0, array.length() - 2);
        }
        
        return "[ " + array + " ]";
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
