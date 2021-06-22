package ballondesktop;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
class Ball extends Ellipse2D.Float
{
    private Boolean gravityState = true;
    private Boolean grabbedState = false;
    private float orgXone;
    private float orgYone;
    private float orgXtwo;
    private float orgYtwo;
    private float velX = 0;
    private float velY = 0;
    private Color c;
    private Color[] colors = {Color.CYAN, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK, Color.BLACK, Color.GRAY, Color.WHITE};
    private int colorIndex = 0;
    private float[] xValues = new float[20];
    private float[] yValues = new float[20];
    private int coordIndex = 0;

    public Ball(float x, float y, float r)
    {
        setFrame(x - r, y - r, 2.0f * r, 2.0 * r);
        this.c = colors[this.colorIndex];
    }

    public boolean isHit(float x, float y)
    {
        return getBounds2D().contains(x, y);
    }
    
    public void release()
    {
    	this.gravityState = true;
    	this.grabbedState = false;
    }
    
    public void released()
    {
    	if (this.x < 0.0f)
        {
            this.velX *= -0.80f;
            this.x = 0.0f;
        }
    	else if (this.x > Structure.d.width - this.width)
        {
            this.velX *= -0.80f;
            this.x = Structure.d.width - this.width;
        }
        
        if (this.y > Structure.d.height - this.height - 20)
    	{
        	this.velX *= 0.90f;
            this.velY *= -0.80f;
            this.y = Structure.d.height - this.height - 20;
    	}
        else if (this.y < 5)
		{
        	this.velX *= 0.90f;
            this.velY *= -0.80f;
            this.y = 5;
        }
        this.moveX(this.velX);
        this.moveY(this.velY);
    }
    
    public void grab()
    {
    	this.gravityState = false;
        this.grabbedState = true;
    }
    
    public void grabbed()
    {
        float orgXavg = (this.orgXone + this.orgXtwo)/2;
        float orgYavg = (this.orgYone + this.orgYtwo)/2;

        this.velX = (orgXavg - this.x) / -5f;
        this.velY = (orgYavg - this.y) / -5f;
    }

    public void moveX(float x)
    {
        this.x += x;
    }

    public void moveY(float y)
    {
        this.y += y;
    }
    
    public double getY()
    {
    	return this.y;
    }
    
    public void changeY(float v)
    {
    	this.y = v;
    }
    
    public float getVelY()
    {
    	return this.velY;
    }
    
    public void changeVelY(float v)
    {
    	this.velY = v;
    }
    
    public void posUpdate()
    {
    	this.orgXone = this.orgXtwo;
        this.orgYone = this.orgYtwo;
        this.orgXtwo = this.x;
        this.orgYtwo = this.y;
    }
    
    public void reset()
    {
    	this.x = Structure.d.width / 2;
    	this.y = Structure.d.height / 2;
    	this.velX = 0;
    	this.velY = 0;
    }
    
    public void setColor(int r, int g, int b)
    {
    	this.c = new Color(r, g, b);
    }
    
    public void changeColor()
    {
    	if (this.colorIndex == colors.length-1) this.colorIndex = -1;
    	this.colorIndex++;
    	this.c = colors[colorIndex];
    	
    	Background.getTrail().setColor(oppositeColor(this.c));
    }
    
    public static Color oppositeColor(Color old)
    {
    	int red = Math.max(old.getRed(), Math.max(old.getBlue(), old.getGreen())) + Math.min(old.getRed(), Math.min(old.getBlue(), old.getGreen())) - old.getRed(); 
        int green = Math.max(old.getRed(), Math.max(old.getBlue(), old.getGreen())) + Math.min(old.getRed(), Math.min(old.getBlue(), old.getGreen())) - old.getGreen();
        int blue = Math.max(old.getRed(), Math.max(old.getBlue(), old.getGreen())) + Math.min(old.getRed(), Math.min(old.getBlue(), old.getGreen())) - old.getBlue();
    	return new Color(red, green, blue, 25);
    }
    
    public Color getColor()
    {
    	return this.c;
    }
    
    public Boolean getGravityState()
    {
    	return this.gravityState;
    }
    
    public Boolean getGrabbedState()
    {
    	return this.grabbedState;
    }
    
    public void updateCoords()
    {
    	if (this.coordIndex == 19) this.coordIndex = -1;
    	this.coordIndex++;
    	this.xValues[coordIndex] = this.x;
    	this.yValues[coordIndex] = this.y;
    }
    
    public float[] getXvalues()
    {
    	return this.xValues;
    }
    
    public float[] getYvalues()
    {
    	return this.yValues;
    }
}