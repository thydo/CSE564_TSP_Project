import java.util.*;
import java.io.*;

interface FetchingData{
    ArrayList<String[]> GetFileList();
    void ParseInfo (File file) throws IOException;
    void ParseData(File file) throws IOException;
    ArrayList<String> GetDataPoints(String dir) throws IOException;
}
