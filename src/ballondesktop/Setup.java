package ballondesktop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

class Setup
{
	private Path p;
	private File dataFolder;
	private File dataFile;
	
	public Setup()
	{
		//p = Path.of(".").toAbsolutePath();
		//dataFolder = new File(p + "/DATA");
		//dataFile = new File(dataFolder + "/data.txt");
		
		//if (createDataFolder())
		{
			//createDataFile();
			//setupDataFile();
		}
		//else
		{
			
		}
	}
	
	private Boolean createDataFolder()
	{
		Boolean state = false;
		
		if (!dataFolder.exists())
		{
			state = dataFolder.mkdir();
		}
		return state;
	}
	
	private void createDataFile()
	{
		try
		{
		    if (dataFile.createNewFile())
		    {
		    	System.out.println("File created: " + dataFile.getName());
		    }
		}
		catch (IOException e)
		{
			System.out.println("An error occurred.");
		    e.printStackTrace();
		    System.exit(0);
		}
	}
	
	private void setupDataFile()
	{
		try
		{
			FileWriter writer = new FileWriter(dataFile, false);
		    writer.write("balance$@#0\nballColor$@#0,255,255\ntrailColor$@#255,0,0\nbackgroundColor$@#0,0,0,1\nballSize$@#");
		    writer.flush();
		    writer.close();
		}
		catch (IOException e)
		{
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		    System.exit(0);
		}
	}
}
