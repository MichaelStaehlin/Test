package client;

import java.io.Serializable;

public class Card implements Serializable {

	    /**
	 * 
	 */

		public enum Suit { Muellerin, Brauer, Hexe, Wachen, Soldat, Wirt, Adlige;
	        @Override
	        public String toString() {
	            String suit = "";
	            switch (this) {
	            case Muellerin: suit = "Muellerin"; break;
	            case Brauer: suit = "Brauer"; break;
	            case Hexe: suit = "Hexe"; break;
	            case Wachen: suit = "Wachen"; break;
	            case Soldat: suit = "Soldat"; break;
	            case Wirt: suit = "Wirt"; break;
	            case Adlige: suit = "Adlige"; break;
	           
	            }
	            return suit;
	        }
	    };
	    
	    public enum Locations {Brauerei, Hexenhaus, Kaserne, Lazarett, Muehle, Schloss, Taverne, Wachturm;
	    	 public String toString() {
		            String suit = "";
		            switch (this) {
		            
		            case Brauerei: suit = "Brauerei"; break;
		            case Hexenhaus: suit = "Hexenhaus"; break;
		            case Kaserne: suit = "Kaserne"; break;
		            case Lazarett: suit = "Lazarett"; break;
		            case Muehle: suit = "Muehle"; break;
		            case Schloss: suit = "Schloss"; break;
		            case Taverne: suit = "Taverne"; break;
		            case Wachturm: suit = "Wachturm"; break;
		            }
		            return suit;
		        }
            
	    };
	    
	    public enum LoadScreen {majesty;
	    	 public String toString() {
		            String suit = "";
		            switch (this) {
		            
		            case majesty: suit = "majesty"; break;
		           
		            }
		            return suit;
		        }
           
	    };
	    
	    
	    private  Suit suit;
	    private Locations location;
	    private LoadScreen ls;
	    
	    public Card(Locations loc) {
	    	location= loc;
	    }
	    
	    public Card(Suit suit) {
	        this.suit = suit;
	    }
	    
	    public Card(LoadScreen ls) {
	        this.ls = ls;
	    }

	   

		public LoadScreen getLs() {
			return ls;
		}

		public void setLs(LoadScreen ls) {
			this.ls = ls;
		}

		public Suit getSuit() {
	        return suit;
	    }

	    @Override
	    public String toString() {
	        return suit.toString();
	    }

		public Locations getLocation() {
			return location;
		}

		public void setLocation(Locations location) {
			this.location = location;
		}
	
}
