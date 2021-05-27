package ballondesktop;

import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Background extends JPanel
{
	JLabel ball = new JLabel();
	public BufferedImage background;
    private ball b;
	
	public Background()
	{
		this.background = Structure.getBackgroundImage();
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
    	
        MovingAdapter ma = new MovingAdapter();
        addMouseMotionListener(ma);
        addMouseListener(ma);
        
        KeyboardAdapter ka = new KeyboardAdapter();
        addKeyListener(ka);
        
        
        b = new ball(250f, 50f, 10f);
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
        
        g2d.setColor(b.c);
        g2d.fill(b);
    }

    public void gravity()
    {
        if (b.gravity_state)
        {
            float a = 0.2f;
            if (b.moving_state)
            {
                if (b.y < Structure.d.height - 45f)
                {
                    b.velY += a;
                } else
                {
                    b.velY *= -0.80f;
                    b.moving_state = false;
                    b.y = Structure.d.height - 45f;
                }
            } else
            {
                if (b.velY < 0.0f)
                {
                    b.velY += a;
                } else
                {
                    b.moving_state = true;
                }
            }
            b.moveY(b.velY);
        } else
        {
            b.velY = 0f;
        }
    }

    public void thrown()
    {
        if (b.grabbed_state && !b.released_state)
        {
            float orgXavg;
            float orgYavg;
            orgXavg = (b.orgXone + b.orgXtwo)/2;
            orgYavg = (b.orgYone + b.orgYtwo)/2;

            b.velX = (orgXavg - b.x) / -5f;
            b.velY = (orgYavg - b.y) / -5f;
        } else if (!b.grabbed_state && b.released_state)
        {
            if (b.x < 0.0f)
            {
                b.velX *= -0.80f;
                b.x = 0.0f;
            } else if (b.x > Structure.d.width - 20f)
            {
                b.velX *= -0.80f;
                b.x = Structure.d.width - 20f;
            } else if (b.y > Structure.d.height - 45f)
            {
                b.velX *= 0.80f;
                b.velY *= -0.90f;
            } else 
            {
                b.velY += 0;
            }
            b.moveX(b.velX);
            b.moveY(b.velY);
            if (Math.abs(b.velY) < 0.1 && b.y > Structure.d.height)
            {
                b.released_state = false;
            }
        }
        b.orgXone = b.orgXtwo;
        b.orgYone = b.orgYtwo;
        b.orgXtwo = b.x;
        b.orgYtwo = b.y;
    }

    class ball extends Ellipse2D.Float
    {
        private Boolean moving_state = true;
        private Boolean gravity_state = true;
        private Boolean grabbed_state = false;
        private Boolean released_state = false;
        private float orgXone;
        private float orgYone;
        private float orgXtwo;
        private float orgYtwo;
        private float velX = 0;
        private float velY = 0;
        private Color c;
        private Color[] colors = {Color.CYAN, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.BLACK};
        private int colorIndex = 0;

        private ball(float x, float y, float r) {
            setFrame(x - r, y - r, 2.0f * r, 2.0 * r);
            this.c = colors[this.colorIndex];
        }

        private boolean isHit(float x, float y) {
            return getBounds2D().contains(x, y);
        }

        private void moveX(float x) {
            this.x += x;
        }

        private void moveY(float y) {
            this.y += y;
        }
        
        private void reset()
        {
        	this.x = Structure.d.width / 2;
        	this.y = Structure.d.height / 2;
        	this.velX = 0;
        	this.velY = 0;
        }
        
        private void changeColor()
        {
        	if (this.colorIndex == 5) this.colorIndex = -1;
        	this.colorIndex++;
        }
    }

    class MovingAdapter extends MouseAdapter
    {
        private int x;
        private int y;

        @Override
        public void mouseReleased(MouseEvent e)
        {
            b.gravity_state = true;
            if (b.grabbed_state)
            {
                b.grabbed_state = false;
                b.released_state = true;
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
        	if (e.getButton() == MouseEvent.BUTTON1)
        	{
        		x = e.getX();
                y = e.getY();
            }
        	else if (e.getButton() == MouseEvent.BUTTON3)
        	{
	            b.reset();
        	}
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

            if (b.isHit(x, y)) {
                b.gravity_state = false;
                b.grabbed_state = true;
                b.released_state = false;
                b.moveX(dx);
                b.moveY(dy);
            }
            x += dx;
            y += dy;            
        }
    }
    
    class KeyboardAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent ke)
        {
            if (ke.getKeyChar() == KeyEvent.VK_SPACE)
            {
            	b.changeColor();
            }
        }
    }
}
