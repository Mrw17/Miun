package dt066g.assignments.assignment1.task1;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TestGrowableShrinkable extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Our own GUI component for displaying growable and shrinkable objects
	private PaintPanel panel;

	public TestGrowableShrinkable() {
		// Set the title of the JFrame (window)
		setTitle("DT066G: Assignment 1 - task 1");

		// What should happen when the user closes the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Using null centers the window on the screen
		setLocationRelativeTo(null);

		// Specify the layout to use in the window
		setLayout(new BorderLayout());

		// Initialize all components
		initComponents();

		// The size of the window
		setSize(400, 300);

		// Make the window visible
		setVisible(true);
	}

	private void initComponents() {
		// Create our panel
		panel = new PaintPanel();

		// Add the panel to the window
		add(panel, BorderLayout.CENTER);	
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new TestGrowableShrinkable());
	}
}