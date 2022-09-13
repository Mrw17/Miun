package dt066g.assignments.assignment8.task1;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Your Name (your@email)
 * @version 1.0
 */

public class University {
	private String name;
	
	private int year;
	
	private List<Course> courses = new ArrayList<>();

	private List<Subject> Subjects = new ArrayList<>();

	private List<Institution> institutions = new ArrayList<>();

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

	public List<Subject> getSubjects() {
		return Subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		Subjects = subjects;
	}

	public List<Institution> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<Institution> institutions) {
		this.institutions = institutions;
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