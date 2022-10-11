import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class Graphics {
	final String path = "src/Data/";
	private JFrame frame;
	JPanel mainPanel;
	//GetShortestPath gsp; 
	public Graphics(){
		frame = new JFrame();
		mainPanel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setGraphics();
	}
	
	public void Start() throws IOException
	{
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
	    
	    mainPanel.add(labelPanel);
	    mainPanel.add(buttonPanel);
	    
	    setGraphics();
	}
	
	public JTable GetJTable(ArrayList<String[]> fileList)
	{
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
	
	public void displaySymmetric() throws IOException
	{
		SymmetricData sym = new SymmetricData(path + "SymmetricData/");
		ArrayList<String[]> fileList = sym.GetFileList();
		JTable table = GetJTable(fileList);
		table.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	try {
					double[][] tsp = sym.GetData(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
					GetShortestPath gsp = new GetShortestPath(tsp);
					gsp.minPath();
					System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
					System.out.println("Path to take: "+ gsp.getOrderOfCitiesVisited());
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
	        }
	    });
		
		mainPanel.removeAll();
		mainPanel.add(scrollPane);
		setGraphics();
	}
	
	public void displayAsymmetric() throws IOException
	{
		AsymmetricData asym = new AsymmetricData(path + "AsymmetricData/");
		ArrayList<String[]> fileList = asym.GetFileList();
		JTable table = GetJTable(fileList);
		table.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent event) {
	        	try {
					double[][] tsp = asym.GetData(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
					GetShortestPath gsp = new GetShortestPath(tsp);
					gsp.minPath();
					System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
					System.out.println("Path to take: "+ gsp.getOrderOfCitiesVisited());
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
	        }	    });
		mainPanel.removeAll();
		mainPanel.add(scrollPane);
		setGraphics();
	}
	
	public void setGraphics() {
		frame.add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
