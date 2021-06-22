package ballondesktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Menu extends JMenuBar
{
	private JMenu mMenu, mControls, mCustomize;
	private JMenuItem mRespawn, mColor, mTrail, mFullscreen, mRegscreen, mQuit, mBackgroundColor, mBallColor, mBallSize, mTrailColor;
	
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
		mColor = new JMenuItem("Change Ball Color | SPACEBAR");
		mTrail = new JMenuItem("Toggle Trail | T key");
		
		mCustomize = new JMenu("Customization");
		mBackgroundColor = new JMenuItem("Set Background Color");
		mBallColor = new JMenuItem("Set Ball Color");
		mBallSize = new JMenuItem("Set Ball Size");
		mTrailColor = new JMenuItem("Set Trail Color");
		
		mMenu.add(mFullscreen);
		mMenu.add(mRegscreen);
		mMenu.add(mQuit);
		this.add(mMenu);
		
		mControls.add(mRespawn);
		mControls.add(mColor);
		mControls.add(mTrail);
		this.add(mControls);
		
		mCustomize.add(mBackgroundColor);
		mCustomize.add(mBallColor);
		mCustomize.add(mBallSize);
		mCustomize.add(mTrailColor);
		this.add(mCustomize);
		
		setupActionMenu();
	}
	
    private void setupActionMenu()
    {
    	// mControls
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
    	
    	// mMenu
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
    	
    	// mCustomize
    	this.mBackgroundColor.addActionListener(new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{

    		}
		});
    	
    	this.mBallColor.addActionListener(new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{

    		}
		});
    	
    	this.mBallSize.addActionListener(new ActionListener()
		{
    		
    		public void actionPerformed(ActionEvent e)
    		{
    			int w = Main.getStructure().getWidth();
    			int h = Main.getStructure().getHeight();
    			
    			JPanel temp = new JPanel();
    			temp.setOpaque(true);
    			temp.setBackground(new Color(255, 255, 255));
    			temp.setSize(new Dimension(w/3, h/3));
    			temp.setFocusable(true);
    			temp.requestFocus();
    			temp.setVisible(true);
    			temp.setLocation(w/3, h/3);
    			
    			JLabel title = new JLabel("Enter an integer value for the radius (between 10-100)");
    			title.setBounds(temp.getWidth()/2-160, temp.getHeight()/2-30, 320, 30);
    			JTextField size = new JTextField();
    			size.setBounds(temp.getWidth()/2-50, temp.getHeight()/2, 100, 30);
    			JButton enter = new JButton("Enter");
    			enter.setBounds(temp.getWidth()/2-35, temp.getHeight()/2+35, 70, 20);
    			
    			enter.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String s = size.getText();
						int rad = Integer.parseInt(s);
						if (rad >= 10 && rad <= 100)
						{
							Background.getBall().width = rad/2;
							Background.getBall().height = rad/2;
						}
						Main.getStructure().remove(temp);
					}
				});
    			
    			temp.add(title);
    			temp.add(size);
    			temp.add(enter);
    			Main.getStructure().add(temp);
    		}
		});
    	
    	this.mTrailColor.addActionListener(new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{

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
