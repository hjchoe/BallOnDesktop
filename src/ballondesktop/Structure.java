package ballondesktop;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Structure extends JFrame
{
	static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	protected static Background background;

	public Structure() throws AWTException
	{
	    initUI();
	}
	
	private void initUI() throws AWTException
	{
	    background = new Background();
		setContentPane(background);

	    setTitle("Ball On Desktop");
        setPreferredSize(new Dimension(d));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
        setDefaultLookAndFeelDecorated(true);
    	setBackground(new Color(0, 0, 0, 1));
        pack();

		setLocationRelativeTo(null);
        setVisible(true);
		setFocusable(false);
	}
}
