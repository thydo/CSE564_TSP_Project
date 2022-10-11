import java.io.IOException;

import javax.swing.*;

public class Main {

	public static void main (String[] args) throws IOException
	{
		Graphics graphics = new Graphics();
		graphics.Start();
		
		String path = "src/Data/";
		Data asym = new AsymmetricData(path + "AsymmetricData/");
		Data sym = new SymmetricData(path + "SymmetricData/");
	}
}
