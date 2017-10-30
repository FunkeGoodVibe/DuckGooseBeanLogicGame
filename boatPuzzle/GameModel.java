/*
 * 
 * @ Stefan & Funke. 
 * 
 * This is the MODEL.
 * Determines the current state of the game.
 * Method updates score on every click.
 * Method informs the view, to update, on each click.
 * Numbers are assigned to each button, which tells the action listener which button was pressed. 
 * Determines if the game is won/lost.
 * 
 * 
 * 
 * */package boatPuzzle;

import java.util.Observable;
import java.util.Observer;

public class GameModel extends Observable implements Observer {

	private boolean boatOnRight;		
	private boolean foxOnRightGrass;
	private boolean gooseOnRightGrass;
	private boolean beansOnRightGrass;
	
	private boolean foxOnBoat;
	private boolean gooseOnBoat;
	private boolean beansOnBoat;
	private boolean boatFull;
	
	private boolean foxGooseAlone;
	private boolean gooseBeanAlone;
	
	private boolean gameOver;  
	private int score; 
	
	public GameModel()
	{		
		boatOnRight = true;
		foxOnRightGrass = true;
		gooseOnRightGrass = true;
		beansOnRightGrass = true;
		foxOnBoat = false;
		gooseOnBoat = false;
		beansOnBoat = false;
		boatFull = false;
		foxGooseAlone = false;
		gooseBeanAlone = false;
		gameOver = false;
		score = 0;
	}

	//determine game win
	public void determineGameOver()
	{
		//FOX + GOOSE ALONE (right grass).
		if ((!boatOnRight && foxOnRightGrass && gooseOnRightGrass && !gooseOnBoat && !foxOnBoat) 
				|| (boatOnRight && !foxOnRightGrass && !gooseOnRightGrass && !gooseOnBoat && !foxOnBoat))
		{
			foxGooseAlone = true;
			gameOver = true;
		}
		
		//GOOSE + BEAN ALONE (right grass).
		if ((!boatOnRight && beansOnRightGrass && gooseOnRightGrass && !gooseOnBoat && !beansOnBoat) 
				|| (boatOnRight && !beansOnRightGrass && !gooseOnRightGrass && !gooseOnBoat && !beansOnBoat))
		{
			gooseBeanAlone = true;
			gameOver = true;
		}
		
		//WIN STATE
		if (!boatOnRight && !foxOnBoat && !foxOnRightGrass && !gooseOnBoat && !gooseOnRightGrass && !beansOnBoat && !beansOnRightGrass)
		{
			gameOver = true;
		}
	}
	
	@Override
	public void update(Observable listener, Object moved) 
	{
		if (!gameOver)
		{
			//Assign variable to fetched int from ActionListener
			int numRecieved = ((Integer) moved).intValue();
	
			switch (numRecieved) 
			{
				//"<" BOAT MOVE LEFT
				case 0:
					if (boatOnRight == true)
					{
						boatOnRight = false;
						score--;
					}
					break;
					
				//">"BOAT MOVE RIGHT
				case 1:
					if (boatOnRight == false) 
					{
						boatOnRight = true;
						score--;
					}
					break;
					
				//"<" FOX MOVE LEFT
				case 2:
					if (foxOnRightGrass && boatOnRight && !boatFull) 
					{
						foxOnRightGrass = false;
						foxOnBoat = true;		
						boatFull = true;
						score--;
					} 
					else if (foxOnBoat && !boatOnRight)
					{
						foxOnBoat = false;
						boatFull = false;
						score--;
					}
					break;
					
				//">" FOX MOVE RIGHT
				case 3:
					if (foxOnBoat && boatOnRight)
					{
						foxOnRightGrass = true;
						foxOnBoat = false;
						score--;
					} 
					else if (!foxOnRightGrass && !boatOnRight && !boatFull)
					{
						foxOnBoat = true;
						boatFull = true;
						score--;
					}
					break;
					
				//"<" GOOSE MOVE LEFT
				case 4: 
					if (gooseOnRightGrass && boatOnRight && !boatFull) 
					{
						gooseOnRightGrass = false;
						gooseOnBoat = true;	
						boatFull = true;
						score--;
					} 
					else if (gooseOnBoat && !boatOnRight)
					{
						gooseOnBoat = false;
						boatFull = false;
						score--;
					} 
					break;
					
				//">" GOOSE MOVE RIGHT
				case 5:
					if (gooseOnBoat && boatOnRight) 
					{
						gooseOnRightGrass = true;
						gooseOnBoat = false;
						boatFull = false;
						score--;
					}
					else if (!gooseOnRightGrass && !boatOnRight && !boatFull)
					{
						gooseOnBoat = true;
						boatFull = true;
						score--;
					}
					break;
					
				//"<" BEAN MOVE LEFT
				case 6: 
					if (beansOnRightGrass && boatOnRight && !boatFull) 
					{
						beansOnRightGrass = false;
						beansOnBoat = true;	
						boatFull = true;
						score--;	
					}
					else if (beansOnBoat && !boatOnRight)
					{
						beansOnBoat = false;
						boatFull = false;
						score--;
					}
					break;
					
				//">" BEAN MOVE RIGHT
				case 7: 
					if (beansOnBoat && boatOnRight) 
					{
						beansOnRightGrass = true;
						beansOnBoat = false;
						boatFull = false;
						score--;
					}
					else if (!beansOnRightGrass && !boatOnRight && !boatFull)
					{
						beansOnBoat = true;
						boatFull = true;
						score--;
					}
					break;	
			}	
		}
		
		determineGameOver();
		
		//Inform the UI that it must update
		setChanged();
		notifyObservers();
	}
	
	public boolean isFoxGooseAlone() {
		return foxGooseAlone;
	}

	public boolean isGooseBeanAlone() {
		return gooseBeanAlone;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean isBoatOnRight() 
	{
		return boatOnRight;
	}

	public boolean isFoxOnRightGrass() 
	{
		return foxOnRightGrass;
	}

	public boolean isGooseOnRightGrass() 
	{
		return gooseOnRightGrass;
	}

	public boolean isBeansOnRightGrass() 
	{
		return beansOnRightGrass;
	}

	public boolean isFoxOnBoat() 
	{
		return foxOnBoat;
	}

	public boolean isGooseOnBoat() 
	{
		return gooseOnBoat;
	}

	public boolean isBeansOnBoat() 
	{
		return beansOnBoat;
	}

	public int getScore() 
	{
		return score;
	}
}