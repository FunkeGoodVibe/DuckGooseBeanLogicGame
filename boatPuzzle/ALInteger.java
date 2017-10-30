/*
 *
 * Stefan & Funke.
 * 
 * CONTROLLER.
 * This is the controller class for the buttons. 
 *
 */
 

package boatPuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ALInteger extends Observable implements ActionListener {

	private int toReturn;

	public ALInteger(int toReturn) {
		super();
		this.toReturn = toReturn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setChanged();
		notifyObservers(new Integer(toReturn));
	}

}
