
import java.io.IOException;

/**
 * The Main class is the driver class which instantiates an object of Graphics
 * class that interactively asks the user to choose from given options
 * displayed on screen.
*/
public class Main {

	/**
	 * main method is the driver method to instantiate an object of Graphics class
	 * 
	 * @throws IOException signals if any IO exception occurred while reading
	 * the files.
	*/
	public static void main (String[] args) throws IOException {

		GraphicsInterface graphics = new Graphics();
		graphics.Start();
	}
}
