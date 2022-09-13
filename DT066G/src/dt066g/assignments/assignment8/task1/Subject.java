package dt066g.assignments.assignment8.task1;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Daniel Westerlund
 * Class that represents a Subject
 */
public class Subject {
    private String bodyText;

    private String preamble;

    private String subject;

    private String subjectCode;

    private String language;

    public Subject(){}

    public Subject(String bodyText, String preamble, String subject, String subjectCode){
        this.bodyText = bodyText;
        this.preamble = preamble;
        this.subject = subject;
        this.subjectCode = subjectCode;
    }

    public Subject(String body_text, String preamble, String subject, String subject_code, String language) {
        this(body_text,preamble,subject,subject_code);
        this.language = language;
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