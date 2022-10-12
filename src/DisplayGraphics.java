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

// <<<<<<< HEAD
// 		FetchingDataInterfaceMatrix sym = new SymmetricData(path + "SymmetricData/");
// 		ArrayList<String[]> fileList = sym.GetFileList();
		FetchingDataInterface sym = new SymmetricData(path + "SymmetricData/");
		//--ArrayList<String[]> fileList = sym.GetFiles();
		//--JTable table = GetJTable(fileList); 

		//---JScrollPane scrollPane = new JScrollPane(table);
		
		//--table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	     //--   public void valueChanged(ListSelectionEvent event) {
	        	try {

// 					double[][] tsp = sym.GetDataPoints(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
// 					ShortestPathInterface gsp = new GetShortestPath(tsp);
// 					gsp.minPath();
// 					System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
// 					System.out.println("Path to take: "+ gsp.getOrderOfCities());

	        		//--String[] sel = {fileList.get(table.getSelectedRow())[0].toString(), fileList.get(table.getSelectedRow())[1].toString()};
					//--RunAlgorithm(sel, sym.GetData(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString())), true);

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

		//-- AsymmetricData asym = new AsymmetricData(path + "AsymmetricData/");
		// --ArrayList<String[]> fileList = asym.GetFiles();
		// --JTable table = GetJTable(fileList);
		
		//===JScrollPane scrollPane = new JScrollPane(table);
		
		//--table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
	        	try {

					// double[][] tsp = asym.GetDataPoints(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
					// ShortestPathInterface gsp = new GetShortestPath(tsp);
					// gsp.minPath();
					// System.out.println("Minimum Distance: " + gsp.getMinDistToVisit());
					// System.out.println("Path to take: "+ gsp.getOrderOfCities());
	        		//--String[] sel = {fileList.get(table.getSelectedRow())[0].toString(), fileList.get(table.getSelectedRow())[1].toString()};

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
		
		JButton symmetricButton = new JButton("Start Over");
		
		labelPanel.add(nameLabel);
		labelPanel.add(infoLabel);
		
	    buttonPanel.add(symmetricButton);
	    
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
		JPanel panel = new JPanel();
		JPanel plot = new JPanel(){
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D graph = (Graphics2D) g;
            	graph.setPaint(Color.MAGENTA);
            	graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        		
            	int scale = 30;
            	for (int city = 0; city < cityOrder.size(); city++)
        	    {
        	    	double x = Double.parseDouble(cityList.get(cityOrder.get(city)-1)[1])/scale;
        	    	double y = Double.parseDouble(cityList.get(cityOrder.get(city)-1)[2])/scale;
        	    	graph.fill(new Ellipse2D.Double(x - 600,- y + 600,3,3));
        	    }
            }
        };
	    JLabel label = new JLabel("<html><p style=\"width:100px\">"+ cityOrder.toString()+"\"</p></html>");
	    panel.add(label);
	    JFrame f = new JFrame();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.add(plot);
	    f.setSize(400,400);
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
		return panel;		
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
