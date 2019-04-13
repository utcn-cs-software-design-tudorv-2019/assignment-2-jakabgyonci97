package ass2.ass2.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "studentactivity")
public class StudentActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idactivity", nullable = false)
    private int studActId;

    @Column(name = "idstudent", nullable = false)
    private int idStudent;

    @Column(name = "activitydate")
    private Date activityDate;

    @Column(name = "activitytype", length = 10)
    private String activityType;

    @Column(name = "description", length = 200)
    private String description;

    public StudentActivity() {
        super();
    }

    public StudentActivity(int idStudent, Date activityDate, String activityType, String description) {
        this.idStudent = idStudent;
        this.activityDate = activityDate;
        this.activityType = activityType;
        this.description = description;
    }

    public int getStudActId() {
        return studActId;
    }

    public void setStudActId(int studActId) {
        this.studActId = studActId;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static boolean isActivityValid(String activity) {
        switch (activity) {
            case "CREATE":
            case "UPDATE":
            case "DELETE":
            case "ENROLLMENT":
                return true;
        }
        return false;
    }

    public String toString() {
        return idStudent + "\t" + activityDate + "\t" + activityType + "\t" + description;
    }
}
