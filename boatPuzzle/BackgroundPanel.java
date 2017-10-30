package boatPuzzle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel  {
	
	private static final long serialVersionUID = -4802389232215639086L;
	private BufferedImage icon;
	
	public BackgroundPanel(String filePath)
	{
		try{
			
			icon = ImageIO.read(new File(filePath));
		}catch (IOException e) {
			
			e.printStackTrace();
			
		}		
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(icon, 0, 0, this); // see javadoc for more info on the parameters            
    }

	
	
		
}
