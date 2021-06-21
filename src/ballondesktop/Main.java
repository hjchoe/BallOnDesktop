package ballondesktop;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class Main
{
	static Structure fr;
	
	public static void main(String[] args) throws AWTException
    {
    	fr = new Structure();
		fr.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent evt)
			{
				Main.getStructure().d = new Dimension(Main.getStructure().getWidth(), Main.getStructure().getHeight());
			}
		});
    }
	
	public static Structure getStructure()
	{
		return fr;
	}
}
