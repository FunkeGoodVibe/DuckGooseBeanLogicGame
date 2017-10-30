/*
 * 
 * @ Stefan & Funke.  
 *  This is the Boat class. 
 *  The Boat has different properties than the ItemPanel, so a seperate class is created.
 *  The boat has a "carrying" panel, so can hold maximum of 1 item.
 *  The farmer is attached to the boat, so can never leave the boat.
 * 
 * */
package boatPuzzle;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Boat extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4802389232215639086L;

	private ItemPanel boatPanel;
	private JPanel carrying;
	private GridLayout layout;

	public Boat() {
		super();

		setOpaque(false);

		layout = new GridLayout(2, 1);
		this.setLayout(layout);

		boatPanel = new ItemPanel(new ImageIcon("resources/BoatWithFarmerEdited.png"));
		boatPanel.setPreferredSize(new Dimension(300, 300));

		add(boatPanel);
	}

	public void setCarrying(ItemPanel carrying) {
		this.carrying = carrying;
		if (carrying != null) {
			add(carrying);
			revalidate();
			repaint();
		}
	}

	public JPanel getCarrying() {
		return carrying;
	}
}
