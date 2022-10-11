import java.io.*;
import java.util.*;

public class SymmetricData extends Data implements MakeGraphInterface {
	private ArrayList<String[]> fileList;
	private ArrayList<String> dataPoints;
    public SymmetricData(String dir) throws IOException {
    	super(dir);
    }
    
    public ArrayList<String[]> GetFiles() {
    	this.fileList = super.GetFileList();

    	return fileList;
    }
    
    public double[][] GetData(String dir, int dimension) throws IOException {
    	this.dataPoints = super.GetDataPoints(dir);
    	this.dataPoints.forEach(item -> {System.out.println (item);});
    	dataPoints = super.GetDataPoints(dir);
        return this.makeAdjacencyMatrix();
    }
    
    public double[][] makeAdjacencyMatrix(){
        int cities = this.dataPoints.size();
        double[][] tsp = new double[cities][cities];

        double[][] coordinates = new double[cities][2];
        for(int i =0; i< cities;i++){
            String[] split = dataPoints.get(i).split(" ");

            int city = Integer.valueOf(split[0]);
            double cordX = Double.valueOf(split[1]);
            double cordY = Double.valueOf(split[2]);

            coordinates[city-1][0] = cordX;
            coordinates[city-1][1] = cordY;
        }

        for(int i =0 ; i < tsp.length;i++)
            tsp[i][i] = Double.MAX_VALUE;

        
        for(int i = 0; i<cities; i++){
            for(int j = i+1;j<dataPoints.size();j++){
                double x1 = coordinates[i][0];
                double y1 = coordinates[i][1];
                double x2 = coordinates[j][0];
                double y2 = coordinates[j][1];
                double dist = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
                tsp[i][j] = dist;
                tsp[j][i] = dist;
            }
        }
        return tsp;
    } 
    
}