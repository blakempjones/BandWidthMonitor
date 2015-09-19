

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class TaskRunner {

	public double download = 0;
	public double upload = 0;
	public double downloadRate;
	public double uploadRate;
	public int refreshRate = 1000; // Set to 180,000 for 3 minute refresh rate.
	
	public void parser(String lineVar) // Parse output from netstat and set the data members.
	{
		String[] parsed = lineVar.split("[ ]+");
		double prevDownload = download;
		double prevUpload = upload;
		download = Double.parseDouble(parsed[1]) / Math.pow(10, 9); // sets the download from netstat and converts to gb.
		upload = Double.parseDouble(parsed[2]) / Math.pow(10, 9);
		downloadRate = (download - prevDownload) / (refreshRate / 1000); // sets the rate and converts to seconds.
		uploadRate = (upload - prevUpload) / (refreshRate / 1000);
		
		
	}
	
	public void executeNetStat() // Runs netstat -e from the cmd prompt.
	{
		ProcessBuilder netStatBuilder = new ProcessBuilder("netstat", "-e", "");
		try {
			Process netStatProcess = netStatBuilder.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(netStatProcess.getInputStream()));
			String line;
			String[] lines = new String[100];
			int counter = 0;
			while((line = in.readLine()) != null)
			{
				lines[counter] = line;
				counter++;
			}
			parser(lines[4]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}