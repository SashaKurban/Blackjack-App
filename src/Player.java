//Oleksandra Kurbanova

/*
 * class to represent a player in blackJack game
 * holds funds, bet, array of Hands, insurance, number of hands
 * allow up to 2 hands, with one split allowed 
 */
public class Player {
	
	private double funds;
	private double insurance;
	private int numHands;
	private double bet;
	private Hand[] hands;
	//initiate a player with $100 of funds
	public Player()
	{
		funds = 100.0;
		insurance = 0;
		hands = new Hand[2];
		bet = 0;
	}
	//method to start a new game with a given bet
	public void newGame(double bet)
	{
		//create an array of 2 hands possible
		//set number of hands to 1, set bet and reduce funds
		hands = new Hand[2];
		numHands = 1;
		hands[numHands - 1] = new Hand();
		insurance = 0;
		this.bet = bet;
		funds -= bet;

	}
	//method to add a card to hand indicated
	public void addCard(Card card, int handIndex)
	{
		hands[handIndex].addCard(card);
	}
	//method to split a given hand
	public void split(int handIndex)
	{
		//save two cards on hand, then split into two new hands
		Card cardOne = hands[handIndex].getCard(0);
		Card cardTwo = hands[handIndex].getCard(1);
		
		hands[handIndex] = new Hand();
		hands[handIndex].addCard(cardOne);
		
		hands[numHands] = new Hand();
		hands[numHands].addCard(cardTwo);
		
		//double the bet since number of hands doubled
		funds -= bet;
		bet *= 2;
		numHands++;
	}
	//method to double down the bet, only used as a first decision, not allowed after split
	public void doubleDown()
	{
		funds -= bet;
		bet *= 2;
	}
	//method to set insurance of half the bet
	public void setInsurance()
	{
		insurance = bet / 2;
		funds -= insurance;
	}
	//set insurance to 0 when insurance is lost
	public void lostInsurance()
	{
		insurance = 0;
	}
	//end game and win triple of insurance
	public void winInsurance()
	{
		funds += insurance * 3;
		bet = 0;
	}
	//method to loose game
	public void lost(int handIndex)
	{
		//if there is another hand left to play, reduce bet by half
		if(handIndex < 1 && numHands == 2)
		{
			bet -= bet / 2;
		}
		else
		{
			bet = 0;
		}
	}
	//end game and win double the bet on the given hand
	//if two hands are in play, the amount won is a bet (as each hand counts for half a bet)
	public void win(int handIndex)
	{
		//if there is another hand left to play, reduce bet by half
		if(handIndex < 1 && numHands == 2)
		{
			funds += bet;
			bet /= 2;
		}
		else
		{
			funds += bet * 2;
			bet = 0;
		}	
	}
	
	//method to use when draw happens
	public void draw(int handIndex)
	{
		//if there is another hand left to play, reduce bet by half
		if(handIndex < 1 && numHands == 2)
		{
			funds += bet / 2;
			bet -= bet / 2;
		}
		else
		{
			funds += bet;
			bet = 0;
		}	
	}
	//method to return half a bet in early surrender
	public void earlySurrender()
	{
		funds += bet / 2;
		bet = 0;
	}
	public void win()
	{
		funds += bet * 2;
		bet = 0;
	}
	//BJ win only possible in the beginning of the game, if player got ace and ten 
	public void BJwin()
	{
		funds += (bet / 2) * 3;
		bet = 0;
	}
	public void lost()
	{
		bet = 0;
	}
	//returns funds as double
	public double getFunds()
	{
		return funds;
	}
	//returns bet
	public double getBet()
	{
		return bet;
	}
	//return number of Hands currently held by player
	public int getNumHands()
	{
		return numHands;
	}
	//return hand requested
	public Hand getHand(int handIndex)
	{
		return hands[handIndex];
	}
	
	//returns Player's representation as String with funds and all available hands and their values
	public String toString()
	{
		String result = "Funds: $" + String.format("%.2f", funds) + "\nHands: \n";
		for(int i = 0; i < numHands; i++)
		{
			result += hands[i].toString() + "\nValue: " + hands[i].getValue();
		}
		result += "\nBet: " + String.format("%.2f\n", bet);
		return result;
	}
}
