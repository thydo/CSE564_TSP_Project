
import java.io.IOException;
import javax.swing.*;

public class Main {
	public static AsymmetricData asym;
	public static SymmetricData sym;
	public static void main (String[] args) throws IOException
	{
/*
		double[][] tsp = {
            { -1, 10, 15, 20 },
            { 1, -1, 35, 25 },
            { 5, 3, -1, 30 },
            { 2, 5, 3, -1 }
        };
		// double[][] tsp = {
        //     { -1, 10, 15, 20 },
        //     { 10, -1, 35, 25 },
        //     { 15, 35, -1, 30 },
        //     { 20, 25, 30, -1 }
        // };
		System.out.println("start");
		GetShortestPath gsp = new GetShortestPath(tsp);
		gsp.minPath();
		System.out.println(gsp.getMinDistToVisit());
		System.out.println(gsp.getOrderOfCitiesVisited());
		System.out.println("end");
*/
		Graphics graphics = new Graphics();
		graphics.Start();

		
		String path = "src/Data/";
		asym = new AsymmetricData(path + "AsymmetricData/");
		sym = new SymmetricData(path + "SymmetricData/");

		asym.GetData("src\\\\Data\\\\AsymmetricData\\\\ry48p.atsp");

	}
}
