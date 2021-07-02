package ballondesktop;

import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Coin extends JLabel
{
	private ImageIcon images[] = new ImageIcon[5];
	private int imageIndex = 0;
	private Path p;
	private Boolean state = true;
	
	Coin(int x, int y)
	{
		p = Path.of(".").toAbsolutePath();
		
		setupImages();
		setIcon(images[0]);
		setLocation(x, y);
		setSize(17, 17);
		setVisible(true);
		
		startAnimation();
	}
	
	private void setupImages()
	{
		for (int i = 0; i < 5; i++)
		{
			images[i] = new ImageIcon(p + "/sprites/coin" + (i+1) + ".png");
		}
	}
	
	private void startAnimation()
	{
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        final Runnable rebuild = new Runnable()
        {
            public void run()
            {
            	setIcon(images[imageIndex]);
                if (imageIndex != 4) imageIndex++;
                else imageIndex = 0;
            }
        };
        executorService.scheduleAtFixedRate(rebuild, 0, 150, TimeUnit.MILLISECONDS);
	}
	
	public Boolean getState()
	{
		return state;
	}
	
	public void changeState(Boolean s)
	{
		this.state = s;
	}
}
