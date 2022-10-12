import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Data implements FetchingDataInterface{
	private Path directory;
	private static BufferedReader br;
	protected ArrayList<String[]> fileList;
	protected ArrayList<String> dataPoints;

	public Data(String dir) throws IOException {
		this.fileList = new ArrayList<String[]>();
		this.directory = Paths.get(dir);
		
		Files.walk(this.directory).forEach(path -> {
			try {
				this.ParseInfo (path.toFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	protected ArrayList<String[]> GetFileList(){
		return this.fileList;
	}

	private void ParseInfo (File file) throws IOException {
		if (!file.isDirectory()) {
	        br = new BufferedReader(new FileReader(file));
	        String[] fileInfo = new String[4];
	        fileInfo[3] = file.getPath();
	        while (br.ready())
	        {
	        	String next = br.readLine();

	        	if(next.startsWith("NAME", 0))
	        	{
	        		fileInfo[0] = next.split("^NAME\\s*:\\s*")[1];
	        		
	        	}
	        	else if(next.startsWith("COMMENT") && fileInfo[1] == null)
	        	{
	        		fileInfo[1] = next.split("^COMMENT\\s*:\\s*")[1];
	        	}
	        	else if(next.startsWith("DIMENSION", 0))
	        	{
	        		fileInfo[2] = next.split("^DIMENSION\\s*:\\s*")[1];
	        	}
	        	else
	        		continue;
	        	
	        }
	        this.fileList.add(fileInfo);
		}
	}
	
	private void ParseData(File file) throws IOException {
		if (!file.isDirectory()) {
	        br = new BufferedReader(new FileReader(file));
	        while (br.ready())
	        {
	        	String next = br.readLine();
	        	if (!(next.charAt(0)>='A' && next.charAt(0) <= 'Z'))
	        	{
	        		this.dataPoints.add(next);
	        	}
	        }
		}
	}
	
	public ArrayList<String> GetDataPoints(String dir) throws IOException {
		Path p = Paths.get(dir);
		this.dataPoints = new ArrayList<String>();
		this.ParseData (p.toFile());
		
		return this.dataPoints;
	}
}
