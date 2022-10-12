import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.multi.MultiLabelUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class DisplayGraphics implements GraphicsInterface {
	final String path = "src/Data/";
	private JFrame frame;
	JPanel mainPanel;

	public DisplayGraphics(){
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
 		ArrayList<String[]> fileList = sym.GetFileList();

		JTable table = GetJTable(fileList); 

		JScrollPane scrollPane = new JScrollPane(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent event) {
	        	try {
	        		String[] sel = {fileList.get(table.getSelectedRow())[0].toString(), fileList.get(table.getSelectedRow())[1].toString()};
					double[][] dataPoints = sym.GetDataPoints(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
	        		RunAlgorithm(sym.GetCityCoords(), sel, dataPoints, true);

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

		FetchingDataInterfaceMatrix asym = new AsymmetricData(path + "AsymmetricData/");
		ArrayList<String[]> fileList = asym.GetFileList();

		JTable table = GetJTable(fileList);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
	        	try {

					 double[][] tsp = asym.GetDataPoints(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
					 ShortestPathInterface gsp = new GetShortestPath(tsp);
					 gsp.minPath();
					 System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
					 System.out.println("Path to take: "+ gsp.getOrderOfCities());
					 String[] sel = {fileList.get(table.getSelectedRow())[0].toString(), fileList.get(table.getSelectedRow())[1].toString()};
		        	 RunAlgorithm(null, sel, asym.GetDataPoints(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString())), false);

				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
	        }	    });
		this.mainPanel.removeAll();
		this.mainPanel.add(scrollPane);
		setGraphics();
	}
	
	public void RunAlgorithm (ArrayList<String[]> cityCoords, String[] info, double[][] tsp, boolean isSym)
	{
		GetShortestPath gsp = new GetShortestPath(tsp);
		gsp.minPath();
		System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
		System.out.println("Path to take: "+ gsp.getOrderOfCities());
		
		mainPanel.removeAll();
		
		JPanel labelPanel = new JPanel(new GridLayout(1,1, 10, 20));
		labelPanel.setBorder(new EmptyBorder(10,10,10,10));
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JPanel outputPanel = new JPanel();
		outputPanel.setBorder(new EmptyBorder(10,10,10,10));
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		
		JLabel distLabel = new JLabel("Minimum Distance: " + gsp.getMinDistToVisit());
		JLabel pathLabel = new JLabel("Path to take: ");
		
		JLabel nameLabel = new JLabel("Filename: " + info[0]);
		JLabel infoLabel = new JLabel("Comment: " + info[1]);		
		
		JButton startOverButton = new JButton("Start Over");
		startOverButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e){ 
				try {
					mainPanel.removeAll();
					Start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			  } 
			} );
		
		labelPanel.add(nameLabel);
		labelPanel.add(infoLabel);
		
	    buttonPanel.add(startOverButton);
	    
	    outputPanel.add(distLabel);
	    outputPanel.add(pathLabel);
		if (isSym)
		{
			outputPanel.add(DisplaySymmetricOutput(gsp, cityCoords));
		}
		else
		{
			outputPanel.add(DisplayAsymmetricOutput(gsp));
		}
		this.mainPanel.add(labelPanel);
		this.mainPanel.add(outputPanel);
		this.mainPanel.add(buttonPanel);
		setGraphics();
		
	}
	
	public JPanel DisplaySymmetricOutput(GetShortestPath gsp, ArrayList<String[]> cityList)
	{
		List<Integer> cityOrder = gsp.getOrderOfCities();
		ArrayList<double[]> plotCoords = ProcessCoordinates(cityList, cityOrder);
		JPanel panel = new JPanel();
		JPanel plot = new JPanel(){
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D graph = (Graphics2D) g;
            	graph.setPaint(Color.MAGENTA);
            	//graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        		
            	for (int i = 0; i < plotCoords.size(); i++)
            	{
            		graph.fill(new Ellipse2D.Double(plotCoords.get(i)[0], -plotCoords.get(i)[1]+500,3,3));
            	}
            }
        };
		plot.setBorder(new EmptyBorder(20,20,20,20));
	
	    JLabel label = new JLabel("<html><p style=\"width:100px\">"+ cityOrder.toString()+"\"</p></html>");
	    panel.add(label);
	    JFrame f = new JFrame();
	    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    f.add(plot);
	    f.setSize(600,600);
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
		return panel;		
	}
	
	public ArrayList<double[]> ProcessCoordinates(ArrayList<String[]> cityList, List<Integer> cityOrder)
	{
		ArrayList<double[]> coords = new ArrayList<double[]>();
		
		double maxX = 0.0;
		double maxY = 0.0;
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
    	for (int city = 0; city < cityOrder.size(); city++)
	    {
    		double[] coord = {0,0}; 
	    	coord[0] = Double.parseDouble(cityList.get(cityOrder.get(city)-1)[1]);
	    	if (maxX < coord[0])
	    			maxX = coord[0];
	    	if (minX > coord[0])
	    			minX = coord[0];
	    	
	    	coord[1] = Double.parseDouble(cityList.get(cityOrder.get(city)-1)[2]);
	    	if (maxY < coord[1])
	    			maxY = coord[1];
	    	if (minY > coord[1])
	    			minY = coord[1];
	    	coords.add(coord);
	    }
    	
    	double diffX = maxX - minX;
    	double diffY = maxY - minY;
    	
    	for (int city = 0; city < coords.size(); city++)
    	{
    		double[] newCoords = {0,0};
    		double normX = coords.get(city)[0] - minX;
    		normX = ((normX/diffX)*400) + 50;
    		newCoords[0] = normX;
    		
    		double normY = coords.get(city)[1] - minY;
    		normY = ((normY/diffY)*400) + 50;
    		newCoords[1] = normY;
    		coords.set(city, newCoords);
    	}
    	
		return coords;
	}
	
	public JLabel DisplayAsymmetricOutput(GetShortestPath gsp)
	{
	    JLabel label = new JLabel("<html><p style=\"width:100px\">"+gsp.getOrderOfCities().toString()+"\"</p></html>");

		return label;
	}
	
	
	public void setGraphics() {
		this.frame.add(this.mainPanel);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}
}
