package ballondesktop;

import java.util.concurrent.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Background extends JPanel
{
	JLabel ball = new JLabel();
	private static Ball b;
	private static float a = 0.3f;

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
	}

    private void initUI()
    {
    	setOpaque(false);
    	setBackground(new Color(0, 0, 0, 255));
    	setFocusable(true);
    	
        MouseSense ma = new MouseSense();
        addMouseMotionListener(ma);
        addMouseListener(ma);
        
        KeySense ka = new KeySense();
        addKeyListener(ka);
        
        b = new Ball(Structure.d.width/2, Structure.d.height/2, 10f);
    }
    
    @Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(b.getColor());
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
    }
    
    public static Ball getBall()
    {
    	return b;
    }
}
