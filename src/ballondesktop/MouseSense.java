package ballondesktop;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MouseSense extends MouseAdapter
{
    private int x;
    private int y;

    @Override
    public void mouseReleased(MouseEvent e)
    {
    	Ball b = Background.getBall();
        b.release();
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
    		Ball b = Background.getBall();
    		b.reset();
    	}
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        doMove(e);
    }
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
    	Menu mb = Structure.getMenu();
    	if (e.getY() < mb.getHeight())
    	{
    		mb.showMenu();
    	}
    	else
    	{
    		mb.hideMenu();
    	}
    }

    private void doMove(MouseEvent e)
    {
        int dx = e.getX() - x;
        int dy = e.getY() - y;
        Ball b = Background.getBall();

        if (b.isHit(x, y))
        {
            b.grab();
            b.moveX(dx);
            b.moveY(dy);
        }
        x += dx;
        y += dy;            
    }
}