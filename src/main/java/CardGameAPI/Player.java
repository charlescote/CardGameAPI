package cardgameapi;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private final String playerToken;
    private int totalScore = 0;
    private ArrayList<Card> playerHand = new ArrayList<>();
    
    
    /**
     * Constructor for player token
     * 
     * @param playerToken
     */
    public Player(String playerToken) {
        this.playerToken = playerToken;
    }
    
    /**
     * Getter for player token
     * 
     * @return playerToken
     */
    public String getPlayerToken() {
        return playerToken;
    }
    
    /**
     * Adds a card to player's hand
     *
     * @param newCard Card object to add
     */
    public void addCard(Card newCard) {
        playerHand.add(newCard);
        totalScore += newCard.getValue();
    }
    
    /**
     * Getter for playerHand
     *
     * @return copy of list of cards
     */
    public ArrayList<Card> getPlayerHand() {
        return new ArrayList<Card>(playerHand);
    }
    
    /**
     * Returns the total value of a player's hand
     *
     * @return totalScore
     */
    public int getTotalScore() {
        return totalScore;
    }
    
    /**
     * Lists cards in hand
     *
     * @return string of list of cards
     */
    public String listPlayerHand() {
        String array = "";
        for (Card card : playerHand) {
            array += card.toJson() + ", ";
        }
        if (array != "") {
            array = array.substring(0, array.length() - 2);
        }
        return "[ " + array + " ]";
    }
    
    /**
     * Comparator for sorting by score
     */
    @Override
    public int compareTo(Player otherPlayer) {
        return totalScore - otherPlayer.totalScore;
    }
}
