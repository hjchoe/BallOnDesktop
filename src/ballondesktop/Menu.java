package ballondesktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Menu extends JMenuBar
{
	private JMenu mMenu, mControls;
	private JMenuItem mRespawn, mColor, mTrail, mFullscreen, mRegscreen, mQuit;
	
	Menu()
	{
		initUI();
	}
	
	private void initUI()
	{
		mMenu = new JMenu("Menu");
		mFullscreen = new JMenuItem("Fullscreen");
		mRegscreen = new JMenuItem("Minimize");
		mQuit = new JMenuItem("Quit");
		
		mControls = new JMenu("Controls");
		mRespawn = new JMenuItem("Respawn | RMB");
		mColor = new JMenuItem("Change Color | SPACEBAR");
		mTrail = new JMenuItem("Toggle Trail | T key");
		
		mMenu.add(mFullscreen);
		mMenu.add(mRegscreen);
		mMenu.add(mQuit);
		this.add(mMenu);
		
		mControls.add(mRespawn);
		mControls.add(mColor);
		mControls.add(mTrail);
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
    	
    	this.mColor.addActionListener(new ActionListener()
    	{  
			public void actionPerformed(ActionEvent e)
			{  
				Ball b = Background.getBall();
	    		b.changeColor();
			}  
		});
    	
    	this.mTrail.addActionListener(new ActionListener()
    	{  
			public void actionPerformed(ActionEvent e)
			{  
				Background.changeTrailState();
			}  
		});
    	
    	this.mFullscreen.addActionListener(new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{
    			Structure s = Main.getStructure();
    			s.fullScreen();
    		}
		});
    	
    	this.mRegscreen.addActionListener(new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{
    			Structure s = Main.getStructure();
    			s.regScreen();
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
    	//this.setVisible(true);
    }
    
    public void hideMenu()
    {
    	//this.setVisible(false);
    }
    
}
