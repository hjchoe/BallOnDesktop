package ballondesktop;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
class Structure extends JFrame
{
	static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	protected static Background background;
	protected static Menu mb;

	public Structure() throws AWTException
	{
	    initUI();
	}
	
	private void initUI() throws AWTException
	{
	    background = new Background();
		setContentPane(background);
		
		mb = new Menu();
		setJMenuBar(mb);

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
	
    public static Menu getMenu()
    {
    	return mb;
    }
}
