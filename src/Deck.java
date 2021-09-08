//Oleksnadra Kurbanova

/*
 * Deck can use card object to simulate the deck of cards, dealing and shuffling
 * will contain any number of decks of 52 cards and will not include Joker
 */
public class Deck {
	
	private final int NUMBERofCARDS = 52; 
	private int numberOfDecks;
	private Card[] deck;
	private int currentCard;
	
	private bfImage image;
	
	//constructor for when the number of decks was not specified
	public Deck()
	{
		//call constructor for 1 deck
		this(1);
	}

	//constructor for the deck of cards 
	public Deck(int numberOfDecks)
	{
		image = new bfImage();
		this.numberOfDecks = numberOfDecks;
		//create an empty deck of cards 
		deck = new Card[NUMBERofCARDS * numberOfDecks];
		//fill the deck with cards, starting from index 0
		int index = 0;
		//repeat filling as many times as the number of decks used
		for(int k = 0; k < numberOfDecks; k++)
		{
			//start from index 0 for img array
			int imgIndex = 0;
			//start with lowest suit, diamonds, and move up to spades
			for(int i = Card.CLUB; i <= Card.DIAMOND; i++)
			{
				//start with lowest card value 2, move up to ace (14)
				for(int j = 1; j <= 13; j++)
				{
					deck[index++] = new Card(i,j, image.cardImg[imgIndex++]);
				}
			}
		}
		//set current card index to 0, since no card was dealt yet
		currentCard = 0;
	}

	//method to deal cards that updates current card index after dealing
	public Card deal()
	{
		//if current card index is less than the total number of cards in deck, proceed
		if(currentCard < (NUMBERofCARDS * numberOfDecks))
		{
			return deck[currentCard++];
		}
		//if current card is equal or more than total number of cards, deck ran out of cards
		else 
		{
			System.out.println("Deck is Empty");
			return(null);
		}
	}
	//method to shuffle the deck and reset the current card to index 0
	public void shuffle()
	{
		int randomPosition;
		Card temp;
		//swap cards with random cards, starting at the index 0
		//repeat the swap for every card in the deck for all decks
		for(int i = 0; i < (NUMBERofCARDS * numberOfDecks);i++)
		{
			//find random position for a card to be swapped with
			randomPosition = (int)(Math.random() * (NUMBERofCARDS * numberOfDecks));
			//swap cards
			temp = deck[randomPosition];
			deck[randomPosition] = deck[i];
			deck[i] = temp;
		}
		currentCard = 0;
	}
	//method to get a card from deck if index is given
	public Card getCard(int index)
	{
		return deck[index];
	}
	//method to return all cards in the deck as a string
	public String toString()
	{
		String result = "";
		for(int i = 0; i < (NUMBERofCARDS * numberOfDecks); i++)
		{
			//make it 13 cards in each row
			if(i % 13 == 0 && i > 0)
				result += "\n";
			result += deck[i].toString() + " ";

		}
		return result;
	}
}