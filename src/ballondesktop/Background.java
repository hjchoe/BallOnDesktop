package ballondesktop;

import java.util.Random;
import java.util.concurrent.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
class Background extends JPanel
{
	private static Ball b;
	private static Trail t;
	private static Coin c;
	private static float a = 0.3f;
	private static Boolean trailState = false;

	public Background()
	{
		initUI();
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        final Runnable rebuild = new Runnable()
        {
            public void run()
            {
                check();
                repaint();
            }
        };
        executorService.scheduleAtFixedRate(rebuild, 0, 25, TimeUnit.MILLISECONDS);
        
        final Runnable trail = new Runnable()
        {
            public void run()
            {
                if (trailState) trailUpdate();
            }
        };
        executorService.scheduleAtFixedRate(trail, 0, 5, TimeUnit.MILLISECONDS);
        
        final Runnable spawnCoin = new Runnable()
        {
            public void run()
            {
            	Random rand = new Random();
            	int n = rand.nextInt(10);
            	if (!c.getState() && n == 10)
            	{
            		Dimension d = Main.getStructure().d;
	        		c = new Coin(rand.nextInt(d.width), rand.nextInt(d.height));
	                add(c);
	                c.changeState(true);
            	}
            }
        };
        executorService.scheduleAtFixedRate(spawnCoin, 0, 5, TimeUnit.SECONDS);
	}

    private void initUI()
    {
    	setOpaque(false);
    	setBackground(new Color(0, 0, 0, 255));
    	setFocusable(true);
        requestFocus();
    	
        MouseSense ma = new MouseSense();
        addMouseMotionListener(ma);
        addMouseListener(ma);
        
        KeySense ka = new KeySense();
        addKeyListener(ka);
        
        b = new Ball(Structure.d.width/2, Structure.d.height/2, 10f);
        t = new Trail();
    }
    
    @Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color bc = b.getColor();
        if (trailState)
        {
	        Color tc = t.getColor();
	        
	        g2d.setColor(tc);
	        
	        for (Trail.Circle circles : t.circles)
	        {
	        	g2d.fill(circles);
	        }
        }
        g2d.setColor(bc);
        g2d.fill(b);
	}
    
    public void check()
    {
    	Boolean gravityState = b.getGravityState();
    	float velY = b.getVelY();
    	if (gravityState) b.changeVelY(velY + a);
        else b.changeVelY(0f);
    	
    	Boolean grabbedState = b.getGrabbedState();
        if (grabbedState) b.grabbed();
        else b.released();
        b.posUpdate();
        b.updateCoords();
        
        float dx = b.x - c.getX();
        float dy = b.y - c.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (!grabbedState && distance < b.width/2 + 17/2)
        {
        	remove(c);
        	c.changeState(false);
        }
    }
    
    public static void changeTrailState()
    {
    	trailState = !trailState;
    }
    
    public void trailUpdate()
    {
    	for (int i = 0; i < 20; i++)
    	{
    		t.circles[i].updateLocation(b.getXvalues()[i], b.getYvalues()[i]);;
    	}
    }
    
    public static Ball getBall()
    {
    	return b;
    }
    
    public static Trail getTrail()
    {
    	return t;
    }
}
