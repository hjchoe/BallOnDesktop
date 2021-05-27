package ballondesktop;

import java.util.concurrent.*;
import java.awt.*;
import java.awt.event.*;
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
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	doDrawing(g);
	}

    private void initUI()
    {
    	setOpaque(false);
    	setBackground(new Color(0, 0, 0, 255));
    	setFocusable(true);
    	
        MovingAdapter ma = new MovingAdapter();
        addMouseMotionListener(ma);
        addMouseListener(ma);
        
        TypingAdapter ka = new TypingAdapter();
        addKeyListener(ka);
        
        b = new Ball(250f, 50f, 10f);
        
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        final Runnable rebuild = new Runnable()
        {
            public void run()
            {
                gravity();
                thrown();
                repaint();
            }
        };
        executorService.scheduleAtFixedRate(rebuild, 0, 25, TimeUnit.MILLISECONDS);
    }

    private void doDrawing(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(b.getColor());
        g2d.fill(b);
    }

    public static void gravity()
    {
    	Boolean gravity_state = b.getGravity_state();
    	float velY = b.getVelY();
    	
        if (gravity_state) b.changeVelY(velY + a);
        else b.changeVelY(0f);
    }

    public void thrown()
    {
    	Boolean grabbed_state = b.getGrabbed_state();
    	Boolean released_state = b.getReleased_state();
    	
        if (grabbed_state && !released_state) b.Grab();
        else if (!grabbed_state && released_state) b.Release();
        b.PosUpdate();
    }

    class MovingAdapter extends MouseAdapter
    {
        private int x;
        private int y;

        @Override
        public void mouseReleased(MouseEvent e)
        {
            b.Released();
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
        	if (e.getButton() == MouseEvent.BUTTON1)
        	{
        		x = e.getX();
                y = e.getY();
            }
        	else if (e.getButton() == MouseEvent.BUTTON3) b.reset();
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            doMove(e);
        }

        private void doMove(MouseEvent e)
        {
            int dx = e.getX() - x;
            int dy = e.getY() - y;

            if (b.isHit(x, y))
            {
                b.Hit();
                b.moveX(dx);
                b.moveY(dy);
            }
            x += dx;
            y += dy;            
        }
    }
    
    class TypingAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent ke)
        {
            if (ke.getKeyChar() == KeyEvent.VK_SPACE) b.changeColor();
        }
    }
}
