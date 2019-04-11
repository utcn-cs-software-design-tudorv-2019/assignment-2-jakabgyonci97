package ass2.ass2.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="personalinformation")
public class PersonalInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idpersonal", nullable = false)
    private int idPersonal;

    @Column(name = "idstudent", nullable = false)
    private int idStudent;

    @Column(name = "first_name", length = 15)
    private String firstName;

    @Column(name = "last_name", length = 15)
    private String lastName;

    @Column(name = "icn", length = 8)
    private String icn;

    @Column(name = "pnc", length = 13)
    private String pnc;

    public PersonalInformation(){
        super();
    }

    public PersonalInformation(int idStudent, String firstName, String lastName, String icn, String pnc) {
        this.idStudent = idStudent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.icn = icn;
        this.pnc = pnc;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
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

    public String getIcn() {
        return icn;
    }

    public void setIcn(String icn) {
        this.icn = icn;
    }

    public String getPnc() {
        return pnc;
    }

    public void setPnc(String pnc) {
        this.pnc = pnc;
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "id=" + idPersonal +
                ", idStudent=" + idStudent +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", icn='" + icn + '\'' +
                ", pnc='" + pnc + '\'' +
                '}';
    }

    public PersonalInformation clone(){
        PersonalInformation pi = new PersonalInformation();
        pi.setIdPersonal(this.idPersonal);
        pi.setIdStudent(this.idStudent);
        pi.setFirstName(this.firstName);
        pi.setLastName(this.lastName);
        pi.setIcn(this.icn);
        pi.setPnc(this.pnc);
        return pi;
    }
}
