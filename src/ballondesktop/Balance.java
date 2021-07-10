package ballondesktop;

import java.awt.Color;
import java.awt.Dimension;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Balance extends JPanel
{
	private ImageIcon images[] = new ImageIcon[5];
	private int imageIndex = 0;
	private Path p;
	private JLabel coinIcon;
	private JLabel bal;
	
	Balance()
	{	
		p = Path.of(".").toAbsolutePath();
	
		setOpaque(true);
		setBackground(new Color(255, 255, 255));
		setSize(new Dimension(300, 50));
		setFocusable(false);
		setVisible(true);
		setLocation(Structure.d.width-300, 0);
		//setLayout(null);
		
		createCoinIcon();
		add(coinIcon);
		
		createBalanceIcon();
		add(bal);
	}
	
	private void createBalanceIcon()
	{
		Setup s = Main.getSetup();
		String act_bal = s.getData("balance");
		bal = new JLabel(act_bal);
		bal.setVisible(true);
	}
	
	public void updateBalance(int newbal)
	{
		bal.setText(Integer.toString(newbal));
	}
	
	private void createCoinIcon()
	{
		coinIcon = new JLabel();
		setupImages();
		
		coinIcon.setIcon(images[0]);
		coinIcon.setSize(17, 17);
		coinIcon.setVisible(true);
		
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
            	coinIcon.setIcon(images[imageIndex]);
                if (imageIndex != 4) imageIndex++;
                else imageIndex = 0;
            }
        };
        executorService.scheduleAtFixedRate(rebuild, 0, 150, TimeUnit.MILLISECONDS);
	}
}
