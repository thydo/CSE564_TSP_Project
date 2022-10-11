import java.io.*;
import java.util.*;

public class AsymmetricData extends Data implements MakeGraph{
	private ArrayList<String[]> fileList;
	private ArrayList<String> dataPoints;
    private int dimension;
    public AsymmetricData(String dir) throws IOException {
    	super(dir);
    	this.fileList = super.GetFileList();
    }
    
    public ArrayList<String[]> GetFiles() {
    	this.fileList = super.GetFileList();

    	return fileList;
    }
    
    public double[][] GetData(String dir, int dimension) throws IOException {
    	dataPoints = super.GetDataPoints(dir);
        this.dimension = dimension;
        return this.makeAdjacencyMatrix();
    }
    
    public double[][] makeAdjacencyMatrix(){
        ArrayList<Double> elements = new ArrayList<Double>();
        dataPoints.forEach(line -> {
        	line = line.strip();
        	String[] e = line.split("\\s+");
        	for (int i = 0; i < e.length; i++)
        	{
        		elements.add(Double.parseDouble(e[i]));
        	}
        });
                
        double[][] tsp = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++)
        {
        	for (int j = 0; j < dimension; j++)
        	{
        		tsp[i][j] = elements.get((i*17)+j);
        	}
        }
        return tsp;
    }
	
}
