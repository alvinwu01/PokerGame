
// add your own banner here

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	private String suitString;

	public Card(int s, int r){
		suit = s;
        rank = r;
        if(suit == 1){ suitString= "diamonds";}
        if(suit == 2){suitString= "clovers";}
        if(suit == 3){suitString= "hearts";}
        if(suit == 4){suitString="spades";}
	}
		
    
    public int compareTo(Card c){
		int suitdistance = (this.suit - c.suit);
        int carddistance = this.rank - c.rank;
        if(carddistance == 0){
			return suitdistance;
		}
        return carddistance;
	}
	
	public String toString(){      
        return this.suitString + " " + this.rank;
        // use this method to easily print a Card object
	}

	public int getRank(){
		return this.rank;
	}

	public int getSuit(){
		return this.suit;
	}
	// add some more methods here if needed

}
