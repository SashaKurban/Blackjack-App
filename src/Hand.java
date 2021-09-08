//Oleksandra Kurbanova

/*
 * class hand can be used to store a hand of cards for the game BlackJack
 * the maximum number of Cards in one hand is 11
 * a new Card cannot be added to a hand if total value of a hand is over 21
 * the value of a hand of cards is calculated by the value of cards' ranks, disregarding suits
 * if hand has an Ace, the value of Ace can be either 11 or 1
 * cards 2 through 10 has value respective to their ranks
 * Jack, Queen and King are worth 10 points
 */
public class Hand {
	
	private int value;
	private int numberOfCards;
	private Card [] hand;
	private int Aces;

	//method that initializes a hand of cards before card's are added
	public Hand()
	{
		//maximum possible number of cards in one hand is 11
		hand = new Card [11];
		value = 0;
		numberOfCards = 0;
		Aces = 0;
	}
	//method to add the Card dealt to a hand
	public void addCard(Card card)
	{
		hand[numberOfCards++] = card;
		updateValue(card.rank());
	}
	//method to update hand's value with new rank added
	private void updateValue(int rank)
	{
		if(rank == 1)
		{
			//rank 11 is an Ace, can be  either 11 or 1 depending on total hand value
			Aces ++;
			value += 11;
		}
		else if(rank > 10)
		{
			//ranks 11, 12, 13 are Jack, Queen and Kind, all valued at 10
			value += 10;
		}
		else
		{
			//all other ranks correspond to their values
			value += rank;
		}
		//if total value is over 21 and there is an Aces on hand, reduce Aces' value from 11 to 1
		if(value > 21 && Aces > 0)
		{
			value -= 10;
			Aces--;
		}
	}
	
	//method to get hand's value
	public int getValue()
	{
		return value;
	}
	//method to get number of cards in hand
	public int getNumberOfCards()
	{
		return numberOfCards;
	}
	
	//method to get Card at specific index in hand
	public Card getCard(int index)
	{
		return hand[index];
	}
	//method to get string representation of a hand
	public String toString()
	{
		String result ="";
		for(int i = 0; i < numberOfCards; i++)
		{
			result += hand[i].toString() + " ";
		}
		return result;
	}
}

