import java.io.*;

interface MakeGraphInterface{
    double[][] makeAdjacencyMatrix();
    double[][] GetData(String dir, int dimension) throws IOException;
}