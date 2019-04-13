package ass2.ass2.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @Column(name = "studentid", nullable = false)
    private int studentid;

    @Column(name = "userid")
    private int userid;

    public Student(int studentid, int userid) {
        this.studentid = studentid;
        this.userid = userid;
    }

    public Student() {
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentid=" + studentid +
                ", userid=" + userid +
                '}';
    }
}
