import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * The Data class implements FetchingDataInterface and fetches data file containing
 * distance between different cities and returns the parsed data. It also provides
 * the user to choose from the data file they want to use.
 */
public class Data implements FetchingDataInterface{
	private Path directory;
	private static BufferedReader br;
	protected ArrayList<String[]> fileList;
	protected ArrayList<String> dataPoints;


	/***
	 * Constructor for class Data.
	 * 
	 * @param dir the path to the directory containing the list of files to choose from.
	 * @throws IOException signals if any IO exception occurred while reading
	 * the files.
	*/
	Data(String dir) throws IOException {
		this.fileList = new ArrayList<String[]>();
		this.directory = Paths.get(dir);
		
		Files.walk(this.directory).forEach(path -> {
			try {
				this.ParseInfo(path.toFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	/**
	 * The GetFileList method provides the list of all the data files available 
	 * for the user to choose from.
	 * 
	 * @return the list of all the data files available for the user to choose from.
	 */
	protected ArrayList<String[]> GetFileList(){
		return this.fileList;
	}

	/**
	 * The parseInfo method parses the required header from the file chosen.
	 * 
	 * @param file The data file containing distance between different cities.
	 * @throws IOException signals if any IO exception occurred while reading
	 * the files.
	 */
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
	
	/**
	 * The ParseData method parses the data file to get the coordinates of
	 * the cities.
	 * 
	 * @param file The data file containing distance between different cities.
	 * @throws IOException signals if any IO exception occurred while reading
	 * the files.
	 */
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
	
	/**
	 * The GetDataPoints parses the data file to get the coordinates 
     * for a given city.
	 * 
	 * @param dir the path to the directory containing the file to be parsed.
     * @return Arraylist of Strings containing the city and its coordinates.
	 * @throws IOException signals if any IO exception occurred while reading
	 * the files.
	 */
	public ArrayList<String> GetDataPoints(String dir) throws IOException {
		Path p = Paths.get(dir);
		this.dataPoints = new ArrayList<String>();
		this.ParseData(p.toFile());
		
		return this.dataPoints;
	}
}
