package dt066g.assignments.assignment6.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author Daniel Westerlund
 * @version 1.0
 */
public class XMLHandler {
	private static final File PATH = new File("src/assets", "assignment6");
	public static final File XML_UNIVERSITY = new File(PATH, "miun_courses.xml");
	public static final File XML_SUBJECTS = new File(PATH, "miun_subjects.xml");
	public static final File XML_INSTITUTIONS = new File(PATH, "miun_institutions.xml");
	
	/**
	 * Unmarshal XML data from the specified file and return the resulting content tree as a University object.  
	 * 
	 * @param xmlFile The XML file containing courses for an university.
	 * @return An University object with all courses in xmlFile or null if an error occurs.
	 */
	public static University getUniversity(File xmlFile) {
		// The University object to return
		University university = null;
		
		try {
			// Create a new instance of a JAXBContext that recognizes the University class (and the classes it uses).
			JAXBContext context = JAXBContext.newInstance(University.class);
			
			// The Unmarshaller class governs the process of deserializing XML data to Java object
			Unmarshaller unmarshaller = context.createUnmarshaller();


			// Unmarshal XML data from the specified file and return the resulting content tree.
			university = (University) unmarshaller.unmarshal(xmlFile);

			//Gets more data
			university.setSubjects(getSubject(XML_SUBJECTS));
			university.setInstitution(getIns(XML_INSTITUTIONS));


			//Gets all institutions and saves them into course object where their institution-code matches.
			for(Course course : university.getCourses()) {
				for(Institution institution :  university.getInstitution().getInstitution()) {
					if (course.getInstitutionCode().toUpperCase().equals(institution.getInstitutionCode().toUpperCase())) {
						course.setInstitution(institution);
					}
				}
			}

			//Gets all subjects and saves them into course object where their subjects-code matches.
			for(Course course : university.getCourses()){
				for(Subject sub :  university.getSubjects().getSubjects()){
					if(course.getSubjectCode().equals(sub.getSubjectCode())){
						course.setSubject(sub);
					}
				}
			}


		} catch (JAXBException e) {
			// If an unexpected problem occurs during the unmarshalling
			System.err.println("An error occurd during unmarsalling: " + e.getMessage());
			e.printStackTrace();
		}
		
		return university; // null if an error occurred
	}


	/**
	 * Get all institutions from a given xml-file
	 * @param xmlFile path to xml-file
	 * @return all Institutions from given file
	 */
	public static Institutions getIns(File xmlFile) {
		Institutions institutions = null;

		try {
			// Create a new instance of a JAXBContext that recognizes the University class (and the classes it uses).
			JAXBContext context = JAXBContext.newInstance(Institutions.class);

			// The Unmarshaller class governs the process of deserializing XML data to Java object
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// Unmarshal XML data from the specified file and return the resulting content tree.
			institutions = (Institutions) unmarshaller.unmarshal(xmlFile);

		} catch (Exception e) {
			// If an unexpected problem occurs during the unmarshalling
			System.err.println("An error occurd during unmarsalling: " + e.getMessage());
			e.printStackTrace();
		}

		return institutions;
	}

	/**
	 * Get all Subjects from a given xml-file
	 * @param xmlFile path to xml-file
	 * @return all subject from given file
	 */
	public static Subjects getSubject(File xmlFile) {
		//List<Subjects> subjects = new ArrayList<>();
		Subjects subjects = null;

		try {
			// Create a new instance of a JAXBContext that recognizes the Subjects class (and the classes it uses).
			JAXBContext context = JAXBContext.newInstance(Subjects.class);

			// The Unmarshaller class governs the process of deserializing XML data to Java object
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// Unmarshal XML data from the specified file and return the resulting content tree.
			subjects = (Subjects) unmarshaller.unmarshal(xmlFile);

		} catch (Exception e) {
			// If an unexpected problem occurs during the unmarshalling
			System.err.println("An error occurd during unmarsalling: " + e.getMessage());
			e.printStackTrace();
		}

		return subjects;
	}


}