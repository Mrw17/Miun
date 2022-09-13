package dt066g.assignments.assignment6.task1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Your Name (your@email)
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="courses")
public class University {
	@XmlAttribute(name="university")
	private String name;
	
	@XmlAttribute
	private int year;
	
	@XmlElement(name="course")
	private List<Course> courses = new ArrayList<>();
	
	Subjects subjects;
	Institutions institutions;

	public University(String name, int year, List<Course> courses) {
		this.name = name;
		this.year = year;
		this.courses = courses;
	}

	public University(String name, int year) {
		this.name = name;
		this.year = year;
	}

	public University() {
	}
	
	public void addCourse(Course c) {
		courses.add(c);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Subjects getSubjects() {
		return subjects;
	}

	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}

	public Institutions getInstitution() {
		return institutions;
	}

	public void setInstitution(Institutions institution) {
		this.institutions = institution;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("University [name=");
		builder.append(name);
		builder.append(", year=");
		builder.append(year);
		builder.append(", courses=");
		builder.append(courses);
		builder.append("]");
		return builder.toString();
	}	
}