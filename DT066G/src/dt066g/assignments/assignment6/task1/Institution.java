package dt066g.assignments.assignment6.task1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Daniel Westerlund
 * Class that represents a institution
 */
@XmlRootElement(name="institution")
@XmlAccessorType(XmlAccessType.FIELD)
public class Institution {

    private String description;
    private String institution;
    private String institutionCode;

    public Institution(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
}
