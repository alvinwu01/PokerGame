import java.util.Random;
// add your own banner here

public class Deck {
	
	private Card[] cards;
	private int top=52; // the index of the top of the deck
    
	// add more instance variables if needed
	
	public Deck(){
	cards = new Card[52];
        int k= 0;       
        for(int i=1;i<14;i++){
            for (int j=1; j<5; j++){
                Card something = new Card(j,i);
                cards[k]= something;
                k++;
            }
        }
	}
	
	public Card deal(){
       top = top - 1;
        return cards[top];
                   // deal the top card in the deck
	}
    
     Random rand = new Random();
    
        public void shuffle(){
            for (int i=0;i<52;i++){
                int randomPosition = rand.nextInt(52);
                Card temp = cards[i];
                cards[i]= cards[randomPosition];
                cards[randomPosition] = temp;
            }
        }  
		// shuffle the deck here
    }
