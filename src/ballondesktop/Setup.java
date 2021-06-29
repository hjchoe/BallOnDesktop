package ballondesktop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

class Setup
{
	private Path p;
	private File dataFolder;
	private File dataFile;
	
	public Setup()
	{
		p = Path.of(".").toAbsolutePath();
		dataFolder = new File(p + "/DATA");
		dataFile = new File(dataFolder + "/data.txt");
		
		if (createDataFolder())
		{
			createDataFile();
			setupDataFile();
		}
		
		try
		{
			updateData("balance", "100");
		}
		catch (IOException e)
		{
			e.printStackTrace();
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
		    writer.write("balance$@#0\nballColor$@#0,255,255\ntrailColor$@#255,0,0\nbackgroundColor$@#0,0,0,1\nballSize$@#10");
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
	
	public String getData(String section) throws FileNotFoundException
	{
		String result = "error";
		
		Scanner s = new Scanner(dataFile);
	    while (s.hasNextLine())
	    {
	    	String[] data = s.nextLine().replace("\n", "").split("\\$@#");
	    	if (data[0].equals(section))
	    	{
	    		result = data[1];
	    		break;
	    	}
	    }
	    s.close();
		
		return result;
	}
	
	public void updateData(String section, String value) throws IOException
	{
		BufferedReader file = new BufferedReader(new FileReader(dataFile));
		String data = "";
        String line;
        
        while ((line = file.readLine()) != null)
        {
        	String temp = line.replace("\n", "");
        	if (temp.split("\\$@#")[0].equals(section))
        	{
        		line.replace(temp.split("\\$@#")[1], value);
        	}
        	data += (line+"\n");
        }
        file.close();
        
		FileWriter writer = new FileWriter(dataFile, false);
	    writer.write(data);
	    writer.flush();
	    writer.close();
	}
}
