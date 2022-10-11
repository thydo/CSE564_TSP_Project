import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Graphics {
	private JFrame frame;
	JPanel mainPanel;
	public Graphics(){
		frame = new JFrame();
		mainPanel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setGraphics();
	}
	
	public void Start()
	{
		JPanel labelPanel = new JPanel(new GridLayout(1,1, 10, 20));
		labelPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel dataPromptLabel = new JLabel("Would you like to view Symmetric or Asymmetric data?");
		
		JButton symmetricButton = new JButton("Symmetric");
		symmetricButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    displaySymmetric();
			  } 
			} );
		
		JButton asymmetricButton = new JButton("Asymmetric");
		asymmetricButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    displayAsymmetric();
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
	
	public void displaySymmetric()
	{
		System.out.println("SYMMETRICCC");
		
		ArrayList<String[]> fileList = Main.sym.GetFileList();
		JTable table = GetJTable(fileList);
		table.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            System.out.println(fileList.get(table.getSelectedRow())[3].toString());
	        }
	    });
		
		mainPanel.removeAll();
		mainPanel.add(scrollPane);
		setGraphics();
	}
	
	
	
	public void displayAsymmetric()
	{
		System.out.println("AAAASYMMETRICCC");
		ArrayList<String[]> fileList = Main.sym.GetFileList();
		JTable table = GetJTable(fileList);
		table.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            System.out.println(fileList.get(table.getSelectedRow())[3].toString());
	        }
	    });
		
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
