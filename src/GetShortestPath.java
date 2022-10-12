import java.util.*;

public class GetShortestPath implements ShortestPathInterface {
    private Set<Integer> visitedCities;
    private List<Integer> orderOfCities;
    private double minDist;
    private double[][] tsp;

    GetShortestPath(double[][] tsp){
        this.minDist = Double.MAX_VALUE;
        this.tsp = tsp;
    }

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
    
    public List<Integer> getOrderOfCities(){
        this.orderOfCities = new ArrayList<>();
        this.orderOfCities.addAll(this.visitedCities);
        this.orderOfCities.add(this.orderOfCities.get(0));
        return this.orderOfCities;
    }

    public double getMinDistToVisit(){
        return this.minDist;
    }
}
