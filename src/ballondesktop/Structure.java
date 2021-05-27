package ballondesktop;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Structure extends JFrame
{
	static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	JLabel label;
	protected static Background background;
	static Structure fr;

	public Structure() throws AWTException
	{
	    initUI();
	}
	
	private void initUI() throws AWTException
	{
	    background = new Background();
	    add(background);
	
	    setTitle("Gravity Simulation");
        setPreferredSize(new Dimension(d));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setDefaultLookAndFeelDecorated(true);
    	setBackground(new Color(0, 0, 0, 0));
        pack();
        setVisible(true);
	}
    
    public static void main(String[] args) throws AWTException
    {
    	fr = new Structure();
		fr.setVisible(true);
    }
}
