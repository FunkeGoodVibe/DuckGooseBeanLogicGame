/*
 * 
 * @ Stefan & Funke. 
 * 
 *  VIEW class. Displays the game GUI to user. 
 *  The Main frame consists of a...
 *  	 left/right river, 
 *  	 left/right bank, 
 *  	 4 items (boat/fox/bean/boat)
 * 	The boat contains the farmer. (i.e. the farmer is attached to the boat)
 *  The game win is determined by the side of the river, the boat is on. 
 *  The game is controlled from the GameModel class. 
 *  This class contains methods to allow the VIEW to communicate with the MODEL.
 *  
 * */
package boatPuzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TopFrameBoatPuzzle extends JFrame implements Observer{
	/**
	 *  This will display the games graphics. 
	 *  
	 */
	
	private static final long serialVersionUID = 1126347731751085591L;

	//This creates the panels for the river, 2 banks, and buttons.
	private JPanel centerPanel;
	private JPanel westPanel;
	private JPanel eastPanel;
	private JPanel southPanel;

	//this creates the two sides of the river.
	private JPanel centerLeftPanel;
	private JPanel centerRightPanel;

	//preparation to display the icons.
	private ItemPanel fox;
	private ItemPanel goose;
	private ItemPanel beans;

	//create the 4x button objects, for each component. 
	private ButtonCollection boatButtons;
	private ButtonCollection foxButtons;
	private ButtonCollection gooseButtons;
	private ButtonCollection beansButtons;
	
	//create the action listeners for each event. (i.e. each component left/right move)
	//this would inform the view, to update. 
	private ALInteger boatLeft;
	private ALInteger boatRight;
	private ALInteger foxLeft;
	private ALInteger foxRight;
	private ALInteger gooseLeft;
	private ALInteger gooseRight;
	private ALInteger beansLeft;
	private ALInteger beansRight;
	
	private Boat boat;
	//private static String scoreText;

	private static GameModel gameModel = new GameModel();

	public TopFrameBoatPuzzle() throws HeadlessException 
	{
		super();
		
		setLayout(new BorderLayout());
		
		centerPanel = new JPanel(new GridLayout(1, 2));			//add image here.
		
		westPanel = new BackgroundPanel("resources/grass.png");	
		westPanel.setLayout(new GridLayout(3, 1));			//add the image here
		
		eastPanel = new BackgroundPanel("resources/grass.png");			//add the image here.
		eastPanel.setLayout(new GridLayout(3, 1));
		
		southPanel = new JPanel(new GridLayout(1, 4, 20, 20));
		
		centerLeftPanel = new BackgroundPanel("resources/water.png");
		centerRightPanel = new BackgroundPanel("resources/water.png");
		centerLeftPanel.setOpaque(false);
		centerRightPanel.setOpaque(false);

		fox = new ItemPanel(new ImageIcon("resources/FoxEdited.png"));
		goose = new ItemPanel(new ImageIcon("resources/GooseEdited.png"));
		beans = new ItemPanel(new ImageIcon("resources/BeanEdited.png"));

		boatButtons = new ButtonCollection("Boat");
		foxButtons = new ButtonCollection("Fox");
		gooseButtons = new ButtonCollection("Goose");
		beansButtons = new ButtonCollection("Beans");

		boat = new Boat();
		
		gameModel = new GameModel();
		
		boatLeft = new ALInteger(0);
		boatRight = new ALInteger(1);
		foxLeft = new ALInteger(2);
		foxRight = new ALInteger(3);
		gooseLeft = new ALInteger(4);
		gooseRight = new ALInteger(5);
		beansLeft = new ALInteger(6);
		beansRight = new ALInteger(7);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initWidgets();
		initListenersAndObservers();
	}
	//set the layout. 
	public void initWidgets() 
	{
		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.EAST);
		add(southPanel, BorderLayout.SOUTH);

		eastPanel.add(fox);
		eastPanel.add(goose);
		eastPanel.add(beans);
		eastPanel.setBackground(Color.GREEN);
		
		eastPanel.setOpaque(true);
		eastPanel.setPreferredSize(new Dimension(100, 0));

		westPanel.setBackground(Color.GREEN);
		westPanel.setOpaque(true);
		westPanel.setPreferredSize(new Dimension(100, 0));

		southPanel.add(boatButtons);
		southPanel.add(foxButtons);
		southPanel.add(gooseButtons);
		southPanel.add(beansButtons);

		centerPanel.add(centerLeftPanel);
		centerPanel.add(centerRightPanel);
		centerPanel.setBackground(Color.BLUE);
		centerPanel.setOpaque(true);
		
		centerRightPanel.setLayout(new GridLayout(1, 1));
		centerLeftPanel.setLayout(new GridLayout(1, 1));
		
		centerRightPanel.add(boat);

		pack();
		setVisible(true);
	}
	
	//Controller will notify view
	private void initListenersAndObservers() 
	{
		boatButtons.addLeftButtonListener(boatLeft);
		boatButtons.addRightButtonListener(boatRight);
		foxButtons.addLeftButtonListener(foxLeft);
		foxButtons.addRightButtonListener(foxRight);
		gooseButtons.addLeftButtonListener(gooseLeft);
		gooseButtons.addRightButtonListener(gooseRight);
		beansButtons.addLeftButtonListener(beansLeft);
		beansButtons.addRightButtonListener(beansRight);
		
		boatLeft.addObserver(gameModel);
		boatRight.addObserver(gameModel);
		foxLeft.addObserver(gameModel);
		foxRight.addObserver(gameModel);
		gooseLeft.addObserver(gameModel);
		gooseRight.addObserver(gameModel);
		beansLeft.addObserver(gameModel);
		gooseRight.addObserver(gameModel);
		
		gameModel.addObserver(this);
	}

	@Override
	//update the user view. 
	public void update(Observable o, Object arg) 
	{
		//Put boat in its place
		if (gameModel.isBoatOnRight())
		{
			centerLeftPanel.remove(boat);
			centerLeftPanel.revalidate();
			centerLeftPanel.repaint();
			centerRightPanel.add(boat);
			centerRightPanel.revalidate();
			centerRightPanel.repaint();
		}
		else
		{
			centerRightPanel.remove(boat);
			centerRightPanel.revalidate();
			centerRightPanel.repaint();
			centerLeftPanel.add(boat);
			centerLeftPanel.revalidate();
			centerLeftPanel.repaint();
		}
		
		//Put fox in its place
		if (gameModel.isFoxOnBoat())
		{
			putInBoat(fox);
		}
		else if (gameModel.isFoxOnRightGrass())
		{
			putInEastPanel(fox);
		}
		else 
		{
			putInWestPanel(fox);
		}
		
		//Put goose in its place
		if (gameModel.isGooseOnBoat())
		{
			putInBoat(goose);
		}
		else if (gameModel.isGooseOnRightGrass())
		{
			putInEastPanel(goose);
		}
		else 
		{
			putInWestPanel(goose);
		}
		
		//Put beans in their place
		if (gameModel.isBeansOnBoat())
		{
			putInBoat(beans);
		}
		else if (gameModel.isBeansOnRightGrass())
		{
			putInEastPanel(beans);
		}
		else 
		{
			putInWestPanel(beans);
		}
		
		//String to allocate to title for score and game over message
		String titleString = new Integer(gameModel.getScore()).toString();
		
		//If the game is over
		if (gameModel.isGameOver())
		{
			titleString += " Game Over!";
			
			//If the player has lost
			if (gameModel.isGooseBeanAlone() || gameModel.isFoxGooseAlone())
			{
				titleString += " You Lose!";
			}
			else //If the player has won
			{
				titleString += " You Win!";
			}
			
			//If the beans were eaten
			if (gameModel.isGooseBeanAlone())
			{
				titleString += " Goose has eaten the Beans!";
			}
			
			//If the goose was eaten
			if (gameModel.isFoxGooseAlone())
			{
				titleString += " Fox has eaten the Goose!";
			}
		}
		this.setTitle(titleString);
	}
	
	private void putInBoat(ItemPanel moved)
	{
		if (!(boat.getCarrying() == moved))
		{
			eastPanel.remove(moved);
			eastPanel.revalidate();
			eastPanel.repaint();
			westPanel.remove(moved);
			westPanel.revalidate();
			westPanel.repaint();
			boat.setCarrying(moved);
		}
	}
	
	private void putInEastPanel(ItemPanel moved)
	{
		if (boat.getCarrying() == moved)
		{
			boat.setCarrying(null);
		}
		westPanel.remove(moved);
		westPanel.revalidate();
		westPanel.repaint();
		eastPanel.add(moved);
		eastPanel.revalidate();
		eastPanel.repaint();
	}
	
	private void putInWestPanel(ItemPanel moved)
	{
		if (boat.getCarrying() == moved)
		{
			boat.setCarrying(null);
		}
		eastPanel.remove(moved);
		eastPanel.revalidate();
		eastPanel.repaint();
		westPanel.add(moved);
		westPanel.revalidate();
		westPanel.repaint();
	}
	
	public static void main(String[] args) 
	{
		@SuppressWarnings("unused")
		TopFrameBoatPuzzle frame = new TopFrameBoatPuzzle();
	}
}