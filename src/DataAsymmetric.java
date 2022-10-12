import java.io.*;
import java.util.*;

/**
 * The AsymmetricData class is inheriting the Data class and implementing the 
 * FetchingDataInterfaceMatrix interface and creating a 2-Dimensional
 * matrix of distances between cities.
 */
public class DataAsymmetric extends Data implements DataInterfaceMatrix{
    private int dimension;
    
    /**
     * Constructor for class AsymmetricData, calling constructor of its parent.
     * 
     * @param dir the path to the directory containing the list of Asymmetric files 
     * to choose from.
     * @throws IOException signals if any IO exception occurred while reading
	 * the files.
     */
    DataAsymmetric(String dir) throws IOException {
    	super(dir);
    }
    
    /**
     * This is an overridden method of parent class to include dimension 
     * required for the matrix and to return the 2-Dimensional matrix 
     * instead of ArrayList of points.
     * 
     * @param dir the path to the directory containing the file to be parsed.
     * @param dimension dimension for the 2-Dimensional matrix to be formed.
     * @return the 2-Dimensional matrix of distances between cities.
     * @throws IOException signals if any IO exception occurred while reading
	 * the files.
     */
    @Override
    public double[][] GetDataPoints(String dir, int dimension) throws IOException {
    	this.dataPoints = super.GetDataPoints(dir);
        this.dimension = dimension;
        return this.makeAdjacencyMatrix();
    }
    
    /**
     * The makeAdjacencyMatrix method uses the parsed data to create 
     * the 2-Dimensional matrix of distances between cities.
     * 
     * @return the 2-Dimensional matrix of distances between cities.
     */
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

    @Override
    public ArrayList<String[]> GetCityCoords() {
        
        return null;
    }
	
}
