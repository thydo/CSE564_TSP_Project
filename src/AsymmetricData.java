import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AsymmetricData extends Data{
	private ArrayList<String[]> fileList;
	private ArrayList<String> dataPoints;
    public AsymmetricData(String dir) throws IOException
    {
    	super(dir);
    	this.fileList = super.GetFileList();
    }
    
    public ArrayList<String[]> GetFiles()
    {
    	this.fileList = super.GetFileList();

    	return fileList;
    }
    
    public void GetData(String dir, int dimension) throws IOException
    {
    	dataPoints = super.GetDataPoints(dir);
    	dataPoints.forEach(item -> {System.out.println (item);});
    	System.out.println(dataPoints.get(0));
    }
    
}
