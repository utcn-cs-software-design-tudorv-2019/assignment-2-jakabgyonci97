package ass2.ass2.business.model;

public class StudentProfile {
    private int idStudent;
    private String firstName;
    private String lastName;
    private String group;
    private String scholarShipState;
    private double average;

    public StudentProfile() {
        super();
    }

    public StudentProfile(int idStudent, String firstName, String lastName, String group, String scholarShipState, double average) {
        this.idStudent = idStudent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.scholarShipState = scholarShipState;
        this.average = average;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getScholarShipState() {
        return scholarShipState;
    }

    public void setScholarShipState(String scholarShipState) {
        this.scholarShipState = scholarShipState;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "StudentProfile{" +
                "idStudent=" + idStudent +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                ", scholarShipState='" + scholarShipState + '\'' +
                ", average=" + average +
                '}';
    }
}