import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class Graphics implements GraphicsInterface {
	final String path = "src/Data/";
	private JFrame frame;
	JPanel mainPanel;

	public Graphics(){
		this.frame = new JFrame();
		this.mainPanel = new JPanel();
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setGraphics();
	}
	
	public void Start() throws IOException {
		JPanel labelPanel = new JPanel(new GridLayout(1,1, 10, 20));
		labelPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel dataPromptLabel = new JLabel("Would you like to view Symmetric or Asymmetric data?");
		
		JButton symmetricButton = new JButton("Symmetric");
		
		symmetricButton.addActionListener (new ActionListener() { 
			  public void actionPerformed(ActionEvent e){ 
				try {
					displaySymmetric();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			  } 
			} ) ;
		
		JButton asymmetricButton = new JButton("Asymmetric");
		asymmetricButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e){ 
				try {
					displayAsymmetric();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			  } 
			} );

		labelPanel.add(dataPromptLabel);
		
	    buttonPanel.add(asymmetricButton);
	    buttonPanel.add(symmetricButton);
	    
	    this.mainPanel.add(labelPanel);
	    this.mainPanel.add(buttonPanel);
	    
	    setGraphics();
	}
	
	public JTable GetJTable(ArrayList<String[]> fileList) {
		String[] columnNames = {"Name",
                "Comment",
                "Dimension",
                };
		Object[][] data = new Object[fileList.size()][4];
		for (int i = 0; i < fileList.size(); i++)
		{
			for (int j = 0; j < 4; j++)
			{
				data[i][j] = fileList.get(i)[j];
			}
		}
		
		JTable table = new JTable(data, columnNames);
		return table;
	}
	
	public void displaySymmetric() throws IOException {
		SymmetricData sym = new SymmetricData(path + "SymmetricData/");
		ArrayList<String[]> fileList = sym.GetFileList();
		JTable table = GetJTable(fileList);
		table.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	try {
					double[][] tsp = sym.GetDataPoints(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
					GetShortestPath gsp = new GetShortestPath(tsp);
					gsp.minPath();
					System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
					System.out.println("Path to take: "+ gsp.getOrderOfCities());
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
	        }
	    });
		
		this.mainPanel.removeAll();
		this.mainPanel.add(scrollPane);
		setGraphics();
	}
	
	public void displayAsymmetric() throws IOException {
		AsymmetricData asym = new AsymmetricData(path + "AsymmetricData/");
		ArrayList<String[]> fileList = asym.GetFileList();
		JTable table = GetJTable(fileList);
		table.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent event) {
	        	try {
					double[][] tsp = asym.GetDataPoints(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
					GetShortestPath gsp = new GetShortestPath(tsp);
					gsp.minPath();
					System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
					System.out.println("Path to take: "+ gsp.getOrderOfCities());
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
	        }	    });
		this.mainPanel.removeAll();
		this.mainPanel.add(scrollPane);
		setGraphics();
	}
	
	public void setGraphics() {
		this.frame.add(this.mainPanel);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}
}
