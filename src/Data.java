import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Data {
	private Path directory;
	private static BufferedReader br;
	private ArrayList<String[]> fileList;
	private ArrayList<Double[]> dataPoints;

	public Data(String dir) throws IOException
	{
		fileList = new ArrayList<String[]>();
		this.directory = Paths.get(dir);
		
		Files.walk(this.directory).forEach(path -> {
			try {
				ParseInfo (path.toFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}
	public ArrayList<String[]> GetFileList()
	{
		return this.fileList;
	}

	public void ParseInfo (File file) throws IOException {
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
	
	public void ParseData(File file) throws IOException
	{
		if (!file.isDirectory()) {
	        br = new BufferedReader(new FileReader(file));
	        while (br.ready())
	        {
	        	String next = br.readLine();
	        	if (next.matches("[0-9]*"))
	        	{
	        		Double[] d = new Double[2];
	        		d[0] = Double.parseDouble(next.split("\\s+")[1]);
	        		d[1] = Double.parseDouble(next.split("\\s+")[2]);
	        		dataPoints.add(d);
	        		System.out.println(d);
	        	}
	        }
		}
	}
	
	public void GetDataPoints(String dir)
	{
		Path p = Path.get(dir)
	}
}
