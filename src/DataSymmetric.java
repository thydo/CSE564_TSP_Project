import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * The DataSymmetric class is inheriting the Data class and implementing the 
 * DataInterfaceMatrix interface and creating a 2-Dimensional
 * matrix of distances between cities.
 */
public class DataSymmetric extends Data implements DataInterfaceMatrix {
    
    /**
     * Constructor for class DataSymmetric, calling constructor of its parent.
     * 
     * @param dir the path to the directory containing the list of Symmetric files 
     * to choose from.
     * @throws IOException signals if any IO exception occurred while reading
	 * the files.
     */
    DataSymmetric(String dir) throws IOException {
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
    public double[][] getDataPoints(String dir, int dimension) throws IOException {
    	this.dataPoints = super.getDataPoints(dir);
    	dataPoints = super.getDataPoints(dir);
        return this.makeAdjacencyMatrix();
    }
    
    /**
     * This method will provide us the coordinates for cities to help in plotting.
     * 
     * @return the list of coordinates of cities.
     */
    @Override
    public List<String[]> getCityCoords() {
    	List<String[]> cityList = new ArrayList<String[]>();
    	this.dataPoints.forEach(city -> {
    		String[] c = city.split("\\s+");
    		cityList.add(c);
    	});
    	return cityList;
    }

    /**
     * The makeAdjacencyMatrix method uses the parsed data to create 
     * the 2-Dimensional matrix of distances between cities.
     * 
     * @return the 2-Dimensional matrix of distances between cities.
     */
    private double[][] makeAdjacencyMatrix() {
        int cities = this.dataPoints.size();
        double[][] tsp = new double[cities][cities];
        double[][] coordinates = new double[cities][2];

        for(int i =0; i< cities;i++){
            String[] split = this.dataPoints.get(i).split(" ");

            int city = Integer.valueOf(split[0]);
            double cordX = Double.valueOf(split[1]);
            double cordY = Double.valueOf(split[2]);

            coordinates[city-1][0] = cordX;
            coordinates[city-1][1] = cordY;
        }

        for(int i =0 ; i < tsp.length;i++)
            tsp[i][i] = Double.MAX_VALUE;

        for(int i = 0; i<cities; i++) {
            for(int j = i+1;j<this.dataPoints.size();j++) {
                double x1 = coordinates[i][0];
                double y1 = coordinates[i][1];
                double x2 = coordinates[j][0];
                double y2 = coordinates[j][1];
                double dist = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
                tsp[i][j] = dist;
                tsp[j][i] = dist;
            }
        }
        return tsp;
    } 
    
}