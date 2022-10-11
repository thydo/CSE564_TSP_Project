import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Graphics {
	private JFrame frame;
	public Graphics(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setGraphics();
	}
	
	public void Start()
	{
		JPanel mainPanel = new JPanel();
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
	    
	    frame.add(mainPanel);
	    setGraphics();
	}
	
	public void displaySymmetric()
	{
		System.out.println("SYMMETRICCC");
	}
	
	public void displayAsymmetric()
	{
		System.out.println("AAAASYMMETRICCC");
	}
	
	public void setGraphics() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
