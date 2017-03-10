
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DillonPane extends JPanel implements Runnable
{
  static Image ground, mario_still_right, mario_jump_right, mario_walk_right, mario_still_left,
  mario_walk_left, mario_jump_left;
  
  static Thread animate;
  
  final static Color SKY_BLUE = new Color(51, 204, 255);
  
  private static int x = 300;
  private static int y = 488;
  
  final static private int GROUND_WIDTH = 98;
  final static private int GROUND_HEIGHT = 32;
  final static private int GROUND = 488; // Y-value when Mario is on the ground.
  
  static boolean right = true; // True means the last key released was the right arrow key, false = left.
  
  static boolean[] key = new boolean[525]; // Left, up, and right arrow key flags,
  										   // true = pressed, false = released.
  public DillonPane() // Constructor
  {
	ImageIcon ground_icon = new ImageIcon("resources/ground.gif");
	ground = ground_icon.getImage();
	
	ImageIcon mario_still_righti = new ImageIcon("resources/characters/mario.png");
	mario_still_right = mario_still_righti.getImage();
	
	ImageIcon mario_jump_righti = new ImageIcon("resources/characters/mariojump.png");
	mario_jump_right = mario_jump_righti.getImage();
	
	ImageIcon mario_walk_righti = new ImageIcon("resources/characters/mariowalk.png");
	mario_walk_right = mario_walk_righti.getImage();
	
	ImageIcon mario_still_lefti = new ImageIcon("resources/characters/marioleft.png");
	mario_still_left = mario_still_lefti.getImage();
	
	ImageIcon mario_walk_lefti = new ImageIcon("resources/characters/mariowalkleft.png");
	mario_walk_left = mario_walk_lefti.getImage();
	
	ImageIcon mario_jump_lefti = new ImageIcon("resources/characters/mariojumpleft.png");
	mario_jump_left = mario_jump_lefti.getImage();
	
	animate = new Thread(this); // Thread constructor.
	animate.start(); // Start the animation thread.
		
	addKeyListener(new KeyAdapter()
	{
	  public void keyPressed(KeyEvent e)
      { 		    
	    switch (e.getKeyCode())
		{
		  case 37: // Left arrow key.
			  System.out.println("hey");
		    if (x > 0)  
			  x -= 1; 
		  break;
		  
		  case 39: // Right arrow key.
		    if (x < 571)
			  x += 1; 
		  break;
		}	                //a = 65 s = 83
	    
		key[e.getKeyCode()] = true;		
	    repaint(); // Re-draw the images.
	    
      }	
			
	  public void keyReleased(KeyEvent e)
	  {
	    key[e.getKeyCode()] = false;
	    
	    if (e.getKeyCode() == 39)
	      right = true;
	    else if (e.getKeyCode() == 37)
	      right = false;
	  } 
    });
	  
	setBackground(SKY_BLUE);
	setDoubleBuffered(true);
	setFocusable(true); // JPanel needs to be in focus for KeyListener methods to work.
	setVisible(true);
  }
 
  public void jump() // Called when Mario is supposed to jump.
  {  
	while (y > GROUND-50)
    {
  	  try
  	  {
  		y--;  
  		
  		if (key[39] == true && key[37] == false)
	      x++;
  		else if (key[39] == false && key[37] == true)
  		  x--;
  		 		
        repaint();
        Thread.sleep(15);
      }
  	
  	  
      catch (InterruptedException jump)
      {
        System.out.println("Jump Interrupted! " + jump);   	
      }
    }
	
  }
  
  public void gravity() // When Mario jumps, this is called to bring him back to the ground.
  {
	while (y < GROUND)  
	{  
      try
      {
    	y++; 
    	
    	if (key[39] == true && key[37] == false)
  	      x++;
    	else if (key[39] == false && key[37] == true)
    	  x--;   	

    	repaint();
	    Thread.sleep(15); // Pause after each call to repaint.
      }
	 
      catch (InterruptedException e)
      {
        System.out.println("Animation Interrupted!");  
      }
	}  
  } 
  
  public void run() // Overrides run method of Runnable interface.
  {
    while (true)
    {	   
      if (key[38] == true && y == GROUND)
    	jump();
      else if (y == GROUND-50)
        gravity();	 
    }    
  }
  	   
  public void paint(Graphics g) 
  {
	super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
  
    if (y == GROUND) // Outer if statement that has to do with drawing Mario on the ground.
    {
      if (key[39] == true && key[37] == false) // Right key pressed, left key released.
      {
        if (x % 3 == 0)
          g2d.drawImage(mario_walk_right, x, y, null);
        else
          g2d.drawImage(mario_still_right, x, y, null);
      }
        
      else if (key[39] == false && key[37] == true) // Right key released, left key pressed.
      {
        if (x % 3 == 0)
          g2d.drawImage(mario_walk_left, x, y, null);
        else
          g2d.drawImage(mario_still_left, x, y, null);
      }
      
      else
      {
        if (right == true) // If the user releases an arrow key, draw still Mario. 
          g2d.drawImage(mario_still_right, x, y, null);
        else
          g2d.drawImage(mario_still_left, x, y, null);
      }
        
      repaint(); // This is called to return Mario to his still standing animation.
    }
          
    else // If Mario is in the air, repaint a jump image.
    {
      if (key[39] == true && key[37] == false)
        g2d.drawImage(mario_jump_right, x, y, null);
      else if (key[39] == false && key[37] == true)
        g2d.drawImage(mario_jump_left, x, y, null);
      else
      {	
        if (right == true)
          g2d.drawImage(mario_jump_right, x, y, null); // Mario jumping.
        else
          g2d.drawImage(mario_jump_left, x, y, null);
      }
    }
  	
    for (int i = 0; i <= 600; i += GROUND_WIDTH)
    {
      g2d.drawImage(ground, i, 515, GROUND_WIDTH, GROUND_HEIGHT, null);	
    }
    
    Toolkit.getDefaultToolkit().sync(); // Ensures animation is up-to-date.
    g2d.dispose(); // Dispose of the graphic context and release the system resources that is used.
  }
  	
}
