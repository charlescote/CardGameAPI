package cardgameapi;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final Rank rank;
    
    
    /**
     * The suits that a card can bear
     */
    public enum Suit {
        HEARTS(0),
        SPADES(1),
        CLUBS(2),
        DIAMONDS(3)
        ;
        
        private final int value;
        
        
        /**
         * Constructor for value initialization
         *
         * @param value rank of the suit of the card
         */
        Suit(int value) {
            this.value = value;
        }

        /**
         * Returns the value of the suit
         *
         * @return rank value
         */
        public int getValue() {
            return value;
        }
        
        /**
         * Returns a string representation of the suit
         * with the first letter capitalized
         *
         * @return string representation of the suit
         */
        @Override
        public String toString() {
            return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }
    
    
    public enum Rank {
    	NORANK(0),
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13)
        ;
        
        private final int value;
        
        
        /**
         * Constructor for value initialization
         *
         * @param value Value of the card
         */
        Rank(int value) {
            this.value = value;
        }

        /**
         * Returns the value of the rank
         *
         * @return rank value
         */
        public int getValue() {
            return value;
        }
        
        /**
         * Returns a string representation of the rank
         * with the first letter capitalized
         *
         * @return string representation of the rank
         */
        @Override
        public String toString() {
            return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }
    
    
    /**
     * @param suit Suit of the card
     * @param rank Rank of the card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    /**
     * Constructor for comparator
     * 
     * @param suit Suit of the card
     */
    public Card(Suit suit) {
        this.suit = suit;
        this.rank = Rank.NORANK;
    }
    
    /**
     * Getter for the suit
     *
     * @return string value of suit
     */
    public String getSuit() {
        return this.suit.toString();
    }
    
    /**
     * Getter for the rank
     *
     * @return string value of rank
     */
    public String getRank() {
        return this.rank.toString();
    }
    
    /**
     * Returns the value of the card
     *
     * @return card value
     */
    public int getValue() {
        return this.rank.getValue();
    }
    
    /**
     * Returns a JSON representation of the card.
     */
    public String toJson() {
        return String.format("{ \"suit\": %s, \"rank\": %s }", this.suit.toString(), this.rank.toString());
    }
    
    @Override
    public boolean equals(Object obj){
    	Card card = (Card) obj;
    	if (card.suit == suit) {
    		if (card.rank == Rank.NORANK) {
    			return true;
    		} else {
    			return card.rank == this.rank;
    		}
    	} else {
    		return false;
    	}
    }
    
    /**
     * Comparator for sorting
     */
    @Override
    public int compareTo(Card otherCard) {
        int suits = suit.getValue() - otherCard.suit.getValue();
        if( suits != 0) {
            return suits;
        } else {
            return rank.getValue() - otherCard.rank.getValue();
        }
    }
}
