package cardgameapi;

import java.util.ArrayList;

public class Deck {
    public static final int CARDS_IN_DECK = 52;
    
    private ArrayList<Card> deck = new ArrayList<>();
    
    
    public Deck() {
        for (Card.Suit suit:Card.Suit.values()) {
            for (Card.Rank rank:Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }
    
    public ArrayList<Card> getDeck() {
        return new ArrayList<Card>(deck);
    }
}
