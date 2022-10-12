import java.io.*;
import java.util.*;
/**
 * The SymmetricData class is inheriting the Data class and implementing the 
 * FetchingDataInterfaceMatrix interface and creating a 2-Dimensional
 * matrix of distances between cities.
 */
public class SymmetricData extends Data implements FetchingDataInterfaceMatrix {
    
    /**
     * Constructor for class SymmetricData, calling constructor of its parent.
     * 
     * @param dir the path to the directory containing the list of Symmetric files 
     * to choose from.
     * @throws IOException signals if any IO exception occurred while reading
	 * the files.
     */
    SymmetricData(String dir) throws IOException {
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
    public double[][] GetDataPoints(String dir, int dimension) throws IOException {
    	this.dataPoints = super.GetDataPoints(dir);
    	//this.dataPoints.forEach(item -> {System.out.println (item);});
    	dataPoints = super.GetDataPoints(dir);
        return this.makeAdjacencyMatrix();
    }
    

    /**
     * The makeAdjacencyMatrix method uses the parsed data to create 
     * the 2-Dimensional matrix of distances between cities.
     * 
     * @return the 2-Dimensional matrix of distances between cities.
     */
   // private double[][] makeAdjacencyMatrix() {
    public ArrayList<String[]> GetCityCoords()
    {
    	ArrayList<String[]> cityList = new ArrayList<String[]>();
    	this.dataPoints.forEach(city ->
    	{
    		String[] c = city.split("\\s+");
    		cityList.add(c);
    	});
    	return cityList;
    }
    
    public double[][] makeAdjacencyMatrix(){
//>>>>>>> 8560c74d6a473b22ee7794b0c149286a68838971
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

        
        for(int i = 0; i<cities; i++){
            for(int j = i+1;j<this.dataPoints.size();j++){
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