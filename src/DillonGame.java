
import javax.swing.*;
import java.awt.event.*;

public class DillonGame extends JFrame implements ActionListener
{
  JMenuBar menu;
  JMenu file;
  JMenuItem help, exit;
  boolean change = true;
	
  public DillonGame() // Constructor
  {
	super("2D Java Game"); // Create an initially invisible JFrame. 
	  
	addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e) // Exits program on close.
	  {
	    System.exit(0);
	  }
    });
	
	menu = new JMenuBar();
	file = new JMenu("File");
	help = new JMenuItem("Help");
	exit = new JMenuItem("Exit");
	
	help.setAccelerator(KeyStroke.getKeyStroke( // Hot key for help CTRL + Z
	        KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
	
	exit.setAccelerator(KeyStroke.getKeyStroke( // Hot key for exit CTRL + X
	        KeyEvent.VK_X, ActionEvent.CTRL_MASK));
	
	help.addActionListener(this);
	exit.addActionListener(this);
	
	file.add(help);
	file.add(exit);
	menu.add(file);

	setContentPane(new DillonIntro());
	
	addMouseListener(new MouseAdapter()
	{
	  public void mouseClicked(MouseEvent e)
	  {
	    if (change == true)
	    { 
	      setContentPane(new DillonPane()); // Set DillonPane as the content pane.
	      setFocusable(false); // We want the new DillonPane to be in focus to allow key events.
	      change = false; // We don't want to set the content pane again, so we use a flag.
	      DillonIntro.animation.stop(); // Stop the animation from DillonIntro.
	      setVisible(true); // "Refresh" the JFrame so the new content pane is visible.  
	    } 
	    
	  }
	});
	
	setJMenuBar(menu);
	setSize(600, 600);
	setLocationRelativeTo(null); // GraphicsEnvironment.getCenterPoint; Centers window.
	setResizable(false);
	setVisible(true);
  }
    
  public void actionPerformed(ActionEvent e) 
  {
    if (e.getActionCommand() == "Help")
	  new DillonHelp(); // Create a JDialog object via DillonHelp class.   
    else if (e.getActionCommand() == "Exit")
      System.exit(0); // Exit the game.
  }
  
  public static void main(String[] args) 
  {  
    new DillonGame();	   
  } 
}
