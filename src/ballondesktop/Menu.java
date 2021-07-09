package ballondesktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
class Menu extends JMenuBar
{
	private JMenu mMenu, mControls, mCustomize;
	private JMenuItem mRespawn, mColor, mTrail, mFullscreen, mRegscreen, mQuit, mBackgroundColor, mBallColor, mBallSize, mTrailColor, mResetDefault;
	
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
		mResetDefault = new JMenuItem("Reset to Default");
		
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
		mCustomize.add(mResetDefault);
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
    			JPanel temp = createTemp();
    			
    			JLabel title = new JLabel("Enter an integer values for r, g, b (0 - 255) and opacity (1 - 255)");
    			title.setBounds(temp.getWidth()/2-175, temp.getHeight()/2-30, 400, 30);
    			JTextField r = new JTextField();
    			r.setBounds(temp.getWidth()/2-105, temp.getHeight()/2, 50, 30);
    			JTextField g = new JTextField();
    			g.setBounds(temp.getWidth()/2-50, temp.getHeight()/2, 50, 30);
    			JTextField b = new JTextField();
    			b.setBounds(temp.getWidth()/2+5, temp.getHeight()/2, 50, 30);
    			JTextField o = new JTextField();
    			o.setBounds(temp.getWidth()/2+60, temp.getHeight()/2, 50, 30);
    			JButton enter = new JButton("Enter");
    			enter.setBounds(temp.getWidth()/2-35, temp.getHeight()/2+35, 70, 20);
    			
    			enter.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String rs = r.getText();
						String gs = g.getText();
						String bs = b.getText();
						String os = o.getText();
						int rn = Integer.parseInt(rs);
						int gn = Integer.parseInt(gs);
						int bn = Integer.parseInt(bs);
						int on = Integer.parseInt(os);
						Structure struct = Main.getStructure();
						Background back = struct.getBackgroundpanel();
						
