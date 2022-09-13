package dt066g.assignments.assignment6.task1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * An JPanel for showing all data for a Course object.
 * 
 * @author Your Name (your@email)
 * @version 1.0
 */
public class CourseDataJPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	// An enum for all course data and their display names 
	private static enum CourseData {COURSE_CODE("Course code"), NAME("Name"),
		POINTS("Points"), LEVEL("Level"), PROGRESSION("Progression"),
		SUBJECT("Subject"), INSTITUTION("Institution");
		
		private String displayName;
	
		private CourseData(String displayName ) {
			this.displayName = displayName;
		}
	}

	// Map of all JTextField (for course data)
	private Map<CourseData, JTextField> courseDataTextFields;
	
	/**
	 * Creates a new CourseDataJPanel with a border layout.
	 */
	public CourseDataJPanel() {
		super(new BorderLayout());
		initComponents();
	}
	
	private void initComponents() {
		// Create panel for course data labels and text fields		
		JPanel courseDataPanel = new JPanel();
		courseDataPanel.setLayout(new BoxLayout(courseDataPanel, BoxLayout.PAGE_AXIS));
				
		// Initialize map for all text components
		courseDataTextFields = new HashMap<>();
		
		// Create labels and text fields for each enum value
		for (CourseData data : CourseData.values()) {
			// Create and add the label
			JLabel label = new JLabel(data.displayName);
			label.setAlignmentX(Component.LEFT_ALIGNMENT); // Make the label align to left in the box layout
			courseDataPanel.add(label);
			
			// Create the text field
			JTextField text = new JTextField();
			text.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			// Disable input, set text color and add to map
			text.setEnabled(false);
			text.setDisabledTextColor(text.getForeground()); // Set disabled text color to the same as ordinary text color
			courseDataTextFields.put(data, text);
			courseDataPanel.add(text);
		}
		
		// Add panel to frame
		add(courseDataPanel, BorderLayout.PAGE_START);	
	}
	
	/**
	 * Updates the panel with data from the given Course. If Course is null,
	 * the panel will cleared (remove all current data).
	 * 
	 * @param course The Course to display data from
	 */
	public void setCourse(Course course) {
		if (course == null) {
			clear();
			return;
		}

		courseDataTextFields.get(CourseData.COURSE_CODE).setText(course.getCourseCode());
		courseDataTextFields.get(CourseData.NAME).setText(course.getName());
		courseDataTextFields.get(CourseData.POINTS).setText(Double.toString(course.getPoints()));
		courseDataTextFields.get(CourseData.LEVEL).setText(course.getLevel());
		courseDataTextFields.get(CourseData.PROGRESSION).setText(course.getProgression());

		courseDataTextFields.get(CourseData.SUBJECT).setText(course.getSubject().getSubject());
		courseDataTextFields.get(CourseData.INSTITUTION).setText(course.getInstitution().getInstitution());
		
		// Move the caret position to 0 (needed if the text is longer than the text fields width)
		courseDataTextFields.forEach((courseData, textComponent) -> {
			textComponent.setCaretPosition(0);
		});		
	}
	
	/**
	 * Clear all course data text fields in the panel.
	 */
	public void clear() {
		courseDataTextFields.forEach((courseData, textComponent) -> {
			textComponent.setText(null);
		});
	}
}
