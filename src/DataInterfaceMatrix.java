import java.io.IOException;
import java.util.List;

/**
 * DataInterfaceMatrix is extending DataInterface and provides
 * the interface for fetching data file containing distance between different
 * cities and to return the parsed data in the form of 2-D matrix instead
 * of an List.
*/
interface DataInterfaceMatrix extends DataInterface {

    /**
     * The getDataPoints method parses the data file to get the coordinates 
     * for a given city.
     * 
     * @param dir the path to the directory containing the file to be parsed.
     * @param dimension dimension for the 2-Dimensional matrix to be formed.
     * @return the 2-Dimensional matrix of distances between cities.
     * @throws IOException signals if any IO exception occurred while reading
	 * the files.
    */
    double[][] getDataPoints(String dir, int dimension) throws IOException;

    /**
     * This method will provide us the coordinates for cities to help in plotting.
     * 
     * @return the list of coordinates of cities.
     */
    public List<String[]> getCityCoords();
}
