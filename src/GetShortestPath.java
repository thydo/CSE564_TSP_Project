import java.util.*;

/**
 * The GetShortestPath Class implements the algorithm to compute the 
 * shortest distance for Travelling Salesman Problem.
*/
public class GetShortestPath implements ShortestPathInterface {
    private Set<Integer> visitedCities;
    private List<Integer> orderOfCities;
    private double minDist;
    private double[][] tsp;
   
    /**
     * Constructor for class GetShortestPath.
     * 
     * @param tsp It will store the matrix representing distances between cities.
    */
    GetShortestPath(double[][] tsp){
        this.minDist = Double.MAX_VALUE;
        this.tsp = tsp;
    }
    
    /**
     * The minPath function will calculate the shortest path for the given set 
     * of cities along with the order of cities that salesman should follow 
     * to get this minimum distance. It will then update the object variables
     * orderOfCities and minDist with the final result.
    */
	public void minPath(){  
        for(int srcCity = 0 ; srcCity < tsp.length; srcCity++){

            Set<Integer> currVisitedCities = new LinkedHashSet<>();
            double currMinDist = 0;

            currVisitedCities.add(srcCity+1);
            int localCity = srcCity;

            while(true){
                int localMinCity = 0;
                double localMinDist = Double.MAX_VALUE;

                for( int i = 0; i < this.tsp.length; i++){

                    if(i == localCity || currVisitedCities.contains(i+1))
                        continue;

                    if(this.tsp[localCity][i] < localMinDist){
                        localMinCity = i;
                        localMinDist = this.tsp[localCity][i];
                    }
                }
                currVisitedCities.add(localMinCity+1);
                currMinDist += localMinDist;
                localCity = localMinCity;

                if(currVisitedCities.size() == this.tsp.length){
                    currMinDist += this.tsp[localCity][srcCity];
                    currVisitedCities.add(srcCity+1);
                    break;
                }
            }

            if(this.minDist > currMinDist){
                this.visitedCities = currVisitedCities;
                this.minDist = currMinDist;
            }
        }
    }
    
    /**
     * The getOrderOfCities function will return the object variable orderOfCities.
     * 
     * @return the order of cities to be followed by salesman to get 
     * the minimum path.
    */
    public List<Integer> getOrderOfCities(){
        this.orderOfCities = new ArrayList<>();
        this.orderOfCities.addAll(this.visitedCities);
        this.orderOfCities.add(this.orderOfCities.get(0));
        return this.orderOfCities;
    }

    /**
     * The getMinDistToVisit function will return the object variable minDist.
     * 
     * @return the minimum distance that saleman will travel.
    */
    public double getMinDistToVisit(){
        return this.minDist;
    }
}
