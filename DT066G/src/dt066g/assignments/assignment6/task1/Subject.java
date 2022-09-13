package dt066g.assignments.assignment6.task1;


import javax.xml.bind.annotation.*;


/**
 * @author Daniel Westerlund
 * Class that represents a Subject
 */
@XmlRootElement(name="subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {
    private String bodyText;

    private String preamble;

    private String subject;

    private String subjectCode;

    public Subject(){}

    public Subject(String bodyText, String preamble, String subject, String subjectCode){
        this.bodyText = bodyText;
        this.preamble = preamble;
        this.subject = subject;
        this.subjectCode = subjectCode;
    }


    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
    public String getPreamble() {
        return preamble;
    }

    public void setPreamble(String preamble) {
        this.preamble = preamble;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
}