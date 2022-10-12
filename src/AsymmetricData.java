import java.io.*;
import java.util.*;

public class AsymmetricData extends Data{
    private int dimension;
    
    public AsymmetricData(String dir) throws IOException {
    	super(dir);
    	this.fileList = super.GetFileList();
    }
    
    protected ArrayList<String[]> GetFileList() {
    	this.fileList = super.GetFileList();
    	return this.fileList;
    }
    
    public double[][] GetDataPoints(String dir, int dimension) throws IOException {
    	this.dataPoints = super.GetDataPoints(dir);
        this.dimension = dimension;
        return this.makeAdjacencyMatrix();
    }
    
    private double[][] makeAdjacencyMatrix(){
        ArrayList<Double> elements = new ArrayList<Double>();
        this.dataPoints.forEach(line -> {
        	line = line.strip();
        	String[] e = line.split("\\s+");
        	for (int i = 0; i < e.length; i++)
        	{
        		elements.add(Double.parseDouble(e[i]));
        	}
        });
                
        double[][] tsp = new double[this.dimension][this.dimension];
        for (int i = 0; i < this.dimension; i++)
        {
        	for (int j = 0; j < this.dimension; j++)
        	{
        		tsp[i][j] = elements.get((i*17)+j);
        	}
        }
        return tsp;
    }
	
}
