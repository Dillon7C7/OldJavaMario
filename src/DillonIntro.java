// This file constructs the intro screen.

import javax.swing.*;
import java.awt.*;

public class DillonIntro extends JPanel implements Runnable
{
  static Image mario_background; 
  static Image ics4m, title;

  static Thread animation; // Static so we can stop it from DillonGame.

  static private int x = 10;
  static private int y = 100;
  
  public DillonIntro() 
  {
    ImageIcon background = new ImageIcon("resources/marioback.png");
	mario_background = background.getImage(); 
	  
	ImageIcon ics = new ImageIcon("resources/intro.gif");
	ics4m = ics.getImage();  
	
	ImageIcon titlee = new ImageIcon("resources/title.gif");
	title = titlee.getImage();

    animation = new Thread(this); // Initialize the thread.
	animation.start(); // Start the animation thread.
	  
    setVisible(true);
  }	
  
  public void animate() // This thread contains the logic for the ICS4M animation.
  {
    try
	{
      while (x <= 200)
	  { 
	    x += 10; 
	    Thread.sleep(100);
	    repaint();
	  }
	 
	  while (x >= 10)
	  {
	    x -= 10;
	    y += 10;  
	    Thread.sleep(100);
	    repaint();	
	  }
		  
	  while (x <= 200)
	  {
	    x += 10;
	    y -= 10;
	    Thread.sleep(100);
	    repaint();
	  }
	      
	  while (x >= 10)
	  {
	    x -= 10;
	    Thread.sleep(100);
	    repaint();
	  }	   	  
	}
		
	catch (InterruptedException e)
	{
	  System.out.println("Animation Interrupted!");
	}  
  }
 
  public void run() 
  {
    while (true)
    {
      animate();
    } 
  }

 public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g; //Graphics2D provides more sophisticated control over geometry.
    
    g2d.drawImage(mario_background, 0, 0, this.getWidth(), this.getHeight(), null); // Draw background first. 
    g2d.drawImage(ics4m, x, y, null);
    g2d.drawImage(title, 250, 250, null);
    g2d.dispose(); // Dispose of the graphic context and release the system resources that is used.    
  }
}
