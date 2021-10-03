import java.util.ArrayList;
import java.util.Scanner;
// add your own banner here

public class Game {
	
	private Player p;
    private Player c;
	private Deck cards;
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		
	}
    
    Scanner input = new Scanner(System.in);
	public void play(){
		boolean continuegame = true;
        p = new Player();
        c = new Player();
        int rounds = 0;

        while (continuegame == true){
            cards = new Deck(); 
            cards.shuffle();
            rounds++;

        while(p.hand.size() < 5){
            Card dealt = cards.deal();
            p.addCard(dealt);
        }
        while(c.hand.size() < 5){
            Card dealt = cards.deal();
            c.addCard(dealt);
        }
        p.sortHand();                   
        c.sortHand();
        
        System.out.println("Round" + rounds + "\n -$10 starting bet.");
        c.adjust(-10);
        p.adjust(-10);
        System.out.println("Current bankroll: " + p.getBankroll() + "\nComputer bankroll: " + c.getBankroll());
        
        double pot = 20;
        while(true){ //Removing cards
             
            System.out.println(p.hand);
            System.out.println("Which cards would you like to remove?Please enter an integer from 1-5 corresponding to the card you'd like to remove.Or 0 if you would not like to remove any cards");                    

            int position = input.nextInt();
            if(position > p.hand.size() || position < 0 ){
                System.out.println("incorrect input");
                continue;
            }
            if(position == 0){
                break;
            }
            p.hand.remove(position-1);
        } //end of removing cards
                 
        while(p.hand.size() < 5){
            Card dealt = cards.deal();
            p.addCard(dealt);
        }  //add new cards
        
        p.sortHand();
        String cpu = checkHand(c.hand);
        String player = checkHand(p.hand);
        System.out.println(p.hand);
        System.out.println("your hand= " + player);
        while(true){
            System.out.println("Enter additional bet:");
            int bet = input.nextInt();

            if(bet>p.getBankroll() || bet > c.getBankroll() ||bet<0){
            System.out.println("Incorrect amount");
            continue;
            }
            else{
                p.adjust(-bet);
                c.adjust(-bet);
                pot = pot + (2*bet);
                break;
            }
        }
        System.out.println(c.hand);
        System.out.println("CPU's hand: " + cpu);
        checkWinner(checkOdds(player),checkOdds(cpu),pot);

        if (c.getBankroll()==0){
            System.out.println("You won the game");
            continuegame = false;
        }
        while(true && continuegame == true){
            System.out.println("Continue game?Enter 1 for YES. 0 for NO");
            int a = input.nextInt();
            if (a!= 0 && a != 1){
                System.out.println("Incorrect input");
                continue;
            }
            if(a == 0 ){
                continuegame = false;
                break;
            }
            break;
        }
        p.removeHand();
        c.removeHand();

    }
    System.out.println("end");
}

    private int checkPairs(ArrayList<Card> hand){
        int numKinds = 1;
        for (int i=0; i<4; i++){
            int x = 1;
            Card a = hand.get(i);
            for (int j=i+1; j<5; j++){
                Card b = hand.get(j);
                if (a.getRank() == b.getRank()) {
                    x++;
                }
            }
            numKinds = Math.max(x,numKinds);
        }
        return numKinds;
    }

    private String checkKinds(ArrayList<Card> hand){
        int numKinds = checkPairs(hand);
        if (numKinds == 2){
            int numpairs = 0;
            for (int i=0; i<4; i++){
                Card a = hand.get(i);
                for(int j=i+1;j<5;j++){
                    Card b = hand.get(j);
                    if (b.getRank() == a.getRank()){
                        numpairs++;
                    }
                }
            }
            if (numpairs == 2){
                return "twopairs";
            }
            else {
                return "onepair";
            }
        }
        if (numKinds == 3){
            if(checkFullHouse(hand) == true){
                return "fullhouse";
            }
            else {
                return "threeofakind";
            }
        }
        if (numKinds == 4){
            return "fourofakind";
        }
        return "none";
    }

    private boolean checkFullHouse(ArrayList<Card> hand){
        if (hand.get(0).getRank() == hand.get(1).getRank() && hand.get(1).getRank() == hand.get(2).getRank() && hand.get(3).getRank() == hand.get(4).getRank()){
            return true;
        }
        if ( hand.get(0).getRank() == hand.get(1).getRank() && hand.get(2).getRank() == hand.get(3).getRank() && hand.get(3).getRank() == hand.get(4).getRank() ){
            return true;
        }
        else return false;
    }

    private boolean checkFlush(ArrayList<Card> hand){
        int a  = hand.get(0).getSuit();
        for (int i=1;i<5;i++){
            Card b = hand.get(i);
            if (a != b.getSuit()){
                return false;
            }
        }
        return true;
    }

    private boolean checkStraight(ArrayList<Card> hand){
        int a = hand.get(0).getRank();
        for (int i=1;i<5;i++){
            int b = hand.get(i).getRank();
            a++;
            if (a != b) {
                return false;
            }
        }
        return true;
    }

    private String checkHand(ArrayList<Card> hand){
        String a = checkKinds(hand);
        if (checkStraight(hand) == true) {
            if(checkFlush(hand) == true) {
                if(hand.get(0).getRank() == 10){
                    return "royalflush";
                }
                return "straightflush";
            }
            return "straight";
        }
        if(checkFlush(hand) == true){
            return "flush";
        }
        return a;
    }

    private int checkOdds(String combo){
        if(combo == "onepair"){
            return 1;
        }
        if(combo == "twopairs"){
            return 2;
        }
        if(combo == "threeofakind"){
            return 3;
        }
        if(combo == "fourofakind"){
            return 4;
        }
        if(combo == "straight"){
            return 5;
        }
        if(combo == "flush"){
            return 6;
        }
        if(combo == "straightflush"){
            return 7;
        }
        if(combo == "royalflush"){
            return 8;
        }
        return 0; 
    }

    private void checkWinner(int a, int b, double pot){
        if (a>b){
            p.adjust(pot);
            System.out.println("You won");
        }
        else if(a==b){
            c.adjust(pot/2);
            p.adjust(pot/2);
            System.out.println("Its a tie");
        }
        else if(a<b){
            c.adjust(pot);
            System.out.println("You lost");
        }
    }
}
