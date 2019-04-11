package ass2.ass2.business.model;

import java.sql.Date;

public class EnrolledCourse {
    private String courseName;
    private String session;
    private Date examDate;
    private double finalGrade;

    public EnrolledCourse(String courseName, String session, Date examDate, double finalGrade) {
        this.courseName = courseName;
        this.session = session;
        this.examDate = examDate;
        this.finalGrade = finalGrade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    @Override
    public String toString() {
        return courseName+"\t"+session+"\t"+examDate+"\t"+finalGrade;
    }
}
