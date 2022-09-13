package dt066g.assignments.assignment6.task1;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Daniel Westerlund
 * Class that contains all Subjects
 */
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Subjects {

    @XmlElement (name="subject")
    private List<Subject> subjects = null;

    @XmlElement (name="university")
    private String university;

    @XmlAttribute
    private int year;

    public Subjects(){}
    public Subjects(List<Subject> subjects){
        this.subjects = subjects;
    }


        public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}