package ngsm.com.mycollege;


/**
 * Created by Daksha on 12/2/2016.
 */

public class PlacementHistory {

    public String companyName;
    public String year;
    public String noOfStudents;

    public PlacementHistory() {
    }

    public PlacementHistory(String companyName, String noOfStudents, String year) {
        this.companyName = companyName;
        this.noOfStudents = noOfStudents;
        this.year = year;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(String noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
