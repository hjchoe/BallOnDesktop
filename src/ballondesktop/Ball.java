package ballondesktop;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
class Ball extends Ellipse2D.Float
{
    private Boolean gravity_state = true;
    private Boolean grabbed_state = false;
    private Boolean released_state = true;
    private float orgXone;
    private float orgYone;
    private float orgXtwo;
    private float orgYtwo;
    private float velX = 0;
    private float velY = 0;
    private Color c;
    private Color[] colors = {Color.CYAN, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.BLACK};
    private int colorIndex = 0;

    public Ball(float x, float y, float r)
    {
        setFrame(x - r, y - r, 2.0f * r, 2.0 * r);
        this.c = colors[this.colorIndex];
    }

    public boolean isHit(float x, float y)
    {
        return getBounds2D().contains(x, y);
    }
    
    public void Hit()
    {
    	this.gravity_state = false;
        this.grabbed_state = true;
        this.released_state = false;
    }
    
    public void Released()
    {
    	this.gravity_state = true;
        if (this.grabbed_state)
        {
            this.grabbed_state = false;
            this.released_state = true;
        }
    }
    
    public void Release()
    {
    	System.out.println(this.velY);
    	if (this.x < 0.0f)
        {
            this.velX *= -0.80f;
            this.x = 0.0f;
        } else if (this.x > Structure.d.width - 20f)
        {
            this.velX *= -0.80f;
            this.x = Structure.d.width - 20f;
        }
        
        if (this.y > Structure.d.height - 45f || this.y < 5)
        {
        	this.velX *= 0.90f;
            this.velY *= -0.80f;
        }
        this.moveX(this.velX);
        this.moveY(this.velY);
    }
    
    public void Grab()
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
    
    public void PosUpdate()
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
    
    public void changeColor()
    {
    	if (this.colorIndex == 5) this.colorIndex = -1;
    	this.colorIndex++;
    	this.c = colors[colorIndex];
    }
    
    public Color getColor()
    {
    	return this.c;
    }
    
    public Boolean getGravity_state()
    {
    	return this.gravity_state;
    }
    
    public Boolean getReleased_state()
    {
    	return this.released_state;
    }
    
    public Boolean getGrabbed_state()
    {
    	return this.grabbed_state;
    }
}