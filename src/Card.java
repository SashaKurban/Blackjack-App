//Oleksandra Kurbanova

/*
 * Card class is used to represent a single card in a Blackjack game
 * holds suit, rank and a matching card image, as well as final values of 4 suits for ease of assignment
 * holds 2 String arrays, suit and rank, for string representation of card in console mode
 * card is used by the Deck class to construct a deck of card
 * 
 */

import java.awt.image.BufferedImage;
public class Card{

	private int suit;
	private int rank;
	//image representation of the card
	private BufferedImage img;
	//numeric values associated with card's suit, the order determined by card image file used
	public static final int CLUB = 1;
	public static final int SPADE = 2;
	public static final int HEART = 3;
	public static final int DIAMOND = 4;
	//string arrays of ranks and suits to be used as output in a game
	public static String [] Suit = {"*", "c", "s", "h", "d"};
	public static String [] Rank = { "*", "A", "2", "3", "4", "5", "6", "7", "8", "9", 
			"10", "J", "Q", "K"};
	//constructor where suit and rank given as indexes for corresponding String arrays
	public Card(int suit, int rank, BufferedImage img)
	{
		this.suit = suit;
		this.rank = rank;
		this.img = img;
	}

	public BufferedImage getImg()
	{
		return img;
	}
	public int suit()
	{
		return suit;
	}
	
	public int rank()
	{
		return rank;
	}
	
	//returns card's suit and rank as a string for output
	public String toString()
	{
		return Rank[rank] + Suit[suit];
	}
}


