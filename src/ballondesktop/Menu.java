package ballondesktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar
{
	private JMenu mControls, mRespawn, mQuit;
	private JMenuItem mTest;
	
	Menu()
	{
		initUI();
	}
	
	private void initUI()
	{
		mControls = new JMenu("Controls");
		mRespawn = new JMenu("Respawn");
		mQuit = new JMenu("Quit");
		
		this.add(mControls);
		this.add(mRespawn);
		this.add(mQuit);
		
		setupActionMenu();
	}
	
    private void setupActionMenu()
    {
    	this.mControls.addActionListener(new ActionListener()
    	{  
			public void actionPerformed(ActionEvent e)
			{  
				System.out.println("yuh");
			}  
		});
    	
    	this.mRespawn.addActionListener(new ActionListener()
    	{  
			public void actionPerformed(ActionEvent e)
			{  
				Ball b = Background.getBall();
	    		b.reset();
			}  
		});
    	
    	this.mQuit.addActionListener(new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{
    			System.exit(0);
    		}
		});
    }
	
    public void showMenu()
    {
    	this.setVisible(true);
    }
    
    public void hideMenu()
    {
    	this.setVisible(false);
    }
    
}
