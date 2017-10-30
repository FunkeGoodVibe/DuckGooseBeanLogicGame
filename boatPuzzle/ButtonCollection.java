/*
 * 
 * @ Stefan & Funke. 
 * BUTTON class. 
 * Replicate buttons for each item (i.e. Boat/Fox/Goose/Bean)
 * As they have similar functionality. 
 * 
 *
 */
package boatPuzzle;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonCollection extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6947457844664431671L;

	private JLabel label;
	private JButton leftButton;
	private JButton rightButton;

	public ButtonCollection(String labelText) {
		super();

		this.setLayout(new GridLayout(1, 3, 10, 10));

		label = new JLabel(labelText);
		leftButton = new JButton("<");
		rightButton = new JButton(">");

		initWidgets();
	}

	public void initWidgets() {
		add(label);
		add(leftButton);
		add(rightButton);
	}

	public void addLeftButtonListener(ActionListener listener) {
		leftButton.addActionListener(listener);
	}

	public void addRightButtonListener(ActionListener listener) {
		rightButton.addActionListener(listener);
	}
}
