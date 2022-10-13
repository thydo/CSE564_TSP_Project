import java.util.List;
import java.io.IOException;

/**
 * DataInterface provides the interface for fetching data file containing
 * distance between different cities and to return the parsed data.
*/
interface DataInterface {

    /**
     * The getDataPoints method parses the data file to get the coordinates 
     * for a given city.
     * 
     * @param dir the path to the directory containing the file to be parsed.
     * @return List of Strings containing the city and its coordinates.
     * @throws IOException signals if any IO exception occurred while reading
	 * the files.
    */
    List<String> getDataPoints(String dir) throws IOException;

    /**
	 * The GetFileList method provides the list of all the data files available 
	 * for the user to choose from.
	 * 
	 * @return the list of all the data files available for the user to choose from.
	 */
	List<String[]> getFileList();
}
