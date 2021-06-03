package dt066g.assignments.assignment8.task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

/**
 * @author Your Name (your@email)
 * @version 1.0
 */
public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Text field in which the user can search/filter courses
	private JTextField searchJTextField;
	
	// A JList that lists Course objects (read from the XML file)
	private JList<Course> courseJList;
	
	// The University with all courses
	private University university;
	
	// The JPanel for showing data of the selected course
	private CourseDataJPanel courseDataPanel;

	public Main() {
		// Set the title of the JFrame (window)
		setTitle("Courses at Mid Sweden University");

		// What should happen when the user closes the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Using null centers the window on the screen
		setLocationRelativeTo(null);
		
		// Set an empty border around the windows content pane
		getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Use a border layout with 10px gaps
		setLayout(new BorderLayout(10, 10));
		
		// Initialize all UI components
		initComponents();

		// The size of the window
		setSize(600, 400);

		// Make the window visible
		setVisible(true);
		
		// Load university data
		loadUniversity();
	}

	private void initComponents() {				
		// Create panel for search		
		JPanel searchPanel = new JPanel(new GridLayout(2, 1));
		
		// Create and add label for searching
		searchPanel.add(new JLabel("Search course:"));
		
		// Create and add text field for searching
		searchJTextField = new JTextField();
		
		// Listen for changes in the caret position and update the course list when its changed
		searchJTextField.addCaretListener(event -> {
			// Update/refresh courses in the list
			filterCourseList(searchJTextField.getText());
		});
		
		// Add the search text field to the search panel (below the search label)
		searchPanel.add(searchJTextField);
		
		// Create the JList for all courses
		courseJList = new JList<Course>(new DefaultListModel<Course>());
		
		// Listen for clicks on courses in the course list
		courseJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Get clicked (selected) course and pass it to the course data panel
				courseDataPanel.setCourse(courseJList.getSelectedValue());
			}
		});
		
		// Create the course data panel
		courseDataPanel = new CourseDataJPanel();
		
		// Create a scroll pane for the course list and course data panel
		JScrollPane listScrollPane = new JScrollPane(courseJList);
		listScrollPane.setPreferredSize(new Dimension(250, 300));
		
		JScrollPane dataScrollPane = new JScrollPane(courseDataPanel);
		dataScrollPane.setBorder(null);
		
		// Create a panel (with 10px gap) for course list and course data
		JPanel courseListAndDataPanel = new JPanel(new BorderLayout(10, 10));
		courseListAndDataPanel.add(listScrollPane, BorderLayout.LINE_START);
		courseListAndDataPanel.add(dataScrollPane);
		
		// Add panels to frame
		add(searchPanel, BorderLayout.PAGE_START);
		add(courseListAndDataPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Loads an universities courses from the XML file specified in 
	 * XMLHandler.XML_UNIVERSITY. If no error occurs, and the university 
	 * has courses, the course list is updated.
	 */
	private void loadUniversity() {
		// Get the university
		university = SQLHandler.getUniversity();
		
		// Add all courses to the JList's model
		if (university != null && university.getCourses() != null) {
			// Get current list model (should be an empty DefaultListModel<Course> at this stage)
			DefaultListModel<Course> listModel = (DefaultListModel<Course>) courseJList.getModel();
			
			// Add all course from the university to the model
			listModel.removeAllElements(); // just in case
			listModel.addAll(university.getCourses());
		}
	}

	/**
	 * Filters the courses in the list so only courses, whose course code or course 
	 * name, match the given filter are displayed.
	 * 
	 * @param filter The filter to apply to the list of courses.
	 */
	private void filterCourseList(String filter) {
		// Get current list model
		DefaultListModel<Course> listModel = (DefaultListModel<Course>) courseJList.getModel();
		
		// Get a filtered list of courses
		java.util.List<Course> filteredList = university.getCourses().stream().filter(course -> {
			return (course.getCourseCode().toLowerCase().contains(filter.toLowerCase()) ||
					course.getName().toLowerCase().contains(filter.toLowerCase()));
		}).collect(Collectors.toList());
		
		// Clear old courses and add the filtered courses
		listModel.clear();
		listModel.addAll(filteredList);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(() -> new Main());
	}
}
