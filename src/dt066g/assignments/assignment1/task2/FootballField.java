package dt066g.assignments.assignment1.task2;

/**
 * @author Robert Jonsson, DSV Östersund
 */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class FootballField extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final double LENGTH = 100; // Length in meter
	private final double WIDTH = 64; // Width in meter
	private final double LINE_WIDTH = 0.24; // Line width in meter (normally max 12 centimeters)
	
	private AffineTransform original = null;
	private AffineTransform centerAndScale = null;
	
	private Image football;
	
	public FootballField() {
		// Example how to load an football image from assets folder
		football = new ImageIcon("assignment1/football10.png").getImage();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawFotballField(g);
		
		// Draw image of football in the center of the field
		g.drawImage(football, (getWidth() / 2) - (football.getWidth(null) / 2),
				(getHeight() / 2) - (football.getHeight(null) / 2), null);
	}

	private void drawFotballField(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Calculate scale (so that the field always is as large as it can be in the frame) 
		final double SCALE = Math.min(getWidth() / LENGTH, getHeight() / WIDTH);

		// Save current transform
		original = g2.getTransform();

		// Create a new transform to center and scale the field
		centerAndScale = new AffineTransform();
		centerAndScale.translate((getWidth() - LENGTH * SCALE) / 2,
				(getHeight() - WIDTH * SCALE) / 2);
		centerAndScale.scale(SCALE, SCALE);
		
		// Use the new transform when drawing
		g2.transform(centerAndScale);

		// Draw the field and lines surrounding it
		g2.setPaint(new Color(2, 152, 52));
		g2.fill(new Rectangle2D.Double(0, 0, LENGTH, WIDTH));
		g2.setStroke(new BasicStroke((float) (LINE_WIDTH)));
		g2.setPaint(Color.white);
		g2.draw(new Rectangle2D.Double(0, 0, LENGTH, WIDTH));

		// Draw center line and circle
		g2.draw(new Line2D.Double(LENGTH / 2, 0, LENGTH / 2, WIDTH));
		g2.fill(new Ellipse2D.Double(LENGTH / 2 - 0.5 / 2, WIDTH / 2 - 0.5 / 2, 	0.5, 0.5));
		g2.draw(new Ellipse2D.Double(LENGTH / 2 - 9.15, WIDTH / 2 - 9.15, 9.15 * 2, 9.15 * 2));

		// Draw the four corners
		g2.draw(new Arc2D.Double(-1, -1, 2, 2, 270, 90, Arc2D.OPEN));
		g2.draw(new Arc2D.Double(-1, -1 + WIDTH, 2, 2, 0, 90, Arc2D.OPEN));
		g2.draw(new Arc2D.Double(-1 + LENGTH, -1, 2, 2, 180, 90, Arc2D.OPEN));
		g2.draw(new Arc2D.Double(-1 + LENGTH, -1 + WIDTH, 2, 2, 90, 90,	Arc2D.OPEN));

		// Draw the penalty areas
		g2.draw(new Rectangle2D.Double(0, WIDTH / 2 - 30.32 / 2, 16.5, 30.32));
		g2.draw(new Rectangle2D.Double(LENGTH - 16.5, WIDTH / 2 - 30.32 / 2, 16.5, 30.32));

		// Draw goal areas
		g2.draw(new Rectangle2D.Double(0, WIDTH / 2 - 12.82 / 2, 5.5, 12.82));
		g2.draw(new Rectangle2D.Double(LENGTH - 5.5, WIDTH / 2 - 12.92 / 2, 5.5, 12.82));

		// Draw penalty shot circle
		g2.fill(new Ellipse2D.Double(11, WIDTH / 2 - 0.5 / 2, 0.5, 0.5));
		g2.fill(new Ellipse2D.Double(-11 + LENGTH, WIDTH / 2 - 0.5 / 2, 0.5, 0.5));

		// Draw an arc outside the menalty areas
		g2.draw(new Arc2D.Double(11, WIDTH / 2 - 9.15 / 2, 9.15, 9.15, 285,	150, Arc2D.OPEN));
		g2.draw(new Arc2D.Double(-(11 + 9.15) + LENGTH, WIDTH / 2 - 9.15 / 2, 9.15, 9.15, 105, 150, Arc2D.OPEN));

		// Reset original transform
		g2.setTransform(original);
	}
}