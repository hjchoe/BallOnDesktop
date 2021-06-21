package ballondesktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar
{
	private JMenu mMenu, mControls;
	private JMenuItem mRespawn, mQuit;
	
	Menu()
	{
		initUI();
	}
	
	private void initUI()
	{
		mMenu = new JMenu("Menu");
		mQuit = new JMenuItem("Quit");
		
		mControls = new JMenu("Controls");
		mRespawn = new JMenuItem("Respawn | RMB");
		
		mMenu.add(mQuit);
		this.add(mMenu);
		
		mControls.add(mRespawn);
		this.add(mControls);
		
		setupActionMenu();
	}
	
    private void setupActionMenu()
    {    	
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
