package ngsm.com.mycollege.Util;

import java.util.ArrayList;

/**
 * Created by Rishi on 12/4/2016.
 */
public class Faculty{
    private String name, image,designation,subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Faculty() {

    }

    public Faculty(String name, String image,String designation,String subject) {
        this.name = name;
        this.image =image;
        this.designation = designation;
        this.subject = subject;
    }


}
