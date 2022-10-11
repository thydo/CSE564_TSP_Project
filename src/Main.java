
import java.io.IOException;

public class Main {
	public static void main (String[] args) throws IOException
	{

		Graphics graphics = new Graphics();
		double[][] tsp = graphics.Start();
		System.out.println(tsp[0][45]);
		GetShortestPath gsp = new GetShortestPath(tsp);
		gsp.minPath();
		System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
		System.out.println("Path to take: "+ gsp.getOrderOfCitiesVisited());
	}
}