						if (rn >= 0 && rn <= 255 && gn >= 0 && gn <= 255 && bn >= 0 && bn <= 255 && on >= 1 && on <= 255)
						{
							struct.setBackground(new Color(rn, gn, bn, on-1));
							Setup s = Main.getSetup();
							StringBuilder color = new StringBuilder();  
							color.append(Integer.toString(rn) + ",");
							color.append(Integer.toString(gn) + ",");
							color.append(Integer.toString(bn) + ",");
							color.append(Integer.toString(on-1));
							s.updateData("backgroundColor", color.toString());
						}
						struct.remove(temp);
						back.setFocusable(true);
						back.requestFocus();
					}
				});
    			
    			temp.add(title);
    			temp.add(r);
    			temp.add(g);
    			temp.add(b);
    			temp.add(o);
    			temp.add(enter);
    			Main.getStructure().add(temp);
    		}
		});
    	
    	this.mBallColor.addActionListener(new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{
    			JPanel temp = createTemp();
    			
    			JLabel title = new JLabel("Enter an integer values for r, g, b (0 - 255)");
    			title.setBounds(temp.getWidth()/2-120, temp.getHeight()/2-30, 320, 30);
    			JTextField r = new JTextField();
    			r.setBounds(temp.getWidth()/2-80, temp.getHeight()/2, 50, 30);
    			JTextField g = new JTextField();
    			g.setBounds(temp.getWidth()/2-25, temp.getHeight()/2, 50, 30);
    			JTextField b = new JTextField();
    			b.setBounds(temp.getWidth()/2+30, temp.getHeight()/2, 50, 30);
    			JButton enter = new JButton("Enter");
    			enter.setBounds(temp.getWidth()/2-35, temp.getHeight()/2+35, 70, 20);
    			
    			enter.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String rs = r.getText();
						String gs = g.getText();
						String bs = b.getText();
						int rn = Integer.parseInt(rs);
						int gn = Integer.parseInt(gs);
						int bn = Integer.parseInt(bs);
						if (rn >= 0 && rn <= 255 && gn >= 0 && gn <= 255 && bn >= 0 && bn <= 255)
						{
							Background.getBall().setColor(rn, gn, bn);
							Setup s = Main.getSetup();
							StringBuilder color = new StringBuilder();  
							color.append(Integer.toString(rn) + ",");
							color.append(Integer.toString(gn) + ",");
							color.append(Integer.toString(bn));
							s.updateData("ballColor", color.toString());
						}
						Structure struct = Main.getStructure();
						struct.remove(temp);
						Background back = struct.getBackgroundpanel();
						back.setFocusable(true);
						back.requestFocus();
					}
				});
    			
    			temp.add(title);
    			temp.add(r);
    			temp.add(g);
    			temp.add(b);
    			temp.add(enter);
    			Main.getStructure().add(temp);
    		}
		});
    	
    	this.mBallSize.addActionListener(new ActionListener()
		{
    		
    		public void actionPerformed(ActionEvent e)
    		{
    			JPanel temp = createTemp();
    			
    			JLabel title = new JLabel("Enter an integer value for the radius (between 10 - 100)");
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
							Background.getBall().width = rad*2;
							Background.getBall().height = rad*2;
							Background.getTrail().reSize(rad);
							Setup set = Main.getSetup();
							set.updateData("ballSize", Integer.toString(rad));
						}
						Structure struct = Main.getStructure();
						struct.remove(temp);
						Background back = struct.getBackgroundpanel();
						back.setFocusable(true);
						back.requestFocus();
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
    			JPanel temp = createTemp();
    			
    			JLabel title = new JLabel("Enter an integer values for r, g, b (0 - 255)");
    			title.setBounds(temp.getWidth()/2-120, temp.getHeight()/2-30, 320, 30);
    			JTextField r = new JTextField();
    			r.setBounds(temp.getWidth()/2-80, temp.getHeight()/2, 50, 30);
    			JTextField g = new JTextField();
    			g.setBounds(temp.getWidth()/2-25, temp.getHeight()/2, 50, 30);
    			JTextField b = new JTextField();
    			b.setBounds(temp.getWidth()/2+30, temp.getHeight()/2, 50, 30);
    			JButton enter = new JButton("Enter");
    			enter.setBounds(temp.getWidth()/2-35, temp.getHeight()/2+35, 70, 20);
    			
    			enter.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String rs = r.getText();
						String gs = g.getText();
						String bs = b.getText();
						int rn = Integer.parseInt(rs);
						int gn = Integer.parseInt(gs);
						int bn = Integer.parseInt(bs);
						if (rn >= 0 && rn <= 255 && gn >= 0 && gn <= 255 && bn >= 0 && bn <= 255)
						{
							Background.getTrail().setColor(new Color(rn, gn, bn, 25));
							Setup s = Main.getSetup();
							StringBuilder color = new StringBuilder();  
							color.append(Integer.toString(rn) + ",");
							color.append(Integer.toString(gn) + ",");
							color.append(Integer.toString(bn));
							s.updateData("trailColor", color.toString());
						}
						Structure struct = Main.getStructure();
						struct.remove(temp);
						Background back = struct.getBackgroundpanel();
						back.setFocusable(true);
						back.requestFocus();
					}
				});
    			
    			temp.add(title);
    			temp.add(r);
    			temp.add(g);
    			temp.add(b);
    			temp.add(enter);
    			Main.getStructure().add(temp);
    		}
		});
    	
    	this.mResetDefault.addActionListener(new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{
    			Setup s = Main.getSetup();
    			s.setupDataFile();
    			
    			Structure struct = Main.getStructure();
				struct.setBackground(new Color(0, 0, 0, 1));
				
				Background.getBall().setColor(0, 255, 255);
				
				Background.getBall().width = 10*2;
				Background.getBall().height = 10*2;
				Background.getTrail().reSize(10);
    			
				Background.getTrail().setColor(new Color(255, 0, 0, 25));

    		}
		});
    }
    
    private JPanel createTemp()
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
		temp.setLayout(null);
		return temp;
    }
}
