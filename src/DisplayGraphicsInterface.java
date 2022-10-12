import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 * 
 */
interface DisplayGraphicsInterface {

    /**
     * 
     * @throws IOException
     */
    public void Start() throws IOException;

    /**
     * 
     * @param fileList
     * @return
     */
    public JTable GetJTable(ArrayList<String[]> fileList);

    /**
     * 
     * @throws IOException
     */
    public void displaySymmetric() throws IOException;

    /**
     * 
     * @throws IOException
     */
    public void displayAsymmetric() throws IOException;

    /**
     * 
     */
    public void setGraphics();  
}
