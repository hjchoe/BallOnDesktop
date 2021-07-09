package ballondesktop;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
			//BufferedImage bg;
			//try
			//{
				//bg = ImageIO.read(getClass().getResource("/sprites/coin" + (i+1) + ".png"));
			//}
			///catch (IOException e)
			//{
			//	e.printStackTrace();
			//}
			//images[i] = new ImageIcon(bg);
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
