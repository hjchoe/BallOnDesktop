package ballondesktop;

import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
class Trail
{
	public Circle[] circles = new Circle[20];
	
	public Trail()
    {
		float size = 5;
		for (int i = 0; i < 20; i++)
		{
			circles[i] = new Circle(size);
			size += 0.25;
		}
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
}
