package ngsm.com.mycollege;

/**
 * Created by Daksha on 12/13/2016.
 */

public class Post {

    private String title;
    private String desc;
    private String image;

    public Post() {
    }

    public Post(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
