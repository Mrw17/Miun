package dt066g.assignments.assignment1.task2;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */

import java.awt.*;
import javax.swing.*;

public class TestFootball extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private FootballField footballField;
	
	public TestFootball() {
		// Set the title of the JFrame (window)
		setTitle("DT066G: Assignment 1 - task 2");

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
		
		// NOTE! It is OK if you make the window none resizeable

		// Make the window visible
		setVisible(true);
	}
	
	private void initComponents() {
		// Create our football field
		footballField = new FootballField();

		// Add the panel to the window
		add(footballField, BorderLayout.CENTER);	
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new TestFootball());	
	}
}