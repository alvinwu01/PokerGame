import java.util.ArrayList;
// add your own banner here

public class Player {

	public ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(){		
	hand = new ArrayList<Card>();
    bankroll = 1000;
	}

	public void addCard(Card c){
	    this.hand.add(c);
        // add the card c to the player's hand
	}

	public void removeHand(){
	    for(int i=0; i<hand.size();i++){
            hand.remove(i);
        }
    }
        // remove the card c from the player's hand

		
    public void bets(double amt){
        bet = 0;
        bet = bet + amt;
    }
            // player makes a bet}

    public void adjust(double amt){
        bankroll = bankroll + amt;
        }    //	adjust bankroll if player wins


    public double getBankroll(){
            return bankroll;// return current balance of bankroll
    }

    public void sortHand(){
        for(int i=0; i<hand.size()-1;i++){
            int min = i;            
            for(int j=i+1; j<hand.size(); j++){
                
                Card test = hand.get(j);
                
                if(test.compareTo(hand.get(min)) < 0){
                      
                    min = j;
                }
            }
            Card temp = hand.get(min);
            hand.set(min,hand.get(i));
            hand.set(i,temp);
        }
    }
}
          
          