//Oleksandra Kurbanova

/*
 * Dealer represents a dealer in a game of BalckJack
 * holds one hand, adds card to the hand and returns hand
 */
public class Dealer {
	
	private Hand hand;
	
	//method initializes dealer's hand
	public Dealer()
	{
		hand = new Hand();
	}
	//adds card to Dealer's hand
	public void addCard(Card card)
	{
		hand.addCard(card);
	}
	//returns dealer's hand
	public Hand getHand()
	{
		return hand;
	}
	//return's dealer's hand as a string with hand's value
	public String toString()
	{
		return "Hand: \n" + hand.toString() + "\nValue: " + hand.getValue();
	}
}
