import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;

interface DisplayGraphicsInterface {
    public void Start() throws IOException;
    public JTable GetJTable(ArrayList<String[]> fileList);
    public void displaySymmetric() throws IOException;
    public void displayAsymmetric() throws IOException;
    public void setGraphics();  
}
