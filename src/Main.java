<<<<<<< HEAD
//import javax.swing.JFrame;
=======
import javax.swing.*;
>>>>>>> a8aa7a7d603b32344e66bfb5a4e67b71fcec8e25

public class Main {

	public static void main (String[] args)
	{
<<<<<<< HEAD
		// JFrame frame = new JFrame();
		// frame.setVisible(true);
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
=======
		Graphics graphics = new Graphics();
		graphics.Start();
>>>>>>> a8aa7a7d603b32344e66bfb5a4e67b71fcec8e25
	}
}
