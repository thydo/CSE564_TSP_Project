import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SymmetricData extends Data{
	private ArrayList<String[]> fileList;
	private ArrayList<String> dataPoints;
    public SymmetricData(String dir) throws IOException
    {
    	super(dir);
    }
    
    public ArrayList<String[]> GetFiles()
    {
    	this.fileList = super.GetFileList();

    	return fileList;
    }
    
    public void GetData(String dir, int dimension) throws IOException
    {
    	dataPoints = super.GetDataPoints(dir);
    	//dataPoints.forEach(item -> {System.out.println (item);});
    }
    
}