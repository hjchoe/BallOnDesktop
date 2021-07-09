package ballondesktop;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
class Trail
{
	float size;
	public Circle[] circles = new Circle[20];
	public Color c;
	
	public Trail()
    {
		Setup s = Main.getSetup();
		size = Float.parseFloat(s.getData("ballSize"))/2f;
		
		for (int i = 0; i < 20; i++)
		{
			circles[i] = new Circle(size);
			size += 0.25;
		}
        String[] color = s.getData("trailColor").split(",");
        this.c = new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]), 25);
    }
	
	public class Circle extends Ellipse2D.Float
	{
		public Circle(float r)
		{
			setFrame(x - r, y - r, 2.0f * r, 2.0 * r);
		}
		
		public void updateLocation(float x, float y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	public void reSize(float s)
	{
		float newsize = s/2;
		for (int i = 0; i < 20; i++)
		{
			circles[i].width = newsize*2;
			circles[i].height = newsize*2;
			newsize += 0.25;
		}
	}
	
	public void setColor(Color c)
	{
		this.c = c;
	}
	
	public Color getColor()
	{
		return c;
	}
}
