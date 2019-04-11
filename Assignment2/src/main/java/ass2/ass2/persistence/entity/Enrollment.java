package ass2.ass2.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="enrollment")
public class Enrollment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "enrollmentId",nullable = false)
    private int enrollmentId;

    @Column(name = "idCourse",nullable = false)
    private int idCourse;

    @Column(name = "idStudent",nullable = false)
    private int idStudent;

    @Column(name = "finalGrade")
    private double finalGrade;

    public Enrollment(){
        super();
    }

    public Enrollment(int idCourse, int idStudent, double finalGrade) {
        this.idCourse = idCourse;
        this.idStudent = idStudent;
        this.finalGrade = finalGrade;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + enrollmentId +
                ", idCourse=" + idCourse +
                ", idStudent=" + idStudent +
                ", finalGrade=" + finalGrade +
                '}';
    }
}
