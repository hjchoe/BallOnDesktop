package ballondesktop;

import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Coin extends JLabel
{
	private ImageIcon images[] = new ImageIcon[5];
	private int imageIndex = 0;
	private Path p;
	
	Coin()
	{
		p = Path.of(".").toAbsolutePath();
		
		setupImages();
		//setText("test");
		setIcon(images[0]);
		setLocation(100, 100);
		setSize(100, 100);
		setVisible(true);
		
		//startAnimation();
	}
	
	private void setupImages()
	{
		for (int i = 0; i < 5; i++)
		{
			images[i] = new ImageIcon(p + "/sprites/coin" + (i+1));
			System.out.println(p + "/sprites/coin" + (i+1));
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
                imageIndex++;
            }
        };
        executorService.scheduleAtFixedRate(rebuild, 0, 100, TimeUnit.MILLISECONDS);
	}
}
