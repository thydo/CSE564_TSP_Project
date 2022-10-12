import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.multi.MultiLabelUI;

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
        this.mainPanel.setBorder(new EmptyBorder(10,10,10,10));
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
		table.setDefaultEditor(Object.class, null);
		table.setFillsViewportHeight(true);
		
		return table;
	}
	
	public void displaySymmetric() throws IOException {
		SymmetricData sym = new SymmetricData(path + "SymmetricData/");
		ArrayList<String[]> fileList = sym.GetFiles();
		JTable table = GetJTable(fileList);

		JScrollPane scrollPane = new JScrollPane(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	try {
	        		String[] sel = {fileList.get(table.getSelectedRow())[0].toString(), fileList.get(table.getSelectedRow())[1].toString()};
					RunAlgorithm(sel, sym.GetData(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString())), true);
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
		ArrayList<String[]> fileList = asym.GetFiles();
		JTable table = GetJTable(fileList);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
	        	try {
	        		String[] sel = {fileList.get(table.getSelectedRow())[0].toString(), fileList.get(table.getSelectedRow())[1].toString()};
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
	        }	    });
		this.mainPanel.removeAll();
		this.mainPanel.add(scrollPane);
		setGraphics();
	}
	
	public void RunAlgorithm (String[] info, double[][] tsp, boolean isSym)
	{
		GetShortestPath gsp = new GetShortestPath(tsp);
		gsp.minPath();
		System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
		System.out.println("Path to take: "+ gsp.getOrderOfCitiesVisited());
		
		mainPanel.removeAll();
		
		JPanel labelPanel = new JPanel(new GridLayout(1,1, 10, 20));
		labelPanel.setBorder(new EmptyBorder(10,10,10,10));
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JPanel outputPanel = new JPanel(new GridLayout(1,1, 10, 20));
		outputPanel.setBorder(new EmptyBorder(10,10,10,10));
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		
		JLabel distLabel = new JLabel("Minimum Distance: " + gsp.getMinDistToVisit());
		JLabel pathLabel = new JLabel("Path to take: ");
		
		JLabel nameLabel = new JLabel("Filename: " + info[0]);
		JLabel infoLabel = new JLabel("Comment: " + info[1]);		
		
		JButton symmetricButton = new JButton("Start Over");
		
		labelPanel.add(nameLabel);
		labelPanel.add(infoLabel);
		
	    buttonPanel.add(symmetricButton);
	    
	    outputPanel.add(distLabel);
	    outputPanel.add(pathLabel);
		if (isSym)
		{
			outputPanel.add(DisplaySymmetricOutput(gsp));
			System.out.print(tsp[0][0]);
		}
		else
		{
			outputPanel.add(DisplayAsymmetricOutput(gsp));
		}

		mainPanel.add(labelPanel);
		mainPanel.add(outputPanel);
		mainPanel.add(buttonPanel);
		mainPanel.setAutoscrolls(true);

		setGraphics();
		
	}
	
	public JLabel DisplaySymmetricOutput(GetShortestPath gsp)
	{
		JLabel distLabel = new JLabel();
		return distLabel;		
	}
	
	public JLabel DisplayAsymmetricOutput(GetShortestPath gsp)
	{
	    JLabel label = new JLabel("<html><p style=\"width:100px\">"+gsp.getOrderOfCitiesVisited().toString()+"\"</p></html>");

		return label;
	}
	
	public void setGraphics() {
		this.frame.add(this.mainPanel);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}
}
