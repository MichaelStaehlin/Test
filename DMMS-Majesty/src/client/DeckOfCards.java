package client;
	import java.io.Serializable;
import java.util.ArrayList;
	import java.util.Collections;

import javafx.beans.property.SimpleIntegerProperty;

	/**
	 * This class represents a deck of playing cards. When initially created, the deck is filled and shuffled.
	 * Later, the deck can be refilled and reshuffled by calling the "shuffle" method.
	 */
	
	
	public class DeckOfCards implements Serializable{
		
		/**
		 * 
		 */
		/**
		 * 
		 */
		public static void main (String[] args){
			DeckOfCards d = new DeckOfCards(NUM_PLAYERS);
			System.out.println(d.deck.size());
			for(Card c : d.deck){
				System.out.println(c);
			}
			
		}
		
		private final ArrayList<Card> greenCards = new ArrayList<>();
		private final ArrayList<Card> redCards = new ArrayList<>();
	    private final ArrayList<Card> deck = new ArrayList<>();
	    private int cardsRemaining = 0;

	    //TESTZWECK:
	    private final static int NUM_PLAYERS = 4;
	    //ENDE TESTZWECK
	    /**
	     * We only ever have one deck of cards, so we do not set an ID attribute.
	     */
	    public DeckOfCards(int NUM_PLAYERS) {
	    	shuffleGreenCards();
	    	createRedCards();
	    	createFinalDeck(NUM_PLAYERS);
	       
	    }

	    /**
	     * How many cards are left in the deck?
	     */
	  /**  public SimpleIntegerProperty getCardsRemainingProperty() {
	        return cardsRemaining;
	    }*/
	    public int getCardsRemaining() {
	    	return cardsRemaining;
	    }
	    
	    public int getCardOrdinalByIndex(int index){
	    	return this.deck.get(index).getSuit().ordinal();
	    }
	    
	    public void removeCardByIndex(int index){
	    	this.deck.remove(index);
	    }
	    
	    public Card getCardByIndex(int index){
	    	return this.deck.get(index);
	    }
	    
	    //Creates a deck of green Cards and shuffles them.
	    public void shuffleGreenCards(){
	    	
	    	greenCards.clear();
	    	
	    	//Add 9 Müllerinnen to the Deck
	        for(int i=1;i<=9;i++){
	        	Card card = new Card(Card.Suit.Muellerin);
	        	greenCards.add(card);
	        }
	        
	        //Add 5 Brauer to the Deck
	        for(int i=1;i<=5;i++){
	        	Card card = new Card(Card.Suit.Brauer);
	        	greenCards.add(card);
	        }
	        
	        //Add 4 Hexen to the Deck
	        for(int i=1;i<=4;i++){
	        	Card card = new Card(Card.Suit.Hexe);
	        	greenCards.add(card);
	        }
	        
	        //Add 4 Wachen to the Deck
	        for(int i=1;i<=4;i++){
	        	Card card = new Card(Card.Suit.Wachen);
	        	greenCards.add(card);
	        }
	        
	        //Add 3 Soldaten to the Deck
	        for(int i=1;i<=3;i++){
	        	Card card = new Card(Card.Suit.Soldat);
	        	greenCards.add(card);
	        }
	        
	        //Add 4 Wirte to the Deck
	        for(int i=1;i<=4;i++){
	        	Card card = new Card(Card.Suit.Wirt);
	        	greenCards.add(card);
	        }
	        
	        //Add 4 Adlige to the Deck
	        for(int i=1;i<=4;i++){
	        	Card card = new Card(Card.Suit.Adlige);
	        	greenCards.add(card);
	        }
	        
	        // Shuffle
	        Collections.shuffle(greenCards);
	    }
	    
	    public void createRedCards(){
	    	
	    	redCards.clear();
	    	
	    	//Add 2 Müllerinnen to the Deck
	        for(int i=1;i<=2;i++){
	        	Card card = new Card(Card.Suit.Muellerin);
	        	redCards.add(card);
	        }
	        
	        //Add 5 Brauer to the Deck
	        for(int i=1;i<=5;i++){
	        	Card card = new Card(Card.Suit.Brauer);
	        	redCards.add(card);
	        }
	        
	        //Add 6 Hexen to the Deck
	        for(int i=1;i<=6;i++){
	        	Card card = new Card(Card.Suit.Hexe);
	        	redCards.add(card);
	        }
	        
	        //Add 4 Wachen to the Deck
	        for(int i=1;i<=4;i++){
	        	Card card = new Card(Card.Suit.Wachen);
	        	redCards.add(card);
	        }
	        
	        //Add 3 Soldaten to the Deck
	        for(int i=1;i<=3;i++){
	        	Card card = new Card(Card.Suit.Soldat);
	        	redCards.add(card);
	        }
	        
	        //Add 4 Wirte to the Deck
	        for(int i=1;i<=4;i++){
	        	Card card = new Card(Card.Suit.Wirt);
	        	redCards.add(card);
	        }
	        
	        //Add 3 Adlige to the Deck
	        for(int i=1;i<=3;i++){
	        	Card card = new Card(Card.Suit.Adlige);
	        	redCards.add(card);
	        }
	        
	        // Shuffle
	        Collections.shuffle(redCards);
	    }
	    
	    public void createFinalDeck(int NUM_PLAYERS){
	    	
	    	//Combine the red with the green cards in a new ArrayList (deck)
	    	deck.addAll(greenCards);
	    	deck.addAll(redCards);
	    	
	    	removeGreenCards(NUM_PLAYERS);
	    	
	    }
	    
	    //this method removes cards from the deck, depending on the number of players
	    public void removeGreenCards(int NUM_PLAYERS){
	    	
	    	if(NUM_PLAYERS == 2){
	    		deck.subList(0, 27).clear();
	    	}
	    	
	    	if(NUM_PLAYERS == 3){
	    		deck.subList(0, 19).clear();
	    	}
	    	
	    	if(NUM_PLAYERS == 4){
	    		deck.subList(0, 7).clear();
	    	}
	    	
	    	//cardsRemaining.setValue(deck.size());
	    }

	    /**
	     * Take one card from the deck and return it
	     * 
	     * This is an example of conditional assignment
	     */
	    public Card dealCard() {
	        Card card = (deck.size() > 0) ? deck.remove(deck.size()-1) : null;
	        cardsRemaining = deck.size();
	        return card;
	    }
	    
	    public ArrayList<Card> dealFirstDeckCards() {
	    	ArrayList<Card> firstCards = new ArrayList<Card>();
	    	for (int i = 0; i<7; i++) {
	    		Card card = (deck.size() > 0) ? deck.remove(deck.size()-1) : null;
	        cardsRemaining = deck.size();
	         firstCards.add(card);
	    	}
	    	return firstCards;
	        
	    }
	    
	    
	    
	}

	

