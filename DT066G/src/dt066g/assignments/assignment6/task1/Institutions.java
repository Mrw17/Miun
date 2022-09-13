package dt066g.assignments.assignment6.task1;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Institutions {

    public Institutions(){
    }

    @XmlElement (name="institution")
    private List<Institution> institution = null;


    @XmlElement(name="university")
    private String university;

    @XmlAttribute
    private int year;

    public List<Institution> getInstitution() {
        return institution;
    }

    public void setInstitution(List<Institution> institution) {
        this.institution = institution;
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
