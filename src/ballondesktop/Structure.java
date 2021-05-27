package ballondesktop;

import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Structure extends JFrame
{
	static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	static Robot robot;
	JLabel label;
	protected Background background;
	static Structure fr;

	public Structure() throws AWTException
	{
	    initUI();
	}
	
	private void initUI() throws AWTException
	{
		robot = new Robot();
		
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
	
    public static BufferedImage getBackgroundImage()
    {
    	try
    	{
    		BufferedImage background = robot.createScreenCapture(new Rectangle(0, 0, d.width, d.height));
    		return background;
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return null;
	}
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
				try
				{
					fr = new Structure();
					fr.setVisible(true);
				} catch (AWTException e)
				{
					e.printStackTrace();
				}
            }
        });
    }
}
