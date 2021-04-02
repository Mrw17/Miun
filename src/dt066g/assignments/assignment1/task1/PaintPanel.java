package dt066g.assignments.assignment1.task1;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PaintPanel extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	
	/* A list of Drawable objects
	 * The reason why we have to enter the full name (including package) is that
	 * there is also a class named list in the java.awt package.*/
	private java.util.List<Drawable> drawableObjects;

	public PaintPanel() {
		// Use an ArrayList to store the drawable objects
		drawableObjects = new java.util.ArrayList<>();

		// Create the Timer used to repaint the panel (redraw every drawable object)
		Timer t = new Timer(50, this);
		t.start();

		// The panel must listen for mouse events (left, right click) so we register
		// our own class as a listener after such events
		addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // always!

		// Loop through all the drawable objects in our list and let them
		// paint themselves on the panel
		for (Drawable d : drawableObjects) {
			d.draw(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// Repaint the panel every time the timer fires an ActionEvent
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		// Find out if left or right mouse button was clicked
		if (me.getButton() == MouseEvent.BUTTON1) {
			// Left button was clicked. Lets add a drawable ball
			//drawableObjects.add(new Ball(me.getX(), me.getY()));
			//Left mouse btn -> Add Triangle on screen
			drawableObjects.add(new Triangle(me.getX(),me.getY()));
		} else {
			// Right button was clicked. Lets add a drawable line
			//drawableObjects.add(new Line(me.getX(), me.getY()));
			//Right mouse btn -> Add Rectangle on screen
			drawableObjects.add(new Rectangle(me.getX(), me.getY()));
		}
	}

	@Override
	public void mouseEntered(MouseEvent ignore) { }

	@Override
	public void mouseExited(MouseEvent ignore) { }

	@Override
	public void mousePressed(MouseEvent ignore) { }

	@Override
	public void mouseReleased(MouseEvent ignore) { }
}