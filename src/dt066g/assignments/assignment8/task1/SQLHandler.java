package dt066g.assignments.assignment8.task1;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dt066g.assignments.assignment8.task1.Util;

/**
 * @author Daniel Westerlund
 * @version 1.0
 */
public class SQLHandler {
	private static final File PATH = new File("src/assets", "assignment6");
	public static final File XML_UNIVERSITY = new File(PATH, "miun_courses.xml");
	private static Connection connection;

	/**
	 * Loads courses, subject and institute from DB
	 * @return An University object with all courses and their data
	 */
	public static University getUniversity() {
		University university = new University();
		try{
			connection = DriverManager.getConnection(Util.DB_URL, Util.DB_USER, Util.DB_PASSWORD);
			System.out.println("Connected to: " + Util.DB_URL);

			university.setCourses(getCourses());
			university.setSubjects(getSubjects());
			university.setInstitutions(getInstitution());

			setCourseInstitution(university);
			setCourseSubjects(university);

			connection.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return university; // null if an error occurred
	}

	private static void setCourseSubjects(University university) {
		//Gets all subjects and saves them into course object where their subjects-code matches.
		for(Course course : university.getCourses()){
			for(Subject sub :  university.getSubjects()){
				if(course.getSubjectCode().equalsIgnoreCase(sub.getSubjectCode())){
					course.setSubject(sub);
					break;
				}
			}
		}
	}

	private static void setCourseInstitution(University university) {
		//Gets all institutions and saves them into course object where their institution-code matches.
		for(Course course : university.getCourses()) {
			for(Institution institution :  university.getInstitutions()) {
				if (course.getInstitutionCode().equalsIgnoreCase(institution.getInstitutionCode())) {
					course.setInstitution(institution);
					break;
				}
			}
		}
	}

	private static List<Institution> getInstitution() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM institution;";
		ResultSet result = statement.executeQuery(sql);
		ArrayList<Institution> institutions = new ArrayList<>();

		while(result.next()) {
			String description = result.getString("description");
			String institution = result.getString("institution");
			String institution_code = result.getString("institution_code");

			Institution inst = new Institution(description,institution,institution_code);
			institutions.add(inst);
		}
		result.close();
		statement.close();
		return institutions;
	}

	private static List<Subject> getSubjects() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM subject;";
		ResultSet result = statement.executeQuery(sql);
		ArrayList<Subject> subjects = new ArrayList<>();

		while(result.next()) {
			String body_text = result.getString("body_text");
			String preamble = result.getString("preamble");
			String subject = result.getString("subject");
			String subject_code = result.getString("subject_code");
			String language = result.getString("language");

			Subject sub = new Subject(body_text, preamble, subject, subject_code, language);
			subjects.add(sub);
		}
		result.close();
		statement.close();
		return subjects;
	}

	private static List<Course> getCourses() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM course;";

		ResultSet result = statement.executeQuery(sql);
		ArrayList<Course> courses = new ArrayList<>();
		while(result.next()) {
			int i = 1;
			String course_code = result.getString("course_code");
			String institution_code = result.getString("institution_code");
			String level = result.getString("level");
			String name = result.getString("name");

			//fast fix on those courses that dont have name
			if(name == null)
				name = "";

			int points = result.getInt("points");
			String progression = result.getString("progression");
			String subject_code = result.getString("subject_code");

			Course course = new Course(course_code,institution_code,level,name,points,progression,subject_code);
			courses.add(course);
		}
		result.close();
		statement.close();
		return courses;
	}
}