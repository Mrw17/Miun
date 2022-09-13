package dt066g.assignments.assignment1.task1;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */

import java.awt.*;

public class Ball extends PaintObject {
	public Ball(int x, int y) {
		// Call the constructor of our super class, setting x, y, min and max values
		super(x, y, 20, 1);
	}

	// This method is called when someone (the calling method) want us to draw ourselves 
	// We use the provided Graphics object to do the drawing
	@Override
	public void draw(Graphics g) {
		g.fillOval(x, y, size, size);
	}
}