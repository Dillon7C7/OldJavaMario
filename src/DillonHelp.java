// This class will contain the JDialog constructor, called when a user clicks help.
// This class contains the JDialog that pops up when the help option is clicked in the JMenu.

import javax.swing.*;
import java.awt.*;

public class DillonHelp extends JDialog
{	
  Image howtoplay;
	
  public DillonHelp()
  {  
	ImageIcon howtoplayi = new ImageIcon("resources/howtoplay.png");
	howtoplay = howtoplayi.getImage();
	  
	setTitle("Instructions"); 
	setLocation(100, 100);  
	setSize(243, 230);  

    repaint();
    
    setResizable(false);
    setVisible(true);
  } 
  
  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    
    g2d.drawImage(howtoplay, 0, 28, null);
  }  
}




