package ngsm.com.mycollege;

/**
 * Created by daksha on 3/9/2017.
 */

public class StudentProfile {

    public String name;
    public String sID;
    public String image;

    public StudentProfile() {
    }

    public StudentProfile(String name) {
        this.name = name;
    }

    public StudentProfile(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public StudentProfile(String image, String name, String sID) {
        this.image = image;
        this.name = name;
        this.sID = sID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
