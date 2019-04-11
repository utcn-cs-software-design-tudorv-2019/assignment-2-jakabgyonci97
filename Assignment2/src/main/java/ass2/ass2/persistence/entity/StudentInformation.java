package ass2.ass2.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "studentinformation")
public class StudentInformation implements Serializable {
    public static final String[] SCHOLARSHIP = {"NONE","MERIT","ACADEMIC"};
    public static boolean isValidScholarShip(String status){
        for(String itr: SCHOLARSHIP)
            if(itr.compareTo(status)==0) return true;
        return false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "studInfoId",nullable = false)
    private int studInfoId;

    @Column(name = "idStudent",nullable = false)
    private int idStudent;

    @Column(name = "studGroup", length = 8)
    private String studGroup;

    @Column(name = "scholarShipState", length = 10)
    private String scholarShipState;

    @Column(name = "gradeAvrg")
    private double gradeAvrg;

    public StudentInformation(){
        super();
    }

    public StudentInformation(int idStudent, String studGroup, String scholarShipState, double gradeAvrg) {
        this.idStudent = idStudent;
        this.studGroup = studGroup;
        this.scholarShipState = scholarShipState;
        this.gradeAvrg = gradeAvrg;
    }

    public int getStudInfoId() {
        return studInfoId;
    }

    public void setStudInfoId(int studInfoId) {
        this.studInfoId = studInfoId;
    }

    public String getStudGroup() {
        return studGroup;
    }

    public void setStudGroup(String studGroup) {
        this.studGroup = studGroup;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getGroup() {
        return studGroup;
    }

    public void setGroup(String studGroup) {
        this.studGroup = studGroup;
    }

    public String getScholarShipState() {
        return scholarShipState;
    }

    public void setScholarShipState(String scholarShipState) {
        this.scholarShipState = scholarShipState;
    }

    public double getGradeAvrg() {
        return gradeAvrg;
    }

    public void setGradeAvrg(double gradeAvrg) {
        this.gradeAvrg = gradeAvrg;
    }

    @Override
    public String toString() {
        return "StudentInformation{" +
                "id=" + studInfoId +
                ", idStudent=" + idStudent +
                ", group='" + studGroup + '\'' +
                ", scholarShipState=" + scholarShipState +
                ", gradeAvrg=" + gradeAvrg +
                '}';
    }

    public StudentInformation clone(){
        StudentInformation si = new StudentInformation();
        si.setStudInfoId(this.studInfoId);
        si.setIdStudent(this.idStudent);
        si.setGroup(this.studGroup);
        si.setScholarShipState(this.scholarShipState);
        si.setGradeAvrg(this.gradeAvrg);
        return si;
    }
}
