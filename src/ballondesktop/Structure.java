package ballondesktop;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
class Structure extends JFrame
{
	static Dimension d;
	protected static Background background;
	protected static Menu mb;
	protected static Balance bal;

	public Structure()
	{
	    initUI();
	}
	
	private void initUI()
	{
		d = Toolkit.getDefaultToolkit().getScreenSize();
		
	    background = new Background();
		setContentPane(background);
		
		mb = new Menu();
		setJMenuBar(mb);
		mb.setVisible(true);
		
		bal = new Balance();
		add(bal);
		
	    setTitle("Ball On Desktop");
        setPreferredSize(new Dimension(d));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
        setDefaultLookAndFeelDecorated(true);
        
        Setup s = Main.getSetup();
        String[] color = s.getData("backgroundColor").split(",");
    	setBackground(new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]), Integer.parseInt(color[3])));
        
    	pack();

		setLocationRelativeTo(null);
        setVisible(true);
		setFocusable(false);
	}
	
	public void fullScreen()
	{
		d = Toolkit.getDefaultToolkit().getScreenSize();
		dispose();
		setUndecorated(true);
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		setPreferredSize(new Dimension(d));
		setBackground(new Color(0, 0, 0, 1));
        setDefaultLookAndFeelDecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    	background.setOpaque(false);
    	background.repaint();
    	background.setVisible(true);
        
        repaint();
	}
	
	public void regScreen()
	{
		int w = d.width;
		int h = d.height;
		d = new Dimension(w/3, h/3);
		dispose();
		setExtendedState(JFrame.NORMAL);
		setPreferredSize(new Dimension(d));
		setBackground(new Color(0, 0, 0, 255));
		setUndecorated(false);
        setDefaultLookAndFeelDecorated(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    	background.setOpaque(true);
    	Background.getBall().reset();
    	background.repaint();
    	background.setVisible(true);
        
        repaint();
	}
	
	public Background getBackgroundpanel()
	{
		return background;
	}
	
    public static Menu getMenu()
    {
    	return mb;
    }
    
    public static Balance getBalance()
    {
    	return bal;
    }
}
