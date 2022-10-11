import java.io.*;

interface MakeGraph{
    double[][] makeAdjacencyMatrix();
    double[][] GetData(String dir, int dimension) throws IOException;
}