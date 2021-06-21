package ballondesktop;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class KeySense extends KeyAdapter
{
    @Override
    public void keyPressed(KeyEvent ke)
    {
    	Ball b = Background.getBall();
        if (ke.getKeyChar() == KeyEvent.VK_SPACE) b.changeColor();
        else if (ke.getKeyCode() == KeyEvent.VK_T) Background.changeTrailState();
    }
}