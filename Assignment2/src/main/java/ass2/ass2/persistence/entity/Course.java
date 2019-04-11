package ass2.ass2.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "courseId",nullable = false)
    private int courseId;

    @Column(name = "courseName",length = 20)
    private String name;

    @Column(name = "courseSession",length = 9)
    private String session;

    @Column(name = "examDate")
    private Date examDate;

    @Column(name = "enrollmentKey",length = 10)
    private String enrollmentKey;

    public Course(){
        super();
    }

    public Course(String name, String session, Date examDate, String enrollmentKey) {
        this.name = name;
        this.session = session;
        this.examDate = examDate;
        this.enrollmentKey = enrollmentKey;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getEnrollmentKey() {
        return enrollmentKey;
    }

    public void setEnrollmentKey(String enrollmentKey) {
        this.enrollmentKey = enrollmentKey;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + courseId +
                ", name='" + name + '\'' +
                ", session='" + session + '\'' +
                ", examDate=" + examDate +
                ", enrollmentKey='" + enrollmentKey + '\'' +
                '}';
    }
}
