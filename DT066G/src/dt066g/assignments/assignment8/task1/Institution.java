package dt066g.assignments.assignment8.task1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Daniel Westerlund
 * Class that represents a institution
 */

public class Institution {

    private String description;
    private String institution;
    private String institutionCode;

    public Institution(){}

    public Institution(String description, String institution, String institution_code) {
        this.description = description;
        this.institution = institution;
        this.institutionCode = institution_code;
    }

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
