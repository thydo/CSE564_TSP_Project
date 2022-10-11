import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AsymmetricData extends Data{
	private ArrayList<String[]> fileList;
	private ArrayList<Object> dataPoints;
    public AsymmetricData(String dir) throws IOException
    {
    	super(dir);
    	this.fileList = super.GetFileList();
    	fileList.forEach(item -> {System.out.println (item[3]);});
    }
    
    public void GetData(String dir)
    {
    	
    }
    
}
