import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class Graphics {

	double[][] tsp;
	private JFrame frame;
	JPanel mainPanel;
	public Graphics(){
		frame = new JFrame();
		mainPanel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setGraphics();
		
	}
	
	public double[][] Start() throws IOException
	{
		
		JPanel labelPanel = new JPanel(new GridLayout(1,1, 10, 20));
		labelPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel dataPromptLabel = new JLabel("Would you like to view Symmetric or Asymmetric data?");
		
		JButton symmetricButton = new JButton("Symmetric");
		String path = "src/Data/";
		symmetricButton.addActionListener (new ActionListener() { 
			  public void actionPerformed(ActionEvent e){ 
				try {
					SymmetricData sym = new SymmetricData(path + "SymmetricData/");
					tsp = displaySymmetric(sym);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			  } 
			} ) ;
		
		JButton asymmetricButton = new JButton("Asymmetric");
		asymmetricButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e){ 
				try {
					AsymmetricData asym = new AsymmetricData(path + "AsymmetricData/");
					tsp = displayAsymmetric(asym);
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
		return tsp;
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
	
	public double[][] displaySymmetric(SymmetricData sym) throws IOException
	{
		ArrayList<String[]> fileList = sym.GetFileList();
		double[][] tsp = sym.GetData("src/Data/SymmetricData/qa194.tsp", 194);
		JTable table = GetJTable(fileList);
		table.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	try {
					sym.GetData(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
	        }
	    });
		
		mainPanel.removeAll();
		mainPanel.add(scrollPane);
		setGraphics();
		return tsp;
	}
	
	public double[][] displayAsymmetric(AsymmetricData asym) throws IOException
	{
		ArrayList<String[]> fileList = asym.GetFileList();
		double[][] tsp = asym.GetData("src/Data/AsymmetricData/ry48p.atsp", 48);
		
		JTable table = GetJTable(fileList);
		table.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
	        	try {
					asym.GetData(fileList.get(table.getSelectedRow())[3].toString(), Integer.parseInt(fileList.get(table.getSelectedRow())[2].toString()));
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
	        }	    });
		
		mainPanel.removeAll();
		mainPanel.add(scrollPane);
		setGraphics();
		return tsp;
	}
	
	public void setGraphics() {
		frame.add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
