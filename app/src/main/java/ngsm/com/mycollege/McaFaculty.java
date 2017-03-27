package ngsm.com.mycollege;

/**
 * Created by daksha on 2/17/2017.
 */

public class McaFaculty {

    public String name;
    public String designation;
    public String subject;
    public String image;

    public McaFaculty(String name) {
        this.name = name;
    }

    public McaFaculty() {
    }

    public McaFaculty(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public McaFaculty(String name, String designation, String subject, String image) {
        this.name = name;
        this.designation = designation;
        this.subject = subject;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
