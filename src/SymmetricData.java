import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SymmetricData extends Data{
	private ArrayList<String[]> fileList;
	private ArrayList<Object> dataPoints;
    public SymmetricData(String dir) throws IOException
    {
    	super(dir);
    	this.fileList = super.GetFileList();
    }
    
    public void GetData(String dir)
    {
    	
    }
    
}