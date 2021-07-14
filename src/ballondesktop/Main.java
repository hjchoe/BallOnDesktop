package ballondesktop;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class Main
{
	static Structure fr;
	private static Setup s;
	
	public static void main(String[] args)
    {
		s = new Setup();
		
    	fr = new Structure();
		fr.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent evt)
			{
				Structure.d = new Dimension(Main.getStructure().getWidth(), Main.getStructure().getHeight());
			}
		});
    }
	
	public static Structure getStructure()
	{
		return fr;
	}
	
	public static Setup getSetup()
	{
		return s;
	}
}