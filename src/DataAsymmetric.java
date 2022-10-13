import java.io.IOException;
import java.util.*;

/**
 * The DataAsymmetric class is inheriting the Data class and implementing the 
 * DataInterfaceMatrix interface and creating a 2-Dimensional
 * matrix of distances between cities.
 */
public class DataAsymmetric extends Data implements DataInterfaceMatrix {
    private int dimension;
    
    /**
     * Constructor for class DataAsymmetric, calling constructor of its parent.
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
     * instead of List of points.
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
    private double[][] makeAdjacencyMatrix() {
        List<Double> elements = new ArrayList<Double>();
        this.dataPoints.forEach(line -> {
        	line = line.strip();
        	String[] e = line.split("\\s+");
        	for (int i = 0; i < e.length; i++) {
        		elements.add(Double.parseDouble(e[i]));
        	}
        });
                
        double[][] tsp = new double[this.dimension][this.dimension];
        for (int i = 0; i < this.dimension; i++) {
        	for (int j = 0; j < this.dimension; j++) {
        		tsp[i][j] = elements.get((i*17)+j);
        	}
        }
        return tsp;
    }

    /**
     * This method will provide us the coordinates for cities to help in plotting.
     * Since, we are getting 2-D matrix as input from data file for Asymmetric data 
     * and not getting coordinates,our code is unable to plot the points on the frame.
     * 
     * @return the list of corrdinates of cities.
     */
    @Override
    public List<String[]> GetCityCoords() {
        return null;
    }
	
}
