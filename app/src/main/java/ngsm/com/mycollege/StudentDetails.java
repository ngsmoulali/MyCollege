package ngsm.com.mycollege;

/**
 * Created by Daksha on 12/6/2016.
 */

public class StudentDetails {

    private String name;
    private String email;
    private String password;
    private String image;

    public StudentDetails() {
    }

    public StudentDetails(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public StudentDetails(String name, String email, String password, String image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
