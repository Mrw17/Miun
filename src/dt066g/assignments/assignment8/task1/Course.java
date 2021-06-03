package dt066g.assignments.assignment8.task1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Your Name (your@email)
 * @version 1.0
 */

public class Course {
	private String courseCode;
	private String institutionCode;
	private String level;
	private String name;
	private double points;
	private String progression;
	private String subjectCode;
	
	private Subject subject;
	private Institution institution;

	public Course(String course_code, String institution_code, String level, String name, int points, String progression, String subject_code) {
		this.courseCode = course_code;
		this.institutionCode = institution_code;
		this.level = level;
		this.name = name;
		this.points = points;
		this.progression = progression;
		this.subjectCode = subject_code;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public String getProgression() {
		return progression;
	}

	public void setProgression(String progression) {
		this.progression = progression;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	@Override
	public String toString() {
		// The returned value will be used in the JList
		return courseCode + " - " + name;
	}
}