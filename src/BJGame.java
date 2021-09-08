//Oleksandra Kurbanova

//BJGame is a class that controls all game operations. holds player, dealer and deck
//class is used inside BJTable, with BJTable's buttons triggering game events 

public class BJGame {

	private Deck deck;
	private Dealer dealer;
	private Player player;
	
	//initialize player, dealer an deck objects
	public BJGame(){
		
		deck = new Deck();
		dealer = new Dealer();
		player = new Player();
	}
	//new game shuffles the deck and sets the player's bet
	public void newGame(double bet)
	{
		//set player's bet
		player.newGame(bet);
		//create empty handed dealer
		dealer = new Dealer();
		deck.shuffle();
	}
	//method to deal to Dealer
	public Card dealToDealer()
	{
		Card card = deck.deal();
		dealer.addCard(card);
		return card;
	}
	//method to deal cards to player
	public Card dealToPlayer(int handIndex)
	{
		Card card = deck.deal();
		player.addCard(card, handIndex);
		return card;
	}
	//method to check if insurance should be allowed
	public boolean insuranceAllowed()
	{
		//if dealer's visible card is an ace, allow insurance, but check player's funds first
		if(dealer.getHand().getCard(1).rank() == 1 && player.getFunds() >= player.getBet() / 2)
			return true;
		else return false;
	}
	//method to check if player has enough funds to double down
	public boolean doubleDownAllowed()
	{
		if(player.getFunds() >= player.getBet())
			return true;
		else return false;
	}
	//method to check if split should be allowed
	public boolean splitAllowed()
	{
		//if player has two cards of same rank, allow split, but check funds first
		if(player.getHand(0).getCard(0).rank() == player.getHand(0).getCard(1).rank() 
				&& player.getFunds() >= player.getBet())
			return true;
		else return false;
	}
	//method to check if dealer busted
	public boolean dealerBusted()
	{
		if(dealer.getHand().getValue() > 21)
			return true;
		else return false;
	}
	//method that checks if dealer got BlackJack
	public boolean dealerBlackJack()
	{
		if(dealer.getHand().getValue() == 21)
			return true;
		else return false;
	}
	//method that checks if player won over dealer
	public boolean playerWon(int handIndex)
	{
		//if dealer got less points than player, player won
		if(dealer.getHand().getValue() < player.getHand(handIndex).getValue())
			return true;
		else 
			return false;
	}
	//check if it is a draw
	public boolean isDraw(int handIndex)
	{
		//if dealer got less points than player, player won
		if(dealer.getHand().getValue() == player.getHand(handIndex).getValue())
			return true;
		else
			return false;
	}
	//method to check if player got BlackJack
	public boolean playerBlackJack(int handIndex)
	{
		if(player.getHand(handIndex).getValue() == 21)
			return true;
		else return false;
	}
	//method to check if player busted
	public boolean playerBusted(int handIndex)
	{
		if(player.getHand(handIndex).getValue() > 21)
			return true;
		else return false;
	}
	//method to win on all hands, used if dealer busted or BJ on first dealing 
	public void win()
	{
		player.win();
	}
	//method to loose on all hands, used for surrender
	public void lost()
	{
		player.lost();
	}
	//method to win on particular hand
	public void win(int handIndex)
	{
		player.win(handIndex);
	}
	//method to win 3:2 if player gets BJ on the first hand out
	public void BJwin()
	{
		player.BJwin();
	}
	//method to loose on particular hand
	public void lost(int handIndex)
	{
		player.lost(handIndex);
	}
	//method to draw on particular hand
	public void draw(int handIndex)
	{
		player.draw(handIndex);
	}
	//method to perform early surrender, where half of the bet is returned to player 
	public void earlySurrender()
	{
		player.earlySurrender();
	}
	//method to get player's funds
	public double getFunds()
	{
		return player.getFunds();
	}
	//method to get player's bet
	public double getBet()
	{
		return player.getBet();
	}
	public Player getPlayer()
	{
		return player;
	}
	public Dealer getDealer()
	{
		return dealer;
	}
}
