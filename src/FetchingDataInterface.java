import java.util.*;
import java.io.*;

/**
 * FetchingDataInterface provides the interface for fetching data file containing
 * distance between different cities and to return the parsed data.
*/
interface FetchingDataInterface {

    /**
     * The GetDataPoints parses the data file to get the coordinates 
     * for a given city.
     * 
     * @param dir the path to the directory containing the file to be parsed.
     * @return Arraylist of Strings containing the city and its coordinates.
     * @throws IOException signals if any IO exception occurred while reading
	 * the files.
    */
    ArrayList<String> GetDataPoints(String dir) throws IOException;
}
