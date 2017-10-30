/*
 * 
 * @ Stefan & Funke. 
 * ITEMPANEL class.
 * Replicate the Items (Fox/Goose/Bean)
 * 
 *
 * 
 * */
package boatPuzzle;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1370087145451062927L;

	private JLabel label;

	public ItemPanel(String labelContents) {
		super(new GridLayout(1, 1));

		setOpaque(false);

		label = new JLabel(labelContents);

		setPreferredSize(new Dimension(100, 100));
		add(label);
	}

	public ItemPanel(ImageIcon icon) {
		super(new GridLayout(1, 1));

		setOpaque(false);

		label = new JLabel(icon);

		setPreferredSize(new Dimension(100, 100));
		add(label);
	}
}
