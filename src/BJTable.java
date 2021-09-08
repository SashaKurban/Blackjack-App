//Oleksandra Kurbanova 

//class represents a BJ table and is responsible for the GUI of the program
//class builds a BJ table and then triggers the backend game processes and changes in GUI with user's 
//interactions 

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class BJTable extends JFrame{
	
	protected BJGame game;
	private int currentHand;
	private boolean firstMove;
	private boolean splitPerformed;
	
	private CardPanel dealerCardPanel;
	private CardPanel playerCardPanel;
	private CardPanel playerCardPanelSplit;
	
	private MoneyLabel fundsText;
	private MoneyLabel betAmount;
	
	private Label dealerScore;
	private Label playerScore;
	private Label playerScoreSplit;
	
	private Label hiddenCard;
	private Label labelTwo;
	private Label firstCardPlayer;
	private Label secondCardPlayer;
	
	
	private JTextField gameStatus;
	
	private Button hitButton;
	private Button standButton;
	private Button doubleDownButton;
	private Button splitButton;
	private Button insuranceButton;
	private Button newGameButton;
	private Button surrenderButton;
	
	/*
	 * constructor methods to build the table and place all the components
	 * includes main constructor, and constructors for Top, Center, Left, Right and Bottom panels
	 */
	
	
	public BJTable(){
		//initialize the new game
		game = new BJGame();
		//set size for the GUI window
		final int TABLE_WIDTH = 1200;
		final int TABLE_HEIGHT = 800;
		
		setTitle("BlackJack");
		
		setMinimumSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		setMaximumSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		
		setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//build and add panels
		add(buildTopPanel(), BorderLayout.NORTH);
		add(buildLeftPanel(), BorderLayout.WEST);
		add(buildRightPanel(), BorderLayout.EAST);
		add(buildBottomPanel(), BorderLayout.SOUTH);
		add(buildCenterPanel(), BorderLayout.CENTER);
		//set player's funds
		updateMoney();
		setVisible(true);	
	}
	
	//method to build top panel
	public JPanel buildTopPanel()
	{
		//create top panel
		JPanel panelTop =  new JPanel();
		//set background 
		panelTop.setBackground(new Color(0, 75, 35));
		//set layout
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
		//text field to show game status messages
		gameStatus = new JTextField("");
		gameStatus.setBackground(new Color(13, 36, 13));
		gameStatus.setForeground(new Color(247, 208, 2));
		gameStatus.setFont(new Font("Helvetica", Font.BOLD, 18));
		gameStatus.setEditable(false);
		gameStatus.setHorizontalAlignment(JLabel.CENTER);
		//add text field to the panel
		panelTop.add(gameStatus);
		//return panel
		return panelTop;
	}
	
	//method to build center panel
	public JPanel buildCenterPanel()
	{
		//create center panel
		JPanel panelCenter = new JPanel();
		//set background color
		panelCenter.setBackground(new Color(0, 75, 35));
		//set layout
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		//create panels for dealer cards and 2 for player hands
		dealerCardPanel= new CardPanel();
		playerCardPanel = new CardPanel();
		playerCardPanelSplit = new CardPanel();
		//labels to point to player and dealer scores
		dealerScore = new Label("");
		playerScore = new Label("");
		playerScoreSplit = new Label("");
		//add labels to card panels
		dealerCardPanel.add(dealerScore);
		playerCardPanel.add(playerScore);
		playerCardPanelSplit.add(playerScoreSplit);
		//label to point to palyer's cards and align it to the center
		Label playerLabel = new Label("  Player");
		playerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		playerLabel.setBorder(BorderFactory.createEmptyBorder(10,0,30,0));
		//label to point to dealer's cards and align it to the center
		Label dealerLabel = new Label("  Dealer");
		dealerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		dealerLabel.setBorder(BorderFactory.createEmptyBorder(0,0,100,0));
		//add panels 
		panelCenter.add(dealerCardPanel);
		//add label to point to dealer's cards
		panelCenter.add(dealerLabel);
		panelCenter.add(playerCardPanelSplit);
		panelCenter.add(playerCardPanel);
		//add labels pointing to player's cards
		panelCenter.add(playerLabel);
		//return center panel
		return panelCenter;
	}
	
	//method to build left panel with current funds
	public JPanel buildLeftPanel()
	{
		//create left panel
		JPanel panelLeft = new JPanel();
		//set background
		panelLeft.setBackground(new Color(0, 75, 35));
		//set layout
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		//labels to point to player's funds
		Label fundsLabel = new Label("  Current Funds");
		//variable that shows current funds 
		fundsText = new MoneyLabel("");
		//buttons to start new game or surrender
		newGameButton = new Button("New Game");
		newGameButton.setEnabled(true);
		surrenderButton = new Button(" Surrender");
		//add labels to panel
		panelLeft.add(fundsLabel);
		panelLeft.add(fundsText);
		//add buttons to panel
		panelLeft.add(newGameButton);
		panelLeft.add(surrenderButton);
		//return the panel
		return panelLeft;
	}
	
	//method to build right panel with current bet and buttons(split, double down and insurance)
	public JPanel buildRightPanel()
	{
		//create right panel
		JPanel panelRight = new JPanel();
		//set background color
		panelRight.setBackground(new Color(0, 75, 35));
		//set layout
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		//label to point to player's bet
		Label betLabel = new Label("     Current Bet   ");
		//variable that shows current bet amount
		betAmount = new MoneyLabel("    $0.00");
		//create double down, split  and insurance buttons
		doubleDownButton = new Button("Double Down");
		splitButton = new Button("        Split       ");
		insuranceButton = new Button("   Insurance   ");
		//add buttons and bet labels
		panelRight.add(betLabel);
		panelRight.add(betAmount);
		panelRight.add(splitButton);
		panelRight.add(doubleDownButton);
		panelRight.add(insuranceButton);
		//return panel
		return panelRight;
	}
	
	//method to build bottom panel with hit or stand buttons 
	public JPanel buildBottomPanel()
	{
		//create bottom panel
		JPanel panelBottom = new JPanel();
		//set background color
		panelBottom.setBackground(new Color(0, 75, 35));
		//set margins
		panelBottom.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//create hit and stand buttons
		hitButton = new Button("Hit");
		standButton = new Button("Stand");
		//add buttons to the panel
		panelBottom.add(hitButton);
		panelBottom.add(standButton);
		//return panel
		return panelBottom;
	}
	
	
	
	
	
	/*
	 * custom classes used in building the table
	 * includes Card Panel, Money Label, Label and Button
	 */
	
	
	//card panel is a panel that holds player's and dealer's cards, to be added to center panel
	private class CardPanel extends JPanel
	{
		private CardPanel()
		{
			super();
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setBackground(new Color(0, 75, 35));
			setPreferredSize(new Dimension(500,200));
		}
	}
	
	//money label is label used for money variables, with specified font, color and border
	private class MoneyLabel extends JLabel
	{
		private MoneyLabel(String str)
		{
			super(str);
			setFont(new Font("Helvetica", Font.BOLD, 30));
			setForeground(new Color(247, 208, 2));
			setBorder(BorderFactory.createEmptyBorder(0,0,170,0));
		}
	}
	
	//label is used with specified font, font color and border for any text output
	private class Label extends JLabel
	{
		private Label(String str)
		{
			super(str);
			setFont(new Font("Helvetica", Font.BOLD, 18));
			setForeground(Color.WHITE);
			setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
		}
		private Label(Icon img)
		{
			super(img);
		}
	}
	
	//button to represent buttons with specified size, font and event listener added
	private class Button extends JButton
	{
		private Button(String str)
		{
			super(str);
			setEnabled(false);
			setFocusPainted(false);
			setFont(new Font("Helvetica", Font.BOLD, 14));
			setPreferredSize(new Dimension(150,35));
			setMaximumSize(new Dimension(150,35));
			addActionListener(new ButtonListener());
		}
	}
	
	
	
	/*
	 * event listeners for each game button that control game operations and output 
	 * includes New Game, Surrender, Hit, Stand, Insurance, Split and Double Down
	 */

	
	
	//handles events such as hit, stand, surrender, new game, insurance, split and double down
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == newGameButton)
			{
				//pop up window asking player for a new bet
				double bet = betPrompt(game.getFunds());
				//only start new game if bet is above 0
				if(bet > 0)
				{
					//reset the table and start a new game
					newGame(bet);
				}
			}
			else if(e.getSource() == surrenderButton)
			{
				//if first move, do early surrender and return half of the bet
				if(firstMove)
				{
					game.earlySurrender();
					endGame("Early Surrender. Half of the bet is returned");
				}
				else {
					//lose the game for all hands
					game.lost();
					endGame("Surrender. You lost your bet");
				}
			}
			else if(e.getSource() == hitButton)
			{
				//if first move, disable double down / split / insurance
				if(firstMove)
				{
					disableOptionalButtons();
					firstMove = false;
				}
				//call hit method to perform the operation
				hit();
			}
			else if(e.getSource() == standButton)
			{
				//if two hands in the game, and player is one the first hand, move onto second hand
				if(splitPerformed && currentHand == 0)
				{
					currentHand++;
					gameStatus.setText("Playing second hand");
				}
				else
				{
					//if no hands left to play, call stand method to deal to dealer
					stand();
				}
			}
			else if(e.getSource() == doubleDownButton)
			{
				//disableOptionalButtons();
				disableOptionalButtons();
				doubleDown();
			}
			else if(e.getSource() == splitButton)
			{
				//Signify that first move was made and disable optional buttons
				firstMove = false;
				disableOptionalButtons();
				//signify that split was performed
				splitPerformed = true;
				split();
			}
			else if(e.getSource() == insuranceButton)
			{
				//Signify that first move was made and disable optional buttons
				firstMove = false;
				disableOptionalButtons();
				insurance();
			}
			
		}
	}
	
	
	/*
	 * methods called by the action listeners that invoke backend game operations and update GUI
	 * includes double down, hit, insurance, new game, split and stand
	 */
	
	
	
	
	//method to perform double down operation
	public void doubleDown()
	{
		//double the bet and update bet and funds
		game.getPlayer().doubleDown();
		updateMoney();
		//deal one more card to player 
		dealToPlayer(game.dealToPlayer(currentHand));
		//check if player busted or got blackjack
		if(game.playerBusted(currentHand))
		{
			game.lost();
			endGame("You Busted. You Lost");
		}
		else if(game.playerBlackJack(currentHand))
		{
			//if dealer got blackjack, return bet
			if(game.isDraw(currentHand))
			{
				game.draw(currentHand);
				endGame("Draw. Bet is Returned");
			}
			else {
				game.win();
				endGame("BlackJack! You Won!");
			}
		}
		else {
			//if neither bust not BJ, move into the stand and deal to dealer
			stand();
		}	
	}
	
	//method to perform hit operation and to check if player busted/got blackjack and if game should end
	public void hit()
	{
		dealToPlayer(game.dealToPlayer(currentHand));
		//if player has two hands, don't end the game on BJ or bust.
		if(game.playerBusted(currentHand))
		{
			if(splitPerformed)
			{
				if(currentHand == 0) {
					gameStatus.setText("First hand busted. Playing second hand");
					currentHand++;
				}
				//if second hand busted, move into stand and deal to dealer
				else {
					stand();
				}
			}
			//if only one hand in the game, end with a loss for busting
			else {
				game.lost();
				endGame("You Busted. You Lost");
			}
		}
		else if(game.playerBlackJack(currentHand))
		{
			if(splitPerformed)
			{
				if(currentHand == 0)
				{
					gameStatus.setText("First hand stood on 21.. Playing second hand");
					currentHand++;
				}
				else {
					//if second hand got BJ, move into stand
					stand();
				}
			}
			//if only one hand in the game, check for draw or total win and end the game
			else {
				stand();
			}
		}
	}

	//method to place and check insurance bet
	public void insurance()
	{
		//place insurance
		game.getPlayer().setInsurance();
		//update funds
		updateMoney();
		//check if dealer has BlackJack, and place game Status message accordingly
		if(game.dealerBlackJack())
		{
			game.getPlayer().winInsurance();
			//end game if dealer has blackjack
			endGame("Dealer has BlackJack. Your Insurance Won!");
		}
		else {
			game.getPlayer().lostInsurance();
			gameStatus.setText("Dealer doesn't have a BlackJack. Insurance was lost");
		}
		
	}
	//method to start a new game by reseting table, assigning bet and dealing first hand or 2 cards
	public void newGame(double bet)
	{
		//clear game status 
		gameStatus.setText("");
		//set first move to true to control double down, split, insurance opportunities
		firstMove = true;
		//set split performed to false
		splitPerformed = false;
		//start new game and set the bet
		game.newGame(bet);
		updateMoney();
		//reset center panel for new game
		add(buildCenterPanel(), BorderLayout.CENTER);

		//deal cards to dealer and player, setting current hand index to 0
		currentHand = 0;
		//remember first card dealt to player in case of a split
		firstCardPlayer = dealToPlayer(game.dealToPlayer(currentHand));
		dealToDealer();
		//remember second card dealt to player in case of a split
		secondCardPlayer = dealToPlayer(game.dealToPlayer(currentHand));
		dealToDealer();

		//if player got BJ, check for draw and proceed to victory if a clear win
		if(game.playerBlackJack(0))
		{
			if(game.isDraw(currentHand))
			{
				game.draw(currentHand);
				endGame("Draw. Bet is Returned");
			}
			else {
				game.BJwin();
				endGame("BlackJack! You Won!");
			}
		}
		else {
			//enable buttons to allow for optional moves
			enableButtons();
		}
	}

	//method to perform split operation
	public void split()
	{
		//perform the split 
		game.getPlayer().split(currentHand);
		//update funds and bet
		updateMoney();
		//clear player's cards
		firstCardPlayer.setVisible(false);
		secondCardPlayer.setVisible(false);
		//add first cards to the two new hands and add two new cards to each
		dealToPlayer(game.getPlayer().getHand(0).getCard(0));
		dealToPlayer(game.dealToPlayer(currentHand));
		//switch to second hand
		currentHand++;
		dealToPlayer(game.getPlayer().getHand(1).getCard(0));
		dealToPlayer(game.dealToPlayer(currentHand));
		//switch back to first hand
		currentHand--;
		gameStatus.setText("Palying first Hand");
	}
	
	//method used in end game to deal to dealer and compare dealer and player
	public void stand()
	{
		String result = "";
		//reset current hand to 0 to evaluate each hand and reveal dealer's hand and score
		currentHand = 0;
		revealDealerCard();
		dealerScore.setText(String.valueOf(game.getDealer().getHand().getValue()) + "  ");
		//if two hands in the game, and both busted or BJ, don't deal to dealer
		if(splitPerformed && (game.playerBusted(0) || game.playerBlackJack(0)) 
				&& (game.playerBusted(1) || game.playerBlackJack(1)))
		{
			//check if first hand busted, got BJ or draw
			if(game.playerBusted(currentHand))
			{
				game.lost(currentHand);
				result += "First Hand Busted. ";
			}
			else if(game.playerBlackJack(currentHand))
			{
				if(game.isDraw(currentHand))
				{
					game.draw(currentHand);
					result += "First Hand got Draw. ";
				}
				else {
					game.win(currentHand);
					result += "First Hand stood on 21. ";
				}
			}
			currentHand++;
			//check if second hand busted, got BJ or draw
			if(game.playerBusted(currentHand))
			{
				game.lost(currentHand);
				result += "Second Hand Busted. ";
			}
			else if(game.playerBlackJack(currentHand))
			{
				if(game.isDraw(currentHand))
				{
					game.draw(currentHand);
					result += "Second Hand got Draw. ";
				}
				else {
					game.win(currentHand);
					result += "Second Hand stood on 21. ";
				}

			}
			endGame(result);
			return;
		}
		
		//if at least one hand not busted/BJ, then deal to dealer until 17
		while(game.getDealer().getHand().getValue() < 17)
		{
			dealToDealer();
			dealerScore.setText(String.valueOf(game.getDealer().getHand().getValue()) + "  ");
		}
		
		//check if dealer busted or got BJ
		if(game.dealerBusted())
		{
			//win on all hands if dealer busted
			game.win();
			result += "Dealer Busted. You Won!";
		}
		else if(game.dealerBlackJack())
		{
			//if dealer got black jack, all hands lost, unless there is a draw
			result += "Dealer got BlackJack. ";
			//check if first hand got draw
			if(game.isDraw(currentHand))
			{
				game.draw(currentHand);
				if(splitPerformed)
					result += "First Hand got Draw. ";
				else
					result += "Draw. Bet is Returned";
			}
			else {
				game.lost(currentHand);
				if(splitPerformed)
					result += "First Hand Lost. ";
				else
					result += "You Lost. ";
			}
			//if two hands, check second hand for draw or lost
			if(splitPerformed)
			{
				currentHand++;
				if(game.isDraw(currentHand))
				{
					game.draw(currentHand);
					result += "Second Hand got Draw. ";
				}
				else {
					game.lost(currentHand);
					result += "Second Hand Lost. ";
				}
			}
		}
		//if dealer didn't get BJ or busted/ compare the points for each hand
		else {
			//if no split performed, player's black jack or bust would not end up in this method, 
			//so only check for draw or win/loss
			if(!splitPerformed)
			{
				if(game.playerWon(currentHand))
				{
					game.win();
					result += "You Won!";
				}
				else if(game.isDraw(currentHand))
				{
					game.draw(currentHand);
					result +=  "Draw. Bet is Returned";
				}
				else {
					game.lost();
					result += "You Lost.";
				}
			}
			else {
				//if split was performed, check each hand for bust, draw or won
				if(game.playerBusted(currentHand))
				{
					game.lost(currentHand);
					result += "First Hand Busted. ";
				}
				else if(game.isDraw(currentHand))
				{
					game.draw(currentHand);
					result += "Frist Hand got Draw. ";
				}
				else if(game.playerWon(currentHand)){
					game.win(currentHand);
					result += "First Hand Won! ";
				}
				else {
					game.lost(currentHand); 
					result += "First Hand Lost. ";
				}
				//check second hand
				currentHand++;
				
				if(game.playerBusted(currentHand))
				{
					game.lost(currentHand);
					result += "Second Hand Busted. ";
				}
				else if(game.isDraw(currentHand))
				{
					game.draw(currentHand);
					result += "Second Hand got Draw. ";
				}
				else if(game.playerWon(currentHand)){
					game.win(currentHand);
					result += "Second Hand Won! ";
				}
				else {
					game.lost(currentHand);
					result += "Second Hand Lost. ";
				}
			}
		}
		endGame(result);
	}
	
	
	
	/*
	 * helper methods that assist large button-dependent methods in GUI updates
	 * includes Enable buttons, Disable All buttons, Disable Optional Buttons, Bet Prompt, 
	 * Update Money, Reveal Dealer Card, End Game,Deal to Dealer, Deal to Player
	 */
	
	
	
	//method to prompt user for a new bet
	public double betPrompt(double fund)
	{
		while(true)
		{
			try {
				double bet = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter your bet: $"));
				if(bet > fund)
				{
					int a = JOptionPane.showConfirmDialog(null, "Insufficient funds, try again?",
							"Invalid Bet Message",JOptionPane.YES_NO_OPTION);
					if(a != JOptionPane.YES_OPTION)
					{
						return 0;
					}
				}
				else {
					return bet;
				}
			}
			catch(Exception e)
			{
				int a = JOptionPane.showConfirmDialog(null, "Invalid input, try again?","Invalid Bet Message",JOptionPane.YES_NO_OPTION );
				if(a != JOptionPane.YES_OPTION)
				{
					return 0;
				}
			}
		}
	}
	
	//method to deal card's to dealer, only show dealer's score in the end game
	public void dealToDealer()
	{
		
		Card card = game.dealToDealer();
		ImageIcon img = new ImageIcon(card.getImg());
		Label label = new Label(img);
		
		if(game.getDealer().getHand().getNumberOfCards() == 1)
		{
			//conceal first card by adding a different label in place of it
			hiddenCard = label;
			hiddenCard.setVisible(false);
			labelTwo = new Label("");
			labelTwo.setBorder(BorderFactory.createLineBorder((new Color(247, 208, 2))));
			labelTwo.setBackground(new Color(13, 36, 13));
			labelTwo.setOpaque(true);
			labelTwo.setMaximumSize(new Dimension(73,98));
			dealerCardPanel.add(labelTwo);
			dealerCardPanel.add(hiddenCard);
		}
		else {
			dealerCardPanel.add(label);
		}
	}
			
	//method to deal card to player
	public Label dealToPlayer(Card card)
	{
		ImageIcon img = new ImageIcon(card.getImg());
		Label label = new Label(img);
		//if current hand is first hand, deal to regular player card panel
		if(currentHand == 0)
		{
			playerScore.setText(String.valueOf(game.getPlayer().getHand(currentHand).getValue()) + "  ");
			playerCardPanel.add(label);
			return label;
		}
		//if dealing to second split hand, deal cards into split card panel
		else {
			playerScoreSplit.setText(String.valueOf(game.getPlayer().getHand(currentHand).getValue() + "  "));
			playerCardPanelSplit.add(label);
			return label;
		}
	}
	
	//method to disable all buttons at the end of the game
	public void disableAllButtons()
	{
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		doubleDownButton.setEnabled(false);
		splitButton.setEnabled(false);
		insuranceButton.setEnabled(false);
		surrenderButton.setEnabled(false);
	}
	
	//method to disable optional buttons after the first move
	public void disableOptionalButtons()
	{
		doubleDownButton.setEnabled(false);
		splitButton.setEnabled(false);
		insuranceButton.setEnabled(false);
	}
	
	//method to enable buttons at the start of the game depending on the game conditions met
	public void enableButtons()
	{
		//enable hit, stand and surrender since new game started
		hitButton.setEnabled(true);
		standButton.setEnabled(true);
		surrenderButton.setEnabled(true);

		//check if player has enough funds to double down
		if(game.doubleDownAllowed())
			doubleDownButton.setEnabled(true);
		//check if conditions are met to allow insurance
		if(game.insuranceAllowed())
			insuranceButton.setEnabled(true);
		//check if conditions are met to allow split
		if(game.splitAllowed())
			splitButton.setEnabled(true);
	}

	//method to display end game status, reveal dealer's hand value, update funds and bet, disable buttons
	public void endGame(String str)
	{
		revealDealerCard();
		dealerScore.setText(String.valueOf(game.getDealer().getHand().getValue()) + "  ");
		gameStatus.setText(str);
		updateMoney();
		disableAllButtons();
		
		System.out.println(game.getPlayer());
		System.out.println(game.getDealer());
	}

	//method to reveal Dealer's hidden card
	public void revealDealerCard()
	{
		labelTwo.setVisible(false);
		hiddenCard.setVisible(true);
	}

	//method to update bet and funds
	public void updateMoney()
	{
		fundsText.setText(" $" + String.format("%.2f", game.getFunds()));
		betAmount.setText("  $" + String.format("%.2f", game.getBet()));
	}
	
}
