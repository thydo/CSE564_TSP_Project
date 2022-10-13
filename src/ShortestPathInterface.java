import java.util.List;

/**
 * ShortestPathInterface provides the interface for implementation of the
 * algorithm to compute the shortest distance for Traveling 
 * Salesman Problem.
*/
interface ShortestPathInterface {
   
    /**
     * The minPath method will calculate the shortest path for the given set 
     * of cities along with the order of cities that salesman should follow 
     * to get this minimum distance. It will then update the object variables
     * orderOfCities and minDist with the final result.
    */
    void minPath();

    /**
     * The getOrderOfCities method will return the object variable orderOfCities.
     * 
     * @return the order of cities to be followed by salesman to get 
     * the minimum path.
    */
    List<Integer> getOrderOfCities();

    /**
     * The getMinDistToVisit method will return the object variable minDist.
     * 
     * @return the minimum distance that salesman will travel.
    */
    double getMinDistToVisit();
}