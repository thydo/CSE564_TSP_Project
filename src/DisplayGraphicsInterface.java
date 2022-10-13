import java.io.IOException;
import java.util.List;
import javax.swing.JTable;

/**
 * The DisplayGraphicsInterface provides the interface for requirements of
 * Graphic User Interface for Traveling Salesman Problem.
 */
interface DisplayGraphicsInterface {

    /**
	 * Start method will make the initial GUI frame, with a user prompt
	 * and two selection button for the user to select between viewing
	 * Symmetric and Asymmetric TSP data files
	 * 
	 * @throws IOException signals if any IO exception occurred while reading
	 * the files.
	*/
    public void start() throws IOException;

    /**
	 * GetJTable method creates a Table panel that takes the list of file information
	 * and provides the user with option to click on the file he wants to process.
	 * 
	 * @param fileList list of files available for the user to choose from.
	 * @return an interactive GUI list containing all the data files available
	 * for the user to click on.
	*/
    public JTable getJTable(List<String[]> fileList);

    /**
	 * RunAlgorithm method creates the shortestPath object and processes
	 * it to get shortest path and city order. 
	 * If it's symmetric, show the shortest path, city order list, and graph of cities.
	 * If it's asymmetric, show the shortest path and city order list.
	 * 
	 * @param cityCoords coordinates of the cities to process to get shortestPath.
	 * @param info Contains information regarding the row selected by the user.
	 * @param tsp 2-D matrix containing distance between different cities.
	 * @param isSym Boolean variable to indicate whether the data is Symmetric
	 * or aSymmetric.
	 */
    public void runAlgorithm (List<String[]> cityCoords, String[] info, double[][] tsp, boolean isSym);

    /**
	 * ProcessCoordinates scales the city coordinates to the size of the view frame
	 * 
	 * @param cityList List of all the cities available as a part of file chosen.
	 * @param cityOrder Order of cities to visit to get the minimum path.
	 * @return return a list of x y coordinates according to the view frame
	*/
    public List<double[]> processCoordinates(List<String[]> cityList, List<Integer> cityOrder);

    /**
	 * This method updates the graphic of the main frame.
	*/
    public void setGraphics();  
}
