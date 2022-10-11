import java.util.*;

public class GetShortestPath {
    private Set<Integer> visitedCities;
    private double minDist;
    private double[][] tsp;

    GetShortestPath(double[][] tsp){
        //this.visitedCities = new HashSet<>();
        this.minDist = Double.MAX_VALUE;
        this.tsp = tsp;
    }

	public void minPath(){
        
        for(int srcCity = 0 ; srcCity < tsp.length; srcCity++){

            Set<Integer> currVisitedCities = new LinkedHashSet<>();
            double currMinDist = 0;

            currVisitedCities.add(srcCity);
            int localCity = srcCity;

            while(true){
                int localMinCity = 0;
                double localMinDist = Double.MAX_VALUE;

                for( int i = 0; i < this.tsp.length; i++){

                    if(i == localCity || currVisitedCities.contains(i))
                        continue;

                    if(this.tsp[localCity][i] < localMinDist){
                        localMinCity = i;
                        localMinDist = this.tsp[localCity][i];
                    }
                }
                currVisitedCities.add(localMinCity);
                currMinDist += localMinDist;
                localCity = localMinCity;

                if(currVisitedCities.size() == this.tsp.length){
                    currMinDist += this.tsp[localCity][srcCity];
                    break;
                }
            }
            
            if(this.minDist > currMinDist){
                this.visitedCities = currVisitedCities;
                this.minDist = currMinDist;
            }
        }
    }
    public Set<Integer> getOrderOfCitiesVisited(){
        return this.visitedCities;
    }
    public double getMinDistToVisit(){
        return this.minDist;
    }
}
