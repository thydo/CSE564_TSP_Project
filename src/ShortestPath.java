import java.util.Set;

interface ShortestPath{
    void minPath();
    Set<Integer> getOrderOfCitiesVisited();
    double getMinDistToVisit();
}