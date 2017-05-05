package cs355.solution;

import cs355.GUIFunctions;
import cs355.controller.MyMouseMotionListener;
import cs355.controller.myController;
import cs355.controller.myMouseListener;
import cs355.view.View;
import java.awt.Color;

/**
 * This is the main class. The program starts here.
 * Make you add code below to initialize your model,
 * view, and controller and give them to the app.
 */
public class CS355 {

	/**
	 * This is where it starts.
	 * @param args = the command line arguments
	 */
	public static void main(String[] args)
	{
		GUIFunctions.createCS355Frame(myController.inst(), View.inst());
               
		GUIFunctions.refresh();
		GUIFunctions.changeSelectedColor(Color.WHITE);
		
	}
}
